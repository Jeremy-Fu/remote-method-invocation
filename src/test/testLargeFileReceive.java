package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class testLargeFileReceive {
	static int BUFFSIZE = 1000000; // 1M
	public static void main (String[] args) {
		try {
			byte[] buff = new byte[BUFFSIZE];
			ServerSocket serverSoc = new ServerSocket(1234);
			Socket soc = serverSoc.accept();
			ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
			File qin = new File("/Users/JeremyFu/Downloads/qin.mkv");
			FileOutputStream out = new FileOutputStream(qin);
			System.out.println("start read...");
			int i = 0;
			while ((buff = (byte[])in.readObject()) != null) {
				i++;
				out.write(buff);
				if (i%100 == 0) {
					System.out.println("Received 100M");
				}
			}
			in.close();
			soc.close();
			serverSoc.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
