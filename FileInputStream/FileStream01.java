import java.net.ServerSocket;
import java.io.*;
import java.nio.charset.*;

public class FileStream01{  
    public static void main(String[] args) throws IOException{  
		var FILENAME = "index.html";
		File myFile = new File(FILENAME);
		InputStream myStream = new FileInputStream(myFile);
		myStream.close();
		}  
	}  