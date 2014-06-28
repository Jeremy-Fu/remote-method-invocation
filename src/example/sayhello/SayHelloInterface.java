package example.sayhello;


import ror.Remote440;
import example.person.PersonInterface;

public interface SayHelloInterface extends Remote440{
	
	/**
	 * Given a remote object person, it gets the name of the person object
	 * and creates a string greeting the person. 
	 * 
	 * @param person A remote object (or its stub)
	 * @return String a string in the format 'Hi + personName'
	 * @throws Exception
	 */
	public String sayHello(PersonInterface person) throws Exception;
	
	/**
	 * Creates a person object, exports the object on the local host
	 * @return the newly created person object
	 */
	public PersonInterface createPerson();
	
	/**
	 * Resets the person's name to DEFAULT
	 * 
	 * @param person the person of which the name is about to reset to DEFAULT
	 * @return the peron's new name
	 */
	public String resetPerson(PersonInterface person);

}
