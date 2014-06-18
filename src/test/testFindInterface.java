package test;

import ror.UnicastRemoteObject440;
import example.SayHello;

public class testFindInterface {
	public static void main(String[] args) {
		System.out.println(UnicastRemoteObject440.parseRemoteInterfaceName(SayHello.class));
	}
}
