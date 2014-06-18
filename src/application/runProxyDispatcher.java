package application;


import java.net.InetAddress;
import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.RemoteObjectRef;
import ror.UnicastRemoteObject440;
import util.ProxyDispatcher;
import example.SayHello;
import example2.Person;

public class runProxyDispatcher {
	public static void main(String[] args) throws InterruptedException, UnknownHostException {
		
		//SayHello remoteObj = new SayHello();
		Person person = new Person();
		RemoteObjectRef ror = UnicastRemoteObject440.exportObject(person, 0);
		String host = InetAddress.getLocalHost().getHostName();
		Registry440 registry = LocateRegistry440.getRegistry(host, 1099);
		registry.rebind("Person" ,ror);
		System.out.println("Bind on registry");
	}
}
