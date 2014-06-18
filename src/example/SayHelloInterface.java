package example;


import example2.PersonInterface;
import ror.Remote440;

public interface SayHelloInterface extends Remote440{
	public String sayHello(PersonInterface person);
	public PersonInterface createPerson();

}
