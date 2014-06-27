package test;

import example.person.PersonInterface;
import example.sayhello.SayHelloInterface;
import exception.RemoteException440;
import registry.LocateRegistry440;
import registry.Registry440;

/**
 * test3: tests the case in which the remote method's return value is an
 * exported remote object (the stub of the object is returned instead).
 * 
 * Client: It gets a stub of SayHelloInterface object named 'SayHelloOnServerRegistry',
 * creates a person object (remote object) and returns a stub of person object
 */
public class test3Client {
	public static void main(String[] args) {
		String serviceName = null;
		String serverIp = null;
		int serverPort  = 0;
		if (args.length != 3) {
			System.out.println("Usage: java example.test3.test3Client <ServiceName> <ServerIp> <ServerPort>");
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
		PersonInterface person = sayHello.createPerson();
		System.out.println("Class Name of person: " + person.getClass().getName());
		
		person.setName("Andy");
		try {
			System.out.println(sayHello.sayHello(person));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
