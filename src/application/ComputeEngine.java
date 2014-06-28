package application;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.compute.Compute;
import example.compute.ComputeInterface;

/**
 * A ComputeEngine serves as a server to accepts remote task request, by default
 * it exports and rebinds a Compute service and waits for remote call
 *
 * Usage: java RMI440 application.ComputeEngine <service name>
 */
public class ComputeEngine {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: java RMI440 application.ComputeEngine <service name>");
			return;
		}
		try {
            String name = args[0];
            ComputeInterface engine = new Compute();
            ComputeInterface stub =
                (ComputeInterface) UnicastRemoteObject440.exportObject(engine, 0);
            Registry440 registry = LocateRegistry440.getRegistry("localhost", 1099);
            registry.rebind(name, stub);
            System.out.println(name + " bound");
        } catch (Exception e) {
            System.err.println("Check the correctness of registry.");
            e.printStackTrace();
        }
	}
}
