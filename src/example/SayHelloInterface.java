package example;

import ror.RemoteInterface;

public interface SayHelloInterface extends RemoteInterface{
	public String sayHello(String name);
}
