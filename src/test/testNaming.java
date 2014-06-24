package test;

import registry.LocateRegistry440;
import registry.Registry440;
import example.person.PersonInterface;
import example.sayhello.SayHelloInterface;

public class testNaming {
	public static void main(String[] args) throws Exception {
		Registry440 registry = LocateRegistry440.getRegistry("128.237.220.250", 1099);
		SayHelloInterface sayHello_stub = null;
		try {
			sayHello_stub = (SayHelloInterface) registry.lookup("SayHelloOnJeremyRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PersonInterface person_stub = null;
		person_stub = (PersonInterface) sayHello_stub.createPerson();
		person_stub.setName("Kimmmm");
		String name = person_stub.getName();
		//System.out.println(name);
		System.out.println(sayHello_stub.sayHello(person_stub));
	}
}
