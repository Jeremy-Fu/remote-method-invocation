package test;

import example.sayhello.SayHelloInterface;
import exception.RemoteException440;
import registry.LocateRegistry440;
import registry.Registry440;

/**
 * test6: tests unchecked (runtime) exception during remote method invocation
 * 
 * Client: passes null as parameter and the method will throw a runtime
 * exception
 *
 */
public class test6Client {
	public static void main(String[] args) {
		String serviceName = null;
		String serverIp = null;
		int serverPort  = 0;
		if (args.length != 3) {
			System.out.println("Usage: java test.test6Client <ServiceName> <ServerIp> <ServerPort>");
			return;
		} else {
			serviceName = args[0];
			serverIp = args[1];
			serverPort = Integer.parseInt(args[2]);
		}
		
		Registry440 registry = null;
		try {
			registry = LocateRegistry440.getRegistry(serverIp, serverPort);
		} catch (RemoteException440 e1) {
			e1.printStackTrace();
		}
		SayHelloInterface sayHello = null;
		try {
			sayHello = (SayHelloInterface) registry.lookup(serviceName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* This remote call will throw a NullPointerException */
		sayHello.resetPerson(null);
	}
}
