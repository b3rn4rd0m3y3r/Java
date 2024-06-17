import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
//import java.util.Map;
//import java.util.List;
import java.util.*;
import java.lang.*;
//import java.lang.Record;
import java.net.*;

public class HttpServer01_03{  
    public static void main(String[] args) throws Exception{  
		var serverSocket = new ServerSocket(8098); 
		var header = "HTTP/1.1 200 OK\nContent-type: text/html;charset=utf-8\nContent-length: %d\n\n%s";
		while(true){
			var connection = serverSocket.accept();
			var request = readRequest(connection);
			// Novo nesta versão 3
			System.out.println("Requisição: "+request);
			try(var os = connection.getOutputStream()){
				var body = "<h1>Deu Certo</h1>";
				var response = String.format(header,body.getBytes(StandardCharsets.UTF_8).length, body);
				os.write(response.getBytes(StandardCharsets.UTF_8));
				}
			}
		}  

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
			System.out.println("Metodo: "+metodo);
			System.out.println("Path: "+path);
			System.out.println("Protocolo: "+protocol);
			}
		return new HttpReq(metodo, path, Map.of(), null);
		}
	private record HttpReq(String metodo, String path, Map<String, List<String>> headers, byte[] body){
		
		}
	}  