import java.net.ServerSocket;
import java.io.*;
import java.nio.charset.*;

public class FileStream04{  
    public static void main(String[] args) throws IOException{  
		var FILENAME = "index.html";
		// Descritor de arquivo em InputStream
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
		System.out.println(body);
		input.close();
		}  
	}  