package example.app;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;
import example.sayhello.SayHelloInterface;

public class SayHelloClient {
	public static void main(String[] args) throws Exception {
		Registry440 remoteRegistry = LocateRegistry440.getRegistry("128.237.217.119", 1099);
		SayHelloInterface sayHelloStub = (SayHelloInterface) remoteRegistry.lookup("SayHelloOnJeremyRegistry");
		
		PersonInterface person = new Person(); 
		person.setName("oh no");
		Registry440 localRegistry = LocateRegistry440.getRegistry("localhost", 1099);
		PersonInterface personStub = (PersonInterface)UnicastRemoteObject440.exportObject(person, 0);
		
		String ret = sayHelloStub.sayHello(personStub);
		System.out.println(personStub.getName());
		System.out.println(ret);
	}
}
