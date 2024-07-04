import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.*;

public class PortServer01_03b{
    static public boolean DEBUG = true;
	
	private static boolean  mReceivingData;
	private static boolean  mStopRequested;
	final static String header = "HTTP/1.1 200 OK\nContent-type: text/html;charset=utf-8\nContent-length: %d\n\n%s";
	static String FNAME = "";	
    public static void main(String[] args) throws Exception{  
		var PORT = 25;
		var serverSocket = new ServerSocket(PORT); 
		serverSocket.setReuseAddress(true);
		var TNAME = "";
		System.out.println("Listen on port: "+PORT);
		while(true){
			try {
				Thread.sleep(1 * 1 * 15 * 1000);
				} catch (final InterruptedException e) { 
					/* */ 
				}
			if( Thread.activeCount() > 10 ){
				continue;
				}
			/*
				Criação de uma nova Thread para CADA CLIENTE do socket
			*/
			Thread t = new Thread(() -> {
				try {
					// Função readRequest para detalhamento
					handleSocket(serverSocket);
					} catch(Exception e){
					/* */
					}
				} ) ;

			// Extrai informações da Thread [No, Name, int, class]
			TNAME = t.toString();
			var rs = readThreadReq(TNAME);
			System.out.println("Thread No.:" + rs.numero + " Name: " + rs.nome);
			// Inicia a Thread
			t.start();
			}
		} // Fim do main
	private record ThreadReq(String numero, String nome){
		/* */
		}
	// Função: readThreadReq
	private static ThreadReq readThreadReq(String tName){
		var arr = tName.split("\\[");
		tName = arr[1];
		arr = tName.split("\\]");
		tName = arr[0];
		arr = tName.split(",");
		var thNumber = arr[0];
		var thName = arr[1];		
		return new ThreadReq(thNumber, thName);
		}
    // Função: handleSocket
	private static void handleSocket(final ServerSocket pSS) {
        final String name = "handle on PORT:" + pSS.getLocalPort() + "";
        while (!mStopRequested) {
            System.out.println(name + "\tWaiting for connection...");
            try (
				final Socket socket = pSS.accept();
				final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), BaseMail.DEFAULT_CHARSET_SMTP_POP3));
				final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), BaseMail.DEFAULT_CHARSET_SMTP_POP3));) {
                System.out.println("Stream readed: ");
				System.out.println(in);
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
	// Função: handle
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
	// Função: send
    static private void send(final String pMessage, final BufferedWriter pBW) {
        try {
            pBW.write(pMessage + "\n");
            pBW.flush();
            if (DEBUG) System.out.println("SENT:\t" + pMessage);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
	// Função: read
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
	private record HttpReq(String metodo, String path, Map<String, List<String>> headers, byte[] body){
		
		}
	}  