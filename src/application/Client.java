package application;

import java.math.BigDecimal;

import registry.LocateRegistry440;
import registry.Registry440;
import example.computePi.ComputeInterface;
import example.computePi.Pi;

public class Client {
	public static void main(String args[]) {
		int precision = 1;
		String serviceName = null;
		String serverIp = null;
		int serverPort  = 0;
		if (args.length != 4) {
			System.out.println("Usage: java example.app.Client <DecimalPrecision> <ServiceName> <ServerIp> <ServerPort>");
			return;
		} else {
			precision = Integer.parseInt(args[0]);
			serviceName = args[1];
			serverIp = args[2];
			serverPort = Integer.parseInt(args[3]);
		}
		
        try {
            Registry440 registry = LocateRegistry440.getRegistry(serverIp, serverPort);
            ComputeInterface comp = (ComputeInterface) registry.lookup(serviceName);
            Pi task = new Pi(precision);
            BigDecimal pi = comp.executeTask(task);
            System.out.println(pi);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }    
}
