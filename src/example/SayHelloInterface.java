package example;

import example2.Person;
import example2.PersonInterface;
import ror.RemoteInterface;

public interface SayHelloInterface extends RemoteInterface{
	public String sayHello(Person person);
}
