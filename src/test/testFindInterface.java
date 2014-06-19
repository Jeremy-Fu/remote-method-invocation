package test;

import util.Naming;
import example.SayHello;

public class testFindInterface {
	public static void main(String[] args) {
		System.out.println(Naming.parseRemoteInterfaceName(SayHello.class));
	}
}
