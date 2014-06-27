package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class testLargeFileTransmit {
	public static void main(String[] args) throws IOException {
		String filePath = "/Users/Kim/Desktop/test.jpg";
		File file = new File(filePath);
		FileInputStream fileIn = new FileInputStream(file);
		Socket soc = new Socket("128.237.220.250", 1234);
		ObjectOutputStream objOut = new ObjectOutputStream(soc.getOutputStream());
		byte[] buff = new byte[20480000];
		while (fileIn.read(buff) != 0) {
			objOut.writeObject(buff);
		}		
		objOut.close();
		soc.close();
		fileIn.close();
	}
}
