package test;

import java.io.NotSerializableException;
import java.net.UnknownHostException;

import util.Naming;
import util.RMIParamCheck;
import example.person.Person;
import example.person.PersonInterface;

public class testRMIparamcheck {
	public static void main(String[] args) throws NotSerializableException {
		Object[] arr = new Object[2];
		PersonInterface person = new Person();
		try {
			Naming.name(person);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arr[0] = person;
		arr[1] = new Integer(2);
		System.out.println(arr[0].getClass().getName());
		System.out.println(arr[1].getClass().getName());
		RMIParamCheck.paramSendCheck(arr);
		System.out.println(arr[0].getClass().getName());
		System.out.println(arr[1].getClass().getName());
	}
}
