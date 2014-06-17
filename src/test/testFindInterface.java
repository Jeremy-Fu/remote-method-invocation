package test;

import util.ProxyDispatcher;
import example.SayHello;

public class testFindInterface {
	public static void main(String[] args) {
		System.out.println(ProxyDispatcher.parseRemoteInterfaceName(SayHello.class));
	}
}
