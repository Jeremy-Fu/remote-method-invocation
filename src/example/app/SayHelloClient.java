package example.app;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.SayHelloInterface;
import example2.Person;
import example2.PersonInterface;

public class SayHelloClient {
	public static void main(String[] args) throws Exception {
		Registry440 remoteRegistry = LocateRegistry440.getRegistry("128.237.217.119", 1099);
		SayHelloInterface sayHelloStub = (SayHelloInterface) remoteRegistry.lookup("ayHelloOnJeremyRegistry");
		
		PersonInterface person = new Person(); 
		Registry440 localRegistry = LocateRegistry440.getRegistry("localhost", 1099);
		PersonInterface personStub = (PersonInterface)UnicastRemoteObject440.exportObject(person, 0);
		
		String ret = sayHelloStub.sayHello(person);
		System.out.println(ret);
	}
}
