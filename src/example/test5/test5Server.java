package example.test5;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.sayhello.SayHello;
import example.sayhello.SayHelloInterface;
import exception.RemoteException440;

/**
 * test5: This program tests the functionality to send checked exception
 * back to client.
 * 
 * Server: It instantiates a SayHello object, exports it and rebinds it with
 * the registry. Due to the object is referenced the by proxy dispatcher 
 * thread, the person object won't be collected by garbage collection.
 * 
 * Client creates a person object on server side, sets name to "Kim" and
 * finally calls the sayHello() method which is implemented to throw an
 * exception on purpose if the person object name is "Kim".
 * 
 *
 */
public class test5Server {
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
		Registry440 registryStub = null;
		try {
			registryStub = LocateRegistry440.getRegistry("localhost", 1099);
		} catch (RemoteException440 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		registryStub.rebind("SayHelloOnServerRegistry", sayHelloStub);
		System.out.println("test5Server.main():\tSayHello has been bound to registry");
	}
}
