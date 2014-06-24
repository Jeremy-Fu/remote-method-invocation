package example.sayhello;


import ror.Remote440;
import example.person.PersonInterface;

public interface SayHelloInterface extends Remote440{
	
	public String sayHello(PersonInterface person) throws Exception;
	
	public PersonInterface createPerson();

}
