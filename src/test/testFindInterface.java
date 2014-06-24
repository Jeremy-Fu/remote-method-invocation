package test;

import util.Naming;
import example.sayhello.SayHello;

public class testFindInterface {
	public static void main(String[] args) {
		System.out.println(Naming.parseRemoteInterfaceName(SayHello.class));
	}
}
