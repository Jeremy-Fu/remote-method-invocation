package test;

import example.SayHelloInterface;
import example2.PersonInterface;
import registry.LocateRegistry440;
import registry.Registry440;

public class testNaming {
	public static void main(String[] args) {
		Registry440 registry = LocateRegistry440.getRegistry("128.237.217.119", 1099);
		SayHelloInterface sayHello_stub = null;
		try {
			sayHello_stub = (SayHelloInterface) registry.lookup("SayHelloOnJeremyRegistry");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PersonInterface person_stub = null;
		person_stub = (PersonInterface) sayHello_stub.createPerson();
		System.out.println(person_stub.getName());
	}
}
