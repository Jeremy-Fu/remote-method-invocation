package application;


import java.net.InetAddress;
import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.sayhello.SayHello;
import example.sayhello.SayHelloInterface;
import exception.RemoteException440;

/**
 * This is an example to write a RMI application on the server side.
 */
public class SayHelloServer {
	public static void main(String[] args) throws InterruptedException, UnknownHostException, ClassNotFoundException {

		SayHelloInterface sayHello = new SayHello();
		SayHelloInterface ror = (SayHelloInterface) UnicastRemoteObject440.exportObject(sayHello, 0);
		String host = InetAddress.getLocalHost().getHostName();
		Registry440 registry = null;
		try {
			registry = LocateRegistry440.getRegistry(host, 1099);
		} catch (RemoteException440 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		registry.rebind("SayHelloOnJeremyRegistry" ,ror);
		System.out.println("Bind on registry");
	}
}
