package application;

import registry.RegistryServer;

/**
 * run a RMIregistry
 */
public class runRegistry {
	public static void main(String[] args) {
		int registryPort = 1099;
		if (args.length > 0) {
			try {
				registryPort = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				registryPort = 1099;
			}
		}
		RegistryServer rs = new RegistryServer(registryPort);
		Thread td = new Thread(rs);
		td.start();
		System.out.println("RMIregistry is running on port:" + registryPort);
	}
}
