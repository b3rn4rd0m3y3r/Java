import java.net.ServerSocket;
import java.nio.charset.*;
import java.text.*;
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
			StringBuilder sb = new StringBuilder();
			try {
                    in = new BufferedReader(new InputStreamReader(connection.getInputStream(), BaseMail.CHARSET_SMTP_POP3));
                    out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), BaseMail.CHARSET_SMTP_POP3)); 
					} catch (final IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
					}
			//System.out.println("Requisição: "+request);
			send("220 meyer SMTP ", out);
			String pRead = "";
			boolean RecData = false;
			loopData:while(true){
				// Testa se a conexão caiu
				if( connection.isClosed() ){
					break;
					}
				// Rastreia envios do cliente
				pRead = read(in);
				// Null
				if( pRead.equals(null) ) { break; }
				// "." - Fim dos dados
				if( pRead.equals(".") ){
					System.out.println("END OF DATA");
					send("250 OK", out);
					RecData = false;
					break;
					}
				// "QUIT" - SAIR
				if( pRead.equals("QUIT") ){
					System.out.println("QUIT found");
					send("221 meyer logoff", out);
					break loopData;
					}
				// "DATA" - Estão chegando dados pela stream
				if( pRead.equals("DATA") ){
					send("354 End data with <CR><LF>.<CR><LF>", out);
					RecData = true;
					}				
				//response += pRead;
				// Acrescenta porção lida ao argumento StringBuilder
				final String correctedRead = pRead.startsWith(".") ? pRead.substring(1) : pRead;
				sb.append(correctedRead + "\n");
				// Se não estiver recebendo dados, envia OK ao cliente
				if( !RecData ){
					send("250 OK", out);
					}
				}
			// Grava o arquivo do email enviado
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			final File file = new File("mails/env_" + sdf.format(new Date()) + "_email.txt");
			file.getParentFile().mkdirs();
			final String msg = sb.toString();
			try (FileOutputStream fos = new FileOutputStream(file)) {
				fos.write(msg.getBytes());
			}
			System.out.println("File saved as " + file.getCanonicalPath());
			return msg;
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