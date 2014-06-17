package application;

import registry.RegistryServer;

public class runRegistry {
	public static void main(String[] args) {
		RegistryServer rs = new RegistryServer();
		Thread td = new Thread(rs);
		td.start();
	}
}
