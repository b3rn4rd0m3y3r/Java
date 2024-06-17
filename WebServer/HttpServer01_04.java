import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
//import java.util.Map;
//import java.util.List;
import java.util.*;
import java.lang.*;
//import java.lang.Record;
import java.net.*;

public class HttpServer01_04{  
    public static void main(String[] args) throws Exception{  
		var serverSocket = new ServerSocket(8098); 
		var header = "HTTP/1.1 200 OK\nContent-type: text/html\nContent-length: %d\n\n%s";
		while(true){
			var connection = serverSocket.accept();
			var request = readRequest(connection);
			System.out.println("Requisição: "+request);
			System.out.println("_________________________________________");
			try(var os = connection.getOutputStream()){
				var body = "<meta charset=\"UTF-8\"><h1>Requisição</h1><h2>Deu Certo</h2>";
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
		// Novo nesta versão 4
		var headers = readHeaders(r);
		//return new HttpReq(metodo, path, Map.of(), null);
		System.out.println("Headers: "+headers);
		return new HttpReq(metodo, path, headers, null);
		}
	// Novo nesta versão 4	
	private static 	Map<String, List<String>> readHeaders(BufferedReader reader) throws Exception{
		var line = reader.readLine();
		var headers = new HashMap<String, List<String>>();
		while( line != null && !line.isEmpty()){
			var par = line.split(":",2);
			var chave = par[0];
			var value = par[1];
			headers.computeIfAbsent(chave, k->new ArrayList<>()).add(value);
			System.out.println("Chave: "+chave+ " - " + value);
			line = reader.readLine();
			}
		return headers;
		}
		
	private record HttpReq(String metodo, String path, Map<String, List<String>> headers, byte[] body){
		
		}
	}  