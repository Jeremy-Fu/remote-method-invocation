
package example.test2;

import example.person.PersonInterface;
import registry.LocateRegistry440;
import registry.Registry440;
import ror.Stub440;

/**
 * test2: This program tests when parameter and return value of remote method
 * are both non-remote serializable object. 
 * 
 * Client: It looks up the stub of a person object named 'PersonOnServerRegistry',
 * sets the NAME of the remote object to the integer passed in and finally gets 
 * the NAME of the remote object again after the NAME is set. Both of the setter 
 * and getter method of the instance variable NAME are remote method invocations.
 * 
 */
public class test2Client {
	public static void main(String[] args) {
		Registry440 registry = LocateRegistry440.getRegistry("128.237.220.250", 1099);
		PersonInterface person = null;
		
		/* Check the class type of the person object */
		System.out.print("Is person a stub?      ");
		System.out.println(person instanceof Stub440);
		try {
			person = (PersonInterface) registry.lookup("PersonOnServerRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* Check the class type of the person object after we get the stub */
		System.out.print("Is person a stub now?  ");
		System.out.println(person instanceof Stub440);
		
		person.setName("Jeremy");
		String name = person.getName();
		System.out.println("The person's name now is: " + name);
	}
	
}
