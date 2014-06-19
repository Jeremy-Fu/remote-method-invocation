package test;

import java.net.UnknownHostException;

import ror.Remote440;
import ror.Stub440;
import util.Naming;
import util.RMIParamCheck;
import example2.Person;
import example2.PersonInterface;

public class testRMIparamcheck {
	public static void main(String[] args) {
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
		RMIParamCheck.paramCheck(arr);
		System.out.println(arr[0].getClass().getName());
		System.out.println(arr[1].getClass().getName());
	}
}
