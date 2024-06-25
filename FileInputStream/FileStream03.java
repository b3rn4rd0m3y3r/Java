import java.net.ServerSocket;
import java.io.*;
import java.nio.charset.*;

public class FileStream03{  
    public static void main(String[] args) throws IOException{  
		var FILENAME = "index.html";
		// Descritor de arquivo em InputStream
		FileInputStream myFile = new FileInputStream(FILENAME);
		// Instancia a BufferedInputStream
		BufferedInputStream input = new BufferedInputStream(myFile);
		// Primeiro byte
		var b = input.read();
		// Leitura da Stream - INICIO
		while( b != -1 ){
			System.out.print((char)b);
			b = input.read();
			}
		// Leitura da Stream - FIM
		input.close();
		}  
	}  