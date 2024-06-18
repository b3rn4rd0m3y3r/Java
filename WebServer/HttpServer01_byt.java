import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
import java.util.Map;
import java.util.List;
import java.lang.*;
//import java.lang.Record;
import java.net.*;

public class HttpServer01_02{  
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
		
		}
	private static HttpReq readRequest(Socket connection) throws Exception{
			//var r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//var line = r.readLine();
			//System.out.println("Requested line = "+line);
			//var body = readBody(connection.getInputStream());
			System.out.println("Bytes = ");
			System.out.println(readBody(connection.getInputStream()));
		return null;
		}
		
	//private static byte[] readBody(InputStream stream) throws Exception {
	private static int readBody(InputStream stream) throws Exception {
		//ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		int number = stream.available();
		byte[] data = new byte[number];
		//DataInputStream dis = new DataInputStream(stream);
		//dis.readFully(data);
		//byte[] data = new byte[16384];
		//data = stream.readAllBytes();
		//while ((nRead = stream.read(data, 0, data.length)) != -1) {
		for(var i=0;i<number+900;i++){
			nRead = stream.read();
			System.out.print((char)nRead);
			}		
		//System.out.println("Buffer = ");
		//System.out.println(buffer.toByteArray());
		//return buffer.toByteArray();
		return number;
		}
	}  