package ror;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * An object of this class is instantiated when a remote object is instantiated.
 * It saves the basic information of the remote object.
 *
 */
public class RemoteObjectRef implements Serializable{
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
	 public Stub440 localise() {
		 Class<? extends Stub440> c = null;
		 try {
			 c = (Class<? extends Stub440>) Class.forName(remoteInterfaceName + "_Stub").asSubclass(Stub440.class);
		 } catch (ClassNotFoundException e) {
			 //TODO: Download .class file from code base
			 e.printStackTrace();
			 System.exit(-1);
		 }
		
		Constructor<?> constructor = null;
		
		Stub440 stub = null;
		try {
			constructor = c.getConstructor(new Class[]{RemoteObjectRef.class});
			stub = (Stub440)constructor.newInstance(this);
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
