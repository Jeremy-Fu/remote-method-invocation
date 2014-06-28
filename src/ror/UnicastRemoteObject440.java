package ror;

import java.net.UnknownHostException;

import util.Naming;
import util.ProxyDispatcher;
import exception.PortUsedException440;
import exception.RemoteException440;

public class UnicastRemoteObject440{
	private static ProxyDispatcher systemPd = null;
	
	public static Remote440 exportObject(Remote440 remoteObject,int argPort) throws UnknownHostException, RemoteException440 {
		if (Naming.isNamed(remoteObject)) {
			throw new RemoteException440("Object has already been exported");
		}
		
		RemoteObjectRef ror = null;
		ProxyDispatcher pd = null;
		if (argPort == 0) {
			ror = Naming.name(remoteObject);
			if (systemPd == null) {
				try {
					systemPd = new ProxyDispatcher(ror.getPort());
				} catch (PortUsedException440 e) {
					int newPort = Naming.refreshPort();
					ror.setPort(newPort);
					systemPd = new ProxyDispatcher(ror.getPort());
				}
				Thread td = new Thread(systemPd);
				td.start();
			}
			pd = systemPd;
		} else {
			ror = Naming.name(remoteObject, argPort);
			try {
				pd = new ProxyDispatcher(ror.getPort());
			} catch (PortUsedException440 e) {
				e.printStackTrace();
				System.err.println("The port has been used");
				System.exit(-1);
			}
			Thread td = new Thread(pd);
			td.start();
		}
		
		pd.addRemoteObject(ror.getObjectKey(), remoteObject);
		return (Remote440) ror.localise();	
	}
}
