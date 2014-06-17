package example;

import ror.RemoteInterface;

public interface RemoteSayHello extends RemoteInterface{
	public String sayHello(String name);
}
