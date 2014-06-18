package example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import example2.Person;
import message.InvokeMessage;
import message.RetMessage;


public class SayHelloTestServer_local {
	public static void main(String[] args) throws IOException {
		SayHello sayHello = new SayHello();
		ServerSocket server = new ServerSocket(55555);
		Socket socket = server.accept();
		
		ObjectInputStream objIn = new ObjectInputStream(socket.getInputStream());
		InvokeMessage msgIn = null;
		try {
			msgIn = (InvokeMessage) objIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		RetMessage msgOut = new RetMessage(sayHello.sayHello(new Person()), false);
		ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
		objOut.writeObject(msgOut);
		objOut.close();
		objIn.close();
	}
}
