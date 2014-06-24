package example.test1;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;

/**
 * test1: This pro
 * @author JeremyFu
 *
 */
public class test1Server {
	public static void main(String[] args) {
		Person person = new Person();
		PersonInterface personStub = null;
		try {
			personStub = (PersonInterface) UnicastRemoteObject440.exportObject(person, 0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (personStub == null) {
			System.err.println("Export remote object failed. Now exit the process.");
			System.exit(-1);
		}
		Registry440 registryStub = LocateRegistry440.getRegistry("localhost", 1099);
		registryStub.rebind("PersonOnServerRegistry", personStub);
		System.out.println("test1Server.main():\tPerson has been bound to registry");
	}
	
	
}
