import java.io.Serializable;
import java.lang.reflect.Constructor;


public class RemoteObjectRef<T extends StubClass> implements Serializable{
	String ipAddr;
	int port;
	String objectKey;
	String remoteInterfaceName;
	
	public RemoteObjectRef(String ip, int portNum, 
			String key, String interfaceName) {
		ipAddr = ip;
		port = portNum;
		objectKey = key;
		remoteInterfaceName = interfaceName;
	}
	
	/**
	 * Get the stub of remote object
	 * 
	 * @return the client-side proxy of remote object(stub)
	 * @throws ClassNotFoundException
	 */
	T localise() throws ClassNotFoundException {
		Class<T> c = (Class<T>) Class.forName(remoteInterfaceName + "_Stub");
		Constructor<?> constructor = null;
		
		T stub = null;
		try {
			constructor = c.getConstructor(new Class[]{RemoteObjectRef.class});
			stub = (T)constructor.newInstance(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stub;
	}
}
