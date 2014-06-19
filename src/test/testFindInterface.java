package test;

import ror.Naming;
import ror.UnicastRemoteObject440;
import example.SayHello;

public class testFindInterface {
	public static void main(String[] args) {
		System.out.println(Naming.parseRemoteInterfaceName(SayHello.class));
	}
}
