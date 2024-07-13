import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.*;

public class MailSrv_02{
	static String FNAME = "";	
    public static void main(String[] args) throws Exception{  
		var PORT = 25;
		var serverSocket = new ServerSocket(PORT); 
		serverSocket.setReuseAddress(true);
		var TNAME = "";
		System.out.println("Listen on port: "+PORT);
		try {
			Thread.sleep(20 * 1000);
			} catch (final InterruptedException e) { 
				/* */ 
			}
		// Inicia a aceitação de pedidos ao socket SERVIDOR
		while(true){
			var connection = serverSocket.accept();
			/*
				Criação de uma nova Thread para CADA CLIENTE do socket
			*/
			Thread t = new Thread(() -> {
				try {
					// Função createConn
					createConn(connection);
					// SocketException
					} catch (final SocketException e) {
					System.err.println(e.getMessage());		
					} catch(Exception e){
						/* */
					}
				} ) ;
				// Extrai informações da Thread [No, Name, int, class]
				TNAME = t.toString();
				var rs = readThreadReq(TNAME);
				System.out.println("Thread No.:" + rs.numero + " Name: " + rs.nome);
				// Inicia a Thread
				t.setDaemon(true);
				t.start();
			}
		} // Fim do main
	private record ThreadReq(String numero, String nome){
		/* */
		}
	// Função readThreadReq
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
	// Função createSocket
	private static String createConn(Socket connection) throws Exception {
			// Função readRequest para detalhamento
			BufferedReader in = null;
			BufferedWriter out = null;
			
			try {
                    in = new BufferedReader(new InputStreamReader(connection.getInputStream(), BaseMail.CHARSET_SMTP_POP3));
                    out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), BaseMail.CHARSET_SMTP_POP3)); 
					} catch (final IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					}
			//System.out.println("Requisição: "+request);
			var response = "";
			send("220 meyer SMTP ", out);
			String pRead = "";
			boolean RecData = false;
			loopData:while(true){
				if( connection.isClosed() ){
					break;
					}
				pRead = read(in);
				if( pRead.equals(null) ) { break; }
				if( pRead.equals(".") ){
					System.out.println("end OF DATA");
					send("250 OK", out);
					RecData = false;
					break;
					}
				if( pRead.equals("QUIT") ){
					System.out.println("QUIT found");
					send("221 meyer logoff", out);
					break loopData;
					}
				if( pRead.equals("DATA") ){
					send("354 End data with <CR><LF>.<CR><LF>", out);
					RecData = true;
					}				
				response += pRead;
				if( !RecData ){
					send("250 OK", out);
					}
				}
			return response;
		}
	// Função read (sobre BufferedInputStream)
    static private String read(final BufferedReader pBR) throws IOException {
        try {
            final String reply = pBR.readLine();
            System.out.println("RECV:\t" + reply);
            return reply;
        } catch (final SocketTimeoutException e) {
            System.err.println("SERVER TIMEOUT");
        }
        return null;
    }
	// Função send
    static private void send(final String pMessage, final BufferedWriter pBW) {
        try {
            pBW.write(pMessage + "\n");
            pBW.flush();
			if( !pMessage.equals("250 OK") ){
				System.out.println("SENT:\t" + pMessage);
				}
			// SocketException
			} catch (final SocketException e) {
				System.err.println("* send *");
				System.err.println(e.getMessage());
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

	}  