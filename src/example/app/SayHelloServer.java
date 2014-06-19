package example.app;

import java.net.InetAddress;
import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.SayHello;
import example.SayHelloInterface;

public class SayHelloServer {
	public static void main(String[] args) throws UnknownHostException {
		SayHelloInterface sayHello = new SayHello();
		SayHelloInterface ror = (SayHelloInterface) UnicastRemoteObject440.exportObject(sayHello, 0);
		String host = InetAddress.getLocalHost().getHostName();
		Registry440 registry = LocateRegistry440.getRegistry(host, 1099);
		registry.rebind("SayHelloOnJeremyRegistry" ,ror);
		System.out.println("Bind on registry");
	}
}
