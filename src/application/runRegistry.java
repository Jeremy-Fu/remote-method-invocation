package application;

import registry.RegistryServer;

/**
 * run a RMIregistry
 */
public class runRegistry {
	public static void main(String[] args) {
		RegistryServer rs = new RegistryServer();
		Thread td = new Thread(rs);
		td.start();
	}
}
