package example;

import registry.LocateRegistry;
import registry.Registry;
import ror.RemoteObjectRef;

public class SayHelloTestClient {
	public static void main(String[] args) {
		Registry registry = LocateRegistry.getRegistry("128.237.217.119", 1099);
		String[] services = registry.list();
		System.out.println("Here are all the services: ");
		for (String str : services) {
			System.out.println(str);
		}
		System.out.println("Services are listed above");
		
		RemoteObjectRef<?> ror = null;
		try {
			ror = registry.lookup("SayHelloOnRegistr");
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		
		SayHelloInterface stub = null;
		try {
			stub = (SayHelloInterface) ror.localise();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String ret = stub.sayHello("nkn!");
		System.out.println(ret);
	}
}
