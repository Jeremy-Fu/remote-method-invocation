
package test;

import example.person.PersonInterface;
import exception.RemoteException440;
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
		String serviceName = null;
		String serverIp = null;
		int serverPort  = 0;
		if (args.length != 3) {
			System.out.println("Usage: java example.test2.test2Client <ServiceName> <ServerIp> <ServerPort>");
			return;
		} else {
			serviceName = args[0];
			serverIp = args[1];
			serverPort = Integer.parseInt(args[2]);
		}
		
		Registry440 registry = null;
		try {
			registry = LocateRegistry440.getRegistry(serverIp, serverPort);
		} catch (RemoteException440 e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PersonInterface person = null;
		
		/* Check the class type of the person object */
		System.out.print("Is person a stub?      ");
		System.out.println(person instanceof Stub440);
		try {
			person = (PersonInterface) registry.lookup(serviceName);
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
