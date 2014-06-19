package ror;

import java.net.UnknownHostException;

import util.ProxyDispatcher;

public class UnicastRemoteObject440 {
	private static ProxyDispatcher systemPd = null;
	
	public static Remote440 exportObject(Remote440 remoteObject,int argPort) throws UnknownHostException {
		if (Naming.isNamed(remoteObject)) {
			//TODO: throw exception
		}
		
		RemoteObjectRef ror = null;
		ProxyDispatcher pd = null;
		if (argPort == 0) {
			ror = Naming.name(remoteObject);
			if (systemPd == null) {
				systemPd = new ProxyDispatcher(ror.getPort());
				Thread td = new Thread(systemPd);
				td.start();
			}
			pd = systemPd;
		} else {
			ror = Naming.name(remoteObject, argPort);
			pd = new ProxyDispatcher(ror.getPort());
			Thread td = new Thread(pd);
			td.start();
		}
		
		return (Remote440) ror.localise();	
	}
}
