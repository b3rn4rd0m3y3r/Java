import java.net.ServerSocket;
import java.nio.charset.*;

public class HttpServer01_01{  
    public static void main(String[] args) throws Exception{  
		var serverSocket = new ServerSocket(8098); 
		var header = "HTTP/1.1 200 OK\nContent-type: text/html\nContent-length: %d\n\n%s";
		while(true){
			var connection = serverSocket.accept();
			try(var os = connection.getOutputStream()){
				var body = "<h1>Deu Certo</h1>";
				var response = String.format(header,body.getBytes(StandardCharsets.UTF_8).length, body);
				os.write(response.getBytes(StandardCharsets.UTF_8));
				}
			}
		}  
	}  