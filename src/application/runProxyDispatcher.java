package application;

import util.ProxyDispatcher;

public class runProxyDispatcher {
	public static void main(String[] args) throws InterruptedException {
		String[] args2pd = new String[6];
		args2pd[0] = "example.SayHello";
		args2pd[1] = "128.237.217.119"; //Registry IP
		args2pd[2] = "1099"; //Registry port
		args2pd[3] = "SayHelloOnRegistry"; //Service Name;
		args2pd[4] = "SayHello-1"; //Object key
		args2pd[5] = "1025"; //Object port;

		ProxyDispatcher pd = new ProxyDispatcher(args2pd);
		Thread td = new Thread(pd);
		td.start();
	}
}
