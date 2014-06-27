package example.app;

import registry.LocateRegistry440;
import registry.Registry440;
import example.person.PersonInterface;
import example.sayhello.SayHelloInterface;
import exception.RemoteException440;

public class SayHelloTestClient {
	public static void main(String[] args) {
//		Registry440 registry = LocateRegistry440.getRegistry("128.237.217.119", 1099);
//		String[] services = registry.list();
//		System.out.println("Here are all the services: ");
//		for (String str : services) {
//			System.out.println(str);
//		}
//		System.out.println("Services are listed above");
//		
//		RemoteObjectRef<?> ror = null;
//		try {
//			ror = registry.lookup("SayHelloOnRegistry");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			return;
//		}
//		
//		SayHelloInterface stub = null;
//		try {
//			stub = (SayHelloInterface) ror.localise();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		Person person = new Person();
//		person.setName("440");
//		String ret = stub.sayHello(person);
//		System.out.println(ret);
		Registry440 registry = null;
		try {
			registry = LocateRegistry440.getRegistry("localhost", 1099);
		} catch (RemoteException440 e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PersonInterface personStub = null;
		try {
			personStub = (PersonInterface) registry.lookup("Person");
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
	
		personStub.setName("Andy");
		String str = personStub.getName();
		System.out.println(str);
		
		Registry440 registry2 = null;
		try {
			registry2 = LocateRegistry440.getRegistry("128.237.217.119", 1099);
		} catch (RemoteException440 e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		SayHelloInterface sayHelloStub = null;
		try {
			sayHelloStub = (SayHelloInterface) registry2.lookup("SayHelloOnRegistry");
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		try {
			String ret = sayHelloStub.sayHello(personStub);
			System.out.println(ret);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
