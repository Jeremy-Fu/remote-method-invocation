package example.app;

import registry.LocateRegistry440;
import registry.Registry440;
import ror.UnicastRemoteObject440;
import example.computePi.Compute;

public class ComputeEngine {
	public static void main(String[] args) {
		try {
            String name = "ComputeOnJeremyServer";
            Compute engine = new Compute();
            Compute stub =
                (Compute) UnicastRemoteObject440.exportObject(engine, 0);
            Registry440 registry = LocateRegistry440.getRegistry("localhost", 1099);
            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
	}
}
