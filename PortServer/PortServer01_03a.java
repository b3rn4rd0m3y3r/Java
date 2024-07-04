import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.*;

public class PortServer01_03a{
    final static String header = "HTTP/1.1 200 OK\nContent-type: text/html;charset=utf-8\nContent-length: %d\n\n%s";
	static String FNAME = "";	
    public static void main(String[] args) throws Exception{  
		var PORT = 25;
		var serverSocket = new ServerSocket(PORT); 
		serverSocket.setReuseAddress(true);
		var TNAME = "";
		System.out.println("Listen on port: "+PORT);
		while(true){
			var connection = serverSocket.accept();
			/*
				Criação de uma nova Thread para CADA CLIENTE do socket
			*/
			Thread t = new Thread(() -> {
				try {
					// Função readRequest para detalhamento
					var request = readRequest(connection);
					createConn(connection, request); 
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
	private static String createConn(Socket connection, String request) throws Exception {
			// Função readRequest para detalhamento
			//var request = readRequest(connection);
			// Exibe request
			System.out.println("Requisição: "+request);
			var response = "";
			var body = "SMTP resp";
			try(var os = connection.getOutputStream()){
						response = String.format(header,body.getBytes(StandardCharsets.UTF_8).length, body);	
				} // Fim do try
			return response;
		}
	// Função readRequest
	private static String readRequest(Socket connection) throws Exception{
		var r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		var line = r.readLine();
		var metodo = "";
		var path = "";
		var protocol = "";
		// Novo nesta versão 3
		// Da requisição retornam ...
		return line;
		}
	private record HttpReq(String metodo, String path, Map<String, List<String>> headers, byte[] body){
		
		}
	// Função readBody
	private static String readBody(String FILENAME)  throws Exception{
		FileInputStream myFile = new FileInputStream(FILENAME);
		// Instancia a BufferedInputStream
		BufferedInputStream input = new BufferedInputStream(myFile);
		// Primeiro byte
		var b = input.read();
		// Leitura da Stream - INICIO
		String body = "";
		while( b != -1 ){
			body +=  (char)b;
			b = input.read();
			}
		// Leitura da Stream - FIM
		input.close();
		return body;
		}
	}  