import java.net.ServerSocket;
import java.nio.charset.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.*;

public class HttpServer01_03c{  
    public static void main(String[] args) throws Exception{  
		var PORT = 8098;
		var serverSocket = new ServerSocket(PORT); 
		var header = "HTTP/1.1 200 OK\nContent-type: text/html;charset=utf-8\nContent-length: %d\n\n%s";
		System.out.println("Listen on port: "+PORT);
		// Aguarda uma conexão cliente
		while(true){
			// 1 - Instancia um socket cliente
			var connection = serverSocket.accept();
			// 2 - Função readRequest para detalhamento
			var request = readRequest(connection);
			// 2.1 - Exibe a requisição na console
			System.out.println("Requisição: "+request);
			// 2.2 - Filename
			var fileName = request.path;
			fileName = (String)fileName;
			// 2.2.1 - Filename sem barra
			var arr = fileName.split("/");
			fileName = arr[1];
			System.out.println("PATH final: "+fileName);
			// 2.2.2 - Extensão
			arr = fileName.split("\\.");
			System.out.println(arr[1]);
			// 2.2.2.1 - Teste da Extensao
			var extensao = arr[1].toLowerCase();
			if( !extensao.equals("html") && !extensao.equals("htm")  && !extensao.equals("css")   && !extensao.equals("js")  && !extensao.equals("php")  && !extensao.equals("py") ){
				System.out.println("Extensão: "+extensao+ " não será processada pelo SERVER");
				continue;
				}
			try(var os = connection.getOutputStream()){
				// Escolha do processamento de scripts ou arquivos do HTTP
				if( extensao.equals("php") ){
					var body = execPHP(fileName,"");
					var response = String.format(header,body.getBytes(StandardCharsets.UTF_8).length, body);
					os.write(response.getBytes(StandardCharsets.UTF_8));
					} else {
					if( extensao.equals("py") ){
						var body = execPython(fileName,"");
						var response = String.format(header,body.getBytes(StandardCharsets.UTF_8).length, body);
						os.write(response.getBytes(StandardCharsets.UTF_8));
						} else {						
						var body = readBody(fileName);
						if( !extensao.equals("css") && !extensao.equals("js") ){
							body += "<p><i>By HttpServer Java</i></p>";
							}
						var response = String.format(header,body.getBytes(StandardCharsets.UTF_8).length, body);	
						os.write(response.getBytes(StandardCharsets.UTF_8));
						}
					} // Fim de extensao.equals
				} // Fim do try
			} // Fim do while
		} // Fim do main
	
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
			}
		return new HttpReq(metodo, path, Map.of(), null);
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
	// Função execPHP
	private static String execPHP(String scriptName, String param) {
		String line;
		StringBuilder output = new StringBuilder();
		try {

			Process p = Runtime.getRuntime().exec("php " + scriptName + " " + param);
			BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				output.append(line);
				}
			input.close();
				} catch (Exception err) {
					err.printStackTrace();
					}
			return output.toString();
			}
	// Função execPython
	private static String execPython(String scriptName, String param) {
		String line;
		StringBuilder output = new StringBuilder();
		try {

			Process p = Runtime.getRuntime().exec("python " + scriptName + " " + param);
			BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				output.append(line);
				}
			input.close();
				} catch (Exception err) {
					err.printStackTrace();
					}
			return output.toString();
			}
	}  