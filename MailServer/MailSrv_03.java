import java.net.ServerSocket;
import java.nio.charset.*;
import java.text.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.*;

public class MailSrv_03{
	static String FNAME = "";	
    public static void main(String[] args) throws Exception{  
		var PORT = 25;
		var SLEEP_TIME = 15;
		var serverSocket = new ServerSocket(PORT); 
		serverSocket.setReuseAddress(true);
		var TNAME = "";
		System.out.println("Listen on port: "+PORT);
		// Definição do tempo de sleep da Thread
		try {
			Thread.sleep(SLEEP_TIME * 1000);
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
					} // Fim do try
				} ) ;  // Fim do Thread
				// Extrai informações da Thread [No, Name, int, class]
				TNAME = t.toString();
				// NOVO - Separa os dados do cabeçalho da Thread
				var rs = readThreadReq(TNAME);
				System.out.println("Thread No.:" + rs.numero + " Name: " + rs.nome);
				// Inicia a Thread
				t.setDaemon(true);
				t.start();
			} // Fim do while
		} // Fim do main
	// NOVO - Tipo record para o cabeçalho da Thread
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
	// Função createConn -  - Tratamento de uma conexão cliente
	private static String createConn(Socket connection) throws Exception {
			BufferedReader in = null;
			BufferedWriter out = null; 
			// NOVO
			StringBuilder sb = new StringBuilder();
			sb.append("From - " + (new Date()).toString() + "\n");
			sb.append("X-Mozilla-Status: 0001\n"); // Status de registro ativo
			sb.append("X-Mozilla-Status2: 00800000\n");
			try {
                    in = new BufferedReader(new InputStreamReader(connection.getInputStream(), BaseMail.CHARSET_SMTP_POP3));
                    out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), BaseMail.CHARSET_SMTP_POP3)); 
					} catch (final IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace(); // NOVO - Error trace
					}
			send("220 meyer SMTP ", out);
			String pRead = "";
				var pFrom = "";
				var pTo = "";
			// Flag de status "recebendo dados"
			boolean RecData = false;
			// Tratamento da conexão com cliente
			connection.setSoTimeout(10000); // Timeout
			// Teste de comandos recebidos
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
					RecData = false; // Status falso
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
					RecData = true; // Status true
					}				
				// Acrescenta porção lida ao argumento StringBuilder
				final String correctedRead = pRead.startsWith(".") ? pRead.substring(1) : pRead;
				
				var prot = correctedRead.split(":");

				if( prot[0].equals("From") ){
					pFrom = prot[1];
					}
				if( prot[0].equals("To") ){
					pTo = prot[1];
					}
				if( RecData && !correctedRead.equals("DATA") ){
					sb.append(correctedRead + "\n");
					}
				// Se não estiver recebendo dados, envia OK ao cliente
				if( !RecData ){
					send("250 OK", out);
					}
				}
			// NOVO - Grava o arquivo do email enviado
			System.out.println("Para: "+pTo);
			sb.append("\n");
			save(sb, "mails");
			save(sb, pTo.trim());
			return sb.toString();
		}
	// Função save (salva um email enviado)
	static void save(StringBuilder txt, String pasta)  throws IOException { 
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			final File file = new File(pasta+"/Inbox");
			//final File file = new File(pasta+"/env_" + sdf.format(new Date()) + "_email.txt");
			file.getParentFile().mkdirs();
			final String msg = txt.toString();
			try (FileOutputStream fos = new FileOutputStream(file, true)) {
				fos.write(msg.getBytes());
				}
			System.out.println("File saved as " + file.getCanonicalPath());		
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