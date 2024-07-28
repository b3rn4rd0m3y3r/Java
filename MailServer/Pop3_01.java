import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.*;

public class Pop3_01{
	static String FNAME = "";	
    public static void main(String[] args) throws Exception{  
		var PORT = 110;
		var serverSocket = new ServerSocket(PORT); 
		serverSocket.setReuseAddress(true);
		var TNAME = "";
		System.out.println("Listen on port: "+PORT);
		// Definição do tempo de sleep da Thread
		try {
			Thread.sleep(15 * 1000); // 15 segundos
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
				System.out.println("Thread " + TNAME);
				// Inicia a Thread
				t.setDaemon(true);
				t.start();
			} // Fim do while
		} // Fim do main
	// Função createConn - Tratamento de uma conexão cliente
	private static String createConn(Socket connection) throws Exception {
			BufferedReader in = null;
			BufferedWriter out = null;
			try {
                    in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                    out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8)); 
					} catch (final IOException e) {
					System.err.println(e.getMessage());
					}
			var response = "";
			send("+OK POP3 server ready", out);
			System.out.println(connection.getRemoteSocketAddress());
			System.out.println(connection.toString());
			String pRead = "";
			connection.setSoTimeout(10000);
			// Tratamento da conexão com cliente
			// Teste de comandos recebidos
			loopData:while(true){
				// Testa se a conexão caiu
				if( connection.isClosed() ){
					break;
					}
				// Rastreia envios do cliente
				pRead = read(in);
				// Null
				if( !pRead.equals(null) ) {
					System.out.println(pRead);
					break loopData; 
					} else {
					System.out.println(pRead);
					}
				// "." - Fim dos dados
				if( pRead.startsWith(".") ){
					System.out.println("END OF DATA");
					break;
					}
				// "QUIT" - SAIR
				if( pRead.startsWith("QUIT") ){
					System.out.println("QUIT found");
					break;
					}
				} // Fim do while
			return response;
		} // Fim de createConn
	// Função read (sobre BufferedInputStream)
    static private String read(final BufferedReader pBR) throws IOException {
        try {
            final String reply = pBR.readLine();
            if( !reply.equals("") ){
				System.out.println("RECV:\t" + reply);
				}
            return reply;
        } catch (final SocketTimeoutException e) {
            System.err.println("SERVER TIMEOUT");
        }
        return null;
    }
	// Função send (sobre BufferedWriter)
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
		} // Final de send
	}  