package example;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.RemoteObjectRef;
import example2.PersonInterface;

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
		Registry440 registry = LocateRegistry440.getRegistry("localhost", 1099);
		RemoteObjectRef<?> ror = null;
		try {
			ror = registry.lookup("Person");
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		PersonInterface stub = null;
		try {
			stub = (PersonInterface) ror.localise();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stub.setName("Andy");
		String str = stub.getName();
		System.out.println(str);
		
		Registry440 registry2 = LocateRegistry440.getRegistry("128.237.217.119", 1099);
		RemoteObjectRef<?> ror2 = null;
		try {
			ror2 = registry2.lookup("SayHelloOnRegistry");
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		SayHelloInterface sayHello = null;
		try {
			sayHello = (SayHelloInterface) ror2.localise();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String ret = sayHello.sayHello(stub);
		System.out.println(ret);
	}
}
