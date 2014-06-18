package ror;

import java.io.Serializable;
import java.lang.reflect.Constructor;



public class RemoteObjectRef<T extends Stub440> implements Serializable{
	private static final long serialVersionUID = -3426999726929419480L;
	private String ipAddr;
	private int port;
	private String objectKey;
	private String remoteInterfaceName;
	
	public RemoteObjectRef(String ip, int portNum, 
			String key, String interfaceName) {
		ipAddr = ip;
		port = portNum;
		objectKey = key;
		remoteInterfaceName = interfaceName;
	}
	
	/**
	 * Construct the stub of the remote object
	 * 
	 * @return the client-side proxy of remote object(stub)
	 * @throws ClassNotFoundException
	 */
	 public T localise() throws ClassNotFoundException {
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
	
	public String getIP() {
		return this.ipAddr;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public String getObjectKey() {
		return this.objectKey;
	}
}
