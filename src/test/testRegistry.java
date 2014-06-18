package test;



import registry.RegistryServer;

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

		//TODO: FINISH TEST
		
		
		
		
		
	}
}
