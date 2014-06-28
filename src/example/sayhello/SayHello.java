package example.sayhello;

import java.net.UnknownHostException;

import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;
import exception.RemoteException440;

public class SayHello implements SayHelloInterface{

	private static final long serialVersionUID = 649136614591494580L;

	@Override
	public String sayHello(PersonInterface person) throws Exception {
		/* param person is an instance of PersonInterface_Stub */
		if (person.getName().equals("Kim")) {
			throw new Exception("Found Kim.");
		}
		String rst = "Hello, " + person.getName();
		return rst;
	}
	
	/* Test method */
	public void beHappy(String name) {
		String rst = name + " should be happy!";
		System.out.println(rst);
		return;
	}

	@Override
	public PersonInterface createPerson() {
		PersonInterface person = new Person();
		person.setName("Jeremy");

		try {
			UnicastRemoteObject440.exportObject(person, 0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (RemoteException440 e) {
			e.printStackTrace();
		}
		
		return person;
	}

	@Override
	public String resetPerson(PersonInterface person) {
		person.setName("DEFAULT");
		return person.getName();
	}

}
