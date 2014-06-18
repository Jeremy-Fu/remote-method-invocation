package example;

import java.net.InetAddress;
import java.net.UnknownHostException;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.RemoteObjectRef;
import ror.UnicastRemoteObject440;
import example2.Person;
import example2.PersonInterface;

public class SayHello implements SayHelloInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 649136614591494580L;

	@Override
	public String sayHello(PersonInterface person) {
		//param person is an instance of PersonInterface_Stub
		String rst = "Hi, " + person.getName() ;
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
		Person person = new Person();
		RemoteObjectRef ror = null;
		try {
			ror = UnicastRemoteObject440.exportObject(person, 0);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String host = null;
		try {
			host = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Registry440 registry = LocateRegistry440.getRegistry(host, 1099);
		registry.rebind("PersonOnJeremyRegistry" ,ror);
		System.out.println("Bind person on Jeremy registry");
		PersonInterface rst = null;
		try {
			rst = (PersonInterface) ror.localise();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rst;
	}

}
