import java.net.ServerSocket;
import java.io.*;
import java.nio.charset.*;

public class FileStream02{  
    public static void main(String[] args) throws IOException{  
		var FILENAME = "index.html";
		File myFile = new File(FILENAME);
		// Instancia a InputStream a partir de uma FileInputStream
		InputStream myStream = new FileInputStream(myFile);
		var NoBytes = myStream.available();
		System.out.println("Bytes: "+NoBytes);
		byte[] data = new byte[NoBytes];
		myStream.read(data);
		// Leitura da Stream - INICIO
		for(var i=0;i<NoBytes;i++){
			System.out.print((char)data[i]);
			}
		// Leitura da Stream - FIM
		myStream.close();
		}  
	}  