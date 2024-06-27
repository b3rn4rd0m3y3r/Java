import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
//import java.util.Map;
//import java.util.List;
import java.util.*;
import java.lang.*;
//import java.lang.Record;
import java.net.*;

public class HttpServer01_03b{  
    public static void main(String[] args) throws Exception{  
		var PORT = 8098;
		var serverSocket = new ServerSocket(PORT); 
		var header = "HTTP/1.1 200 OK\nContent-type: text/html;charset=utf-8\nContent-length: %d\n\n%s";
		System.out.println("Listen on port: "+PORT);
		while(true){
			var connection = serverSocket.accept();
			// Função readRequest para detalhamento
			var request = readRequest(connection);
			// Novo nesta versão 3
			System.out.println("Requisição: "+request);
			// Filename
			var fileName = request.path;
			fileName = (String)fileName;
			// Filename sem barra
			var arr = fileName.split("/");
			fileName = arr[1];
			System.out.println("PATH final: "+fileName);
			arr = fileName.split("\\.");
			System.out.println(arr[1]);
			// Extensao
			var extensao = arr[1].toLowerCase();
			if( !extensao.equals("html") && !extensao.equals("htm")  && !extensao.equals("css")   && !extensao.equals("js")){
				System.out.println("Extensão: "+extensao+ " não será processada pelo SERVER");
				continue;
				}
			try(var os = connection.getOutputStream()){
				var body = readBody(fileName);
				if( !extensao.equals("css") && !extensao.equals("js") ){
					body += "<p><i>By HttpServer Java</i></p>";
					}
				var response = String.format(header,body.getBytes(StandardCharsets.UTF_8).length, body);
				os.write(response.getBytes(StandardCharsets.UTF_8));
				}
			}
		}  
	// Função readBody
	
	// Função readRequest
	private static HttpReq readRequest(Socket connection) throws Exception{
		var r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		var line = r.readLine();
		var metodo = "";
		var path = "";
		var protocol = "";
		// Novo nesta versão 3
		// Da requisição retornam: MÉTODO PATH PROTOCOL
		if( line != null ){	
			var RetornoISP = line.split(" ");
			metodo = RetornoISP[0];
			path = RetornoISP[1];
			protocol = RetornoISP[2];
			/*
			System.out.println("Metodo: "+metodo);
			System.out.println("Path: "+path);
			System.out.println("Protocolo: "+protocol);
			*/
			}
		return new HttpReq(metodo, path, Map.of(), null);
		}
	private record HttpReq(String metodo, String path, Map<String, List<String>> headers, byte[] body){
		
		}
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
			//System.out.print((char)b);
			b = input.read();
			}
		// Leitura da Stream - FIM
		input.close();
		return body;
		}
	}  