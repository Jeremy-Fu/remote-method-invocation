package example.app;

import java.math.BigDecimal;

import registry.LocateRegistry440;
import registry.Registry440;
import example.computePi.Compute;
import example.computePi.Pi;

public class Client {
	public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry440 registry = LocateRegistry440.getRegistry("128.237.220.250", 1099);
            Compute comp = (Compute) registry.lookup(name);
            Pi task = new Pi(Integer.parseInt(args[1]));
            BigDecimal pi = comp.executeTask(task);
            System.out.println(pi);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }    
}
