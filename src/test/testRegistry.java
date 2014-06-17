package test;



import registry.LocateRegistry;
import registry.Registry;
import registry.RegistryServer;
import util.ProxyDispatcher;

public class testRegistry {
	public static void main(String[] args) {
		
		System.out.println("Start the registry server...");
		RegistryServer rs = new RegistryServer();
		Thread td = new Thread(rs);
		td.start();
		System.out.println("testRegistry.main():\tSleep 3s...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Start the proxy...");
		String[] args2pd = new String[5];
		args2pd[0] = "example.SayHello";
		args2pd[1] = "128.237.217.119";
		args2pd[2] = "1025";
		args2pd[3] = "SayHelloOnRegistry"; //Service name
		args2pd[4] = "SayHello-1"; //Object key

		ProxyDispatcher pd = new ProxyDispatcher(args2pd);
		Thread td2 = new Thread(pd);
		td2.start();
		System.out.println("testRegistry.main():\tSleep 3s...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Registry registry = LocateRegistry.getRegistry("128.237.217.119", 1099);
		String[] services = registry.list();
		int i = 0;
		for (String service : services) {
			i++;
			System.out.println("service " + i + ":\t" + service);
		}
		
		
		
		
		
	}
}
