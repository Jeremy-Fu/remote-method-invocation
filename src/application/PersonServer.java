package application;

import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;
import exception.RemoteException440;

public class PersonServer {
	public static void main(String[] args) throws UnknownHostException {
		Person person = new Person();
		PersonInterface stub = (PersonInterface) UnicastRemoteObject440.exportObject(person, 0);
		Registry440 registry = null;
		try {
			registry = LocateRegistry440.getRegistry("localhost", 1099);
		} catch (RemoteException440 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		registry.rebind("Person", person);
		System.out.println("bind person");
	}
}
