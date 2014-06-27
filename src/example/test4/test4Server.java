package example.test4;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.sayhello.SayHello;
import example.sayhello.SayHelloInterface;
import exception.RemoteException440;

/**
 * test4: This program tests when parameter and return value of remote method
 * are void and exported remote objects respectively.
 * 
 * Server: It instantiates a SayHello object, exports it and rebinds it with
 * the registry. Due to the object is referenced the by proxy dispatcher 
 * thread, the person object won't be collected by garbage collection.
 * 
 * Client instantiates a Person remote object. Client could pass both unexported 
 * and exported remote objects as parameters in resetPerson() method 
 * respectively. The non-exported object will be passed by value as the change
 * on remote host is not seen by local host. 
 *
 */
public class test4Server {
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
		System.out.println("test4Server.main():\tSayHello has been bound to registry");
	}
}
