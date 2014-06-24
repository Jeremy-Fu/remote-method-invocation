package example.app;

import java.net.InetAddress;
import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.sayhello.SayHello;
import example.sayhello.SayHelloInterface;

public class SayHelloServer {
	public static void main(String[] args) throws UnknownHostException {
		SayHelloInterface sayHello = new SayHello();
		SayHelloInterface sayHelloStub = (SayHelloInterface) UnicastRemoteObject440.exportObject(sayHello, 0);
		String host = InetAddress.getLocalHost().getHostName();
		Registry440 registry = LocateRegistry440.getRegistry(host, 1099);
		registry.rebind("SayHelloOnJeremyRegistry" ,sayHelloStub);
		System.out.println("Bind SayHello on Jeremy's registry");
	}
}
