
package example.test1;

import example.person.PersonInterface;
import registry.LocateRegistry440;
import registry.Registry440;
import ror.Stub440;

/**
 * test1: This program tests the case when parameter and return value of 
 * remote method are both primitive types. It looks up the stub of a person
 * object named 'PersonOnServerRegistry', sets the AGE of the remote object
 * to the integer passed in and finally gets the age of the remote
 * object again after the age is set. Both of the setter and getter method of
 * the instance variable AGE are remote method invocations
 * 
 */
public class test1Client {
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
		
		person.setAge(22);
		int age = person.getAge();
		System.out.println("The person's age now is: " + age);
	}
	
}
