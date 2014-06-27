package test;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.sayhello.SayHello;
import example.sayhello.SayHelloInterface;

public class testSayHelloServer {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage:\t<service name>");
		}
		
		try {
			String name = args[0];
			SayHello sayHello = new SayHello();
			SayHelloInterface sayHelloStub = (SayHelloInterface) UnicastRemoteObject440.exportObject(sayHello, 0);
			Registry440 registryStub = LocateRegistry440.getRegistry("localhost", 1099);
			registryStub.rebind(name , sayHelloStub);
			System.out.println(name + " bound to registry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
