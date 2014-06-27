package example.sayhello;

import java.net.UnknownHostException;

import ror.UnicastRemoteObject440;
import example.person.Person;
import example.person.PersonInterface;

public class SayHello implements SayHelloInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 649136614591494580L;

	@Override
	public String sayHello(PersonInterface person) throws Exception {
		//param person is an instance of PersonInterface_Stub
		if (person.getName().equals("Kim")) {
			throw new Exception("Found Kim.");
		}
		System.out.println("Sleep...");
		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("wake up...");
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
		PersonInterface personStub = person;
		try {
			personStub = (PersonInterface) UnicastRemoteObject440.exportObject(person, 0);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
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
