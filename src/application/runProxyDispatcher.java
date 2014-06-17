package application;

import util.ProxyDispatcher;

public class runProxyDispatcher {
	public static void main(String[] args) throws InterruptedException {
		String[] args2pd = new String[5];
		args2pd[0] = "SayHello";
		args2pd[1] = "128.237.217.119";
		args2pd[2] = "1025";
		args2pd[3] = "SayHelloOnRegistry";
		args2pd[4] = "SayHello-1";

		ProxyDispatcher pd = new ProxyDispatcher(args2pd);
		Thread td = new Thread(pd);
		td.start();
	}
}
