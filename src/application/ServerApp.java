package application;


import java.net.InetAddress;
import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.RemoteObjectRef;
import ror.UnicastRemoteObject440;
import example2.Person;
import example2.PersonInterface;

/**
 * This is an example to write a RMI application on the server side.
 */
public class ServerApp {
	public static void main(String[] args) throws InterruptedException, UnknownHostException, ClassNotFoundException {
		
		//SayHello remoteObj = new SayHello();
		Person person = new Person();
		RemoteObjectRef ror = UnicastRemoteObject440.exportObject(person, 0);
		String host = InetAddress.getLocalHost().getHostName();
		Registry440 registry = LocateRegistry440.getRegistry(host, 1099);
		registry.rebind("Person" ,ror);
		System.out.println("Bind on registry");
		
		PersonInterface personStub = (PersonInterface) ror.localise();
	}
}
