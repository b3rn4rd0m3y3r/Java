import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
import java.util.*;
//import java.util.Map;
//import java.util.List;
import java.lang.*;
import java.net.*;

public class HttpServer01{  
    public static void main(String[] args) throws Exception{  
		var serverSocket = new ServerSocket(8098); 
		
		while(true){
			var connection = serverSocket.accept();
			var request = readRequest(connection);
			try(var os = connection.getOutputStream()){
				var body = "<h1>Deu Certo</h1>";
				var response = String.format("HTTP/1.1 200 OK\nContent-type: text/html\nContent-length: %d\n\n%s",body.getBytes(StandardCharsets.UTF_8).length, body);
				//var response = header.formatted();
				os.write(response.getBytes(StandardCharsets.UTF_8));
				}
			}
		}  
	private record HttpReq(String method, String url, Map<String, List<String>> headers, byte[] body){
		//System.out.println("Method = "+method);
		}
	private static HttpReq readRequest(Socket connection) throws Exception{
			var r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			var line = r.readLine();
			System.out.println("Requested line = "+line);
			
		return null;
		}
	}  