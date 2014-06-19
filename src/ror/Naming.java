package ror;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Random;

public class Naming {
	private static long objectKeyCounter;
	private static int port = (new Random()).nextInt(65535 - 1024) + 1024;
	private static Hashtable<Remote440, RemoteObjectRef> obj2RefTbl = 
			new Hashtable<Remote440, RemoteObjectRef>();
	
	public static RemoteObjectRef name(Remote440 remoteObject, int argPort) throws UnknownHostException {
		
		String objectKey = genObjectKey();
		String hostInetAddr = InetAddress.getLocalHost().getHostName();
		RemoteObjectRef ror = new RemoteObjectRef(hostInetAddr, argPort, objectKey, parseRemoteInterfaceName(remoteObject.getClass()));
		obj2RefTbl.put(remoteObject, ror);
		return ror;
	}
	
	public static RemoteObjectRef name(Remote440 remoteObject) throws UnknownHostException {
		
		String objectKey = genObjectKey();
		String hostInetAddr = InetAddress.getLocalHost().getHostName();
		RemoteObjectRef ror = new RemoteObjectRef(hostInetAddr, port, objectKey, parseRemoteInterfaceName(remoteObject.getClass()));
		obj2RefTbl.put(remoteObject, ror);
		return ror;
	}
	
	public static String parseRemoteInterfaceName (Class<?> initClass) {
		Class<?>[] interfaces = initClass.getInterfaces();
		Class<?> remoteInterfaceName = null;
		boolean breakFlag = false;
		for (int i = 0; i < interfaces.length; i++) {
			Class<?>[] nestedInterfaces = interfaces[i].getInterfaces();
			for (int j = 0; j < nestedInterfaces.length; j++) {
				if (nestedInterfaces[j] == Remote440.class) {
					remoteInterfaceName = interfaces[i];
					breakFlag = true;
					break;
				}
			}
			if (breakFlag) {
				break;
			}
		}
		return remoteInterfaceName.getName();
	}
	
	public static String genObjectKey() {
		String rst = String.format("%20d", objectKeyCounter);
		objectKeyCounter++;
		return rst;
	}
	
	public static boolean isNamed(Remote440 remoteObject) {
		return obj2RefTbl.containsKey(remoteObject);
	}
}
