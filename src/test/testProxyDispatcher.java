package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import message.InvokeMessage;
import message.RetMessage;
import ror.RemoteObjectRef;
import util.ProxyDispatcher;


public class testProxyDispatcher {
	public static void main(String[] args) throws InterruptedException {
		String[] args2pd = new String[5];
		args2pd[0] = "SayHello";
		args2pd[1] = "127.0.0.1";
		args2pd[2] = "1025";
		args2pd[3] = "SayHelloOnRegistry";
		args2pd[4] = "SayHello-1";

		ProxyDispatcher pd = new ProxyDispatcher(args2pd);
		Thread td = new Thread(pd);
		td.start();
		Thread.sleep(5000);
		try {
			Socket soc = new Socket(args2pd[1], Integer.parseInt(args2pd[2]));
			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
			RemoteObjectRef ror = new RemoteObjectRef(args2pd[1], Integer.parseInt(args2pd[2]), args2pd[4], "RemoteSayHello");
			String[] methodArgs = new String[]{"GENG FU"};
			InvokeMessage invokeMsg = new InvokeMessage(ror, "sayHello", methodArgs);
			out.writeObject(invokeMsg);
			RetMessage retMsg = (RetMessage)in.readObject();
			Object retValue = retMsg.getRet();
			System.out.println("retValue type: " + retValue.getClass().getName());
			System.out.println("retValue: " + retValue.toString());
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
