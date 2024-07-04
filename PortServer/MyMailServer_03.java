import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class BaseMail {

    static public final int     SMTP_PORT = 25;
    static public final int     SMTP_PORT_LEGAL = 587;
    static public final int     SMTP_PORT_SMTPS = 465;
    static public final int[]   SMTP_PORTS  = { SMTP_PORT, SMTP_PORT_LEGAL, SMTP_PORT_SMTPS };

    static public final int     POP_PORT_POP3          = 110;
    static public final int     POP_PORT_SSL        = 995;
    static public final int     POP_PORT_KERBEROS   = 1109;
    static public final int[]   POP_PORTS           = { POP_PORT_POP3, POP_PORT_SSL, POP_PORT_KERBEROS };

    static public final String DEFAULT_CHARSET_SMTP_POP3 = "8859_1";

    static public final String  NAME                			  = "Meyer POP3 Server";
    static public final String  SERVICE_ADDRESS     = "bmy@meyer";
    static public final String  CMD_QUIT        			  = "QUIT";
    static public final String  CMD_DATA        			  = "DATA";
    static public final String  CMD_ENDATA    			  = ".";

    static public void send(final BufferedWriter pBW, final String pMessage) throws IOException {
        pBW.write(pMessage + "\n");
        pBW.flush();
        System.out.println("SENT:\t" + pMessage);
		}
    // Pode receber uma ou mais Strings em pExpectedRespPref
	static public String sendExpect(final BufferedWriter pBW, final String pMessage, final BufferedReader pBR, final String... pExpectedRespPref) throws IOException {
        send(pBW, pMessage);
        final String read = read(pBR);
        for (final String erp : pExpectedRespPref) {
            if (read.startsWith(erp)) return read;
			}
        throw new IllegalStateException("Bad response: Expected [" + toString(", ", pExpectedRespPref) + "] but [" + read + "] instead!");
		}

    static public String read(final BufferedReader pBR) throws IOException {
        final String reply = pBR.readLine();
        System.out.println("Recebi:\t" + reply);
        return reply;
    }

    @SafeVarargs public static <T> String toString(final String pSeparator, final T... pObjects) {
        if (pObjects == null) return null;
        final StringBuilder ret = new StringBuilder();
        for (final T o : pObjects) {
            ret.append(o + pSeparator);
        }
        if (ret.length() > 0) ret.setLength(ret.length() - pSeparator.length());
        return ret.toString();
    }

}

// PRTY - Test_SMTP_Server (sem parâmetros)
public class MyMailServer_03 {
    static public boolean DEBUG = true;
    public static void main(final String s[]) throws UnknownHostException, IOException {
        final MyMailServer_03 server = new MyMailServer_03(BaseMail.SMTP_PORTS);
        // Inicia server
		server.start();

        try {
            Thread.sleep(3600 * 1000);
			} catch (final InterruptedException e) { 
				/* */ 
			}
    }

    /*
     * Objeto RAIZ
	 * Variáveis globais
     */

    private final ServerSocket[]    mSockets;
    private volatile boolean         mStopRequested;
    private static boolean            mReceivingData;


	// PRTY - Test_SMTP_Server (com as portas)
    public MyMailServer_03(final int[] pPorts) throws IOException {
        mSockets = new ServerSocket[pPorts.length];
        for (int i = 0; i < pPorts.length; i++) {
            final int port = pPorts[i];
            try {
                mSockets[i] = new ServerSocket(port);
            } catch (final BindException e) {
                new BindException("Error on opening port " + port + ": " + e.getMessage()).printStackTrace();
            }
            System.out.println("ServerSocket created on port " + port);
        }
    }


	// PRTY - start (all sockets)
    public void start() {
        mStopRequested = false;
        for (final ServerSocket ss : mSockets) {
            if (ss == null) continue;
            // THREADs dos sockets
			final Thread t = new Thread(() -> handleServerSocket(ss), "handleServerSocket(" + ss.getLocalPort() + ")");
            t.setDaemon(true);
            t.start();
        }
    }
    // PRTY - handleServerSocket
	private void handleServerSocket(final ServerSocket pSS) {
        final String name = "handleServerSocket(" + pSS.getLocalPort() + ")";
        while (!mStopRequested) {
            System.out.println(name + "\tWaiting for connection...");
            try (
				final Socket socket = pSS.accept();
				final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), BaseMail.DEFAULT_CHARSET_SMTP_POP3));
				final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), BaseMail.DEFAULT_CHARSET_SMTP_POP3));) {
                System.out.println(name + "\tNew Socket.");
                handle(socket, in, out);
                System.out.println(name + "\tClosing Socket.");
            } catch (final IOException e) {
                System.err.println("In " + name + ":");
                e.printStackTrace();
            }
            System.out.println(name + "\tComm Done.");
        }
    }

    static private void handle(final Socket pSocket, final BufferedReader bufR, final BufferedWriter bufW) throws IOException {
        send("220 meyer SMTP " + BaseMail.NAME, bufW);

        final StringBuilder sb = new StringBuilder();

        mainLoop: while (!pSocket.isClosed()) {
            final String read = read(bufR);
            if (read == null) break;
			
			if( read == BaseMail.CMD_DATA ) { 
				//send("354 End data with <CR><LF>.<CR><LF>", bufW);
				mReceivingData = true; 
				} else {
				// Final: "."
				if( read == BaseMail.CMD_ENDATA ) { 
					send("250 OK", bufW);
					mReceivingData = false; 
					} else {
						if( read == BaseMail.CMD_QUIT ) {
							send("221 " + BaseMail.NAME + " logoff", bufW);
							break mainLoop;
						} else {
							if (!mReceivingData) send("250 OK", bufW);
						}
					}
				}
			System.out.println("Input read: "+read);
			}

    }

    static private void send(final String pMessage, final BufferedWriter pBW) {
        try {
            pBW.write(pMessage + "\n");
            pBW.flush();
            if (DEBUG) System.out.println("SENT:\t" + pMessage);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    static private String read(final BufferedReader pBR) throws IOException {
        try {
            final String reply = pBR.readLine();
            if( reply != "" && reply != null ){
				if (DEBUG) System.out.println("RECV:\t" + reply);
				}
            return reply;

        } catch (final SocketTimeoutException e) {
            System.err.println("SERVER TIMEOUT");
        }
        return null;
    }
	
}