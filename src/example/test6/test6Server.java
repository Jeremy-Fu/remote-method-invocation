package example.test6;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.sayhello.SayHello;
import example.sayhello.SayHelloInterface;

/**
 * test5: This program tests the functionality to send checked exception
 * back to client.
 * 
 * Server: It instantiates a SayHello object, exports it and rebinds it with
 * the registry. Due to the object is referenced the by proxy dispatcher 
 * thread, the person object won't be collected by garbage collection.
 * 
 * Client invokes resetPerson() along with null which results in 
 * NullPointerException.
 * 
 */
public class test6Server {
	public static void main(String[] args) {
		SayHello sayHello = new SayHello();
		SayHelloInterface sayHelloStub = null;
		try {
			sayHelloStub = (SayHelloInterface) UnicastRemoteObject440.exportObject(sayHello, 0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (sayHelloStub == null) {
			System.err.println("Export remote object failed. Now exit the process.");
			System.exit(-1);
		}
		Registry440 registryStub = LocateRegistry440.getRegistry("localhost", 1099);
		registryStub.rebind("SayHelloOnServerRegistry", sayHelloStub);
		System.out.println("test6Server.main():\tSayHello has been bound to registry");
	}
}
