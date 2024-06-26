import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
import java.util.*;
//import java.util.Map;
//import java.util.List;
import java.lang.*;
//import java.lang.Record;
import java.net.*;

public class HttpServer01_02{  
    public static void main(String[] args) throws Exception{  
		var serverSocket = new ServerSocket(8098); 
		System.out.println("Socket: "+serverSocket);
		var header = "HTTP/1.1 200 OK\nContent-type: text/html;charset=utf-8\nContent-length: %d\n\n%s";
		while(true){
			var connection = serverSocket.accept();
			System.out.println("Connection: "+connection);
			// Request
			var request = readRequest(connection);
			try(var os = connection.getOutputStream()){
				var body = "<h1>Requisição</h1><h2>Chegou</h2>";
				var response = String.format(header,body.getBytes(StandardCharsets.UTF_8).length, body);
				os.write(response.getBytes(StandardCharsets.UTF_8));
				}
			}
		}  
	private record HttpReq(String method, String url, Map<String, List<String>> headers, byte[] body){
		}
	private static HttpReq readRequest(Socket connection) throws Exception{
		var is = new InputStreamReader(connection.getInputStream());
		var r = new BufferedReader(is);
		//var line = is;
		//var line = r;
		var line = r.readLine();
		System.out.println("Requested line = "+line);
		return null;
		}
	}  