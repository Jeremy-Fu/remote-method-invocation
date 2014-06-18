package ror;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import util.ProxyDispatcher;

public class UnicastRemoteObject440 {
	private static int port = (new Random()).nextInt(65535 - 1024) + 1024;
	private static ProxyDispatcher systemPd = null;
	
	public static RemoteObjectRef exportObject(Remote440 remoteObject,int argPort) throws UnknownHostException {
		String hostInetAddr = InetAddress.getLocalHost().getHostName();
		int listenPort;
		ProxyDispatcher pd = null;
		if (argPort == 0) {
			listenPort = port;
			if (systemPd == null) {
				systemPd = new ProxyDispatcher(listenPort);
				Thread td = new Thread(systemPd);
				td.start();
			}
			pd = systemPd;
		} else {
			listenPort = argPort;
			pd = new ProxyDispatcher(listenPort);
			Thread td = new Thread(pd);
			td.start();
		}
		
		String objectKey = pd.genObjectKey();
		RemoteObjectRef<?> ror = new RemoteObjectRef(hostInetAddr, listenPort, objectKey, parseRemoteInterfaceName(remoteObject.getClass()));
		pd.addRemoteObject(objectKey, remoteObject);
		return ror;
		
	}
	
	public static String parseRemoteInterfaceName (Class<?> initClass) {
		Class<?>[] interfaces = initClass.getInterfaces();
		Class remoteInterfaceName = null;
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
}
