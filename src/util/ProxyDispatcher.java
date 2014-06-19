package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Random;

import message.InvokeMessage;
import message.RetMessage;
import ror.Remote440;


public class ProxyDispatcher implements Runnable{
	private long objectKeyCounter;
	private Hashtable<String, Object> rorTable;  //Object-Key -> RemoteObject
	private int dispatcherPort;
	
	public ProxyDispatcher (int port) {
		this.rorTable = new Hashtable<String, Object>();
		this.objectKeyCounter = (new Random()).nextLong();
		this.dispatcherPort = port;
	}
	
	@Override
	public void run () {
		try {
			ServerSocket serverSoc = new ServerSocket(dispatcherPort);
			while (true) {
				Socket socket = serverSoc.accept();
				System.out.println("ProxyDispatcher.run():\tReceive an invocation request.");
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
				/* Receive invocation request */
				InvokeMessage invokeReq = (InvokeMessage) in.readObject();
				
				/* Obtain the remote object to function */
				Object remoteObj = rorTable.get(invokeReq.getROR().getObjectKey());
				
				/* Obtain the method to be invoked */
				Class<?>[] argsType = invokeReq.getArgsType();
				Method method = remoteObj.getClass().getMethod(invokeReq.getMethodName(), argsType);
				System.out.println("ProxyDispatcher.run():\tInvoke " + remoteObj.getClass().getName() + "." + method.getName()+"()");
				System.out.print("\t\t\t@param: ");
				for (Class<?> eachType :  invokeReq.getArgsType()) {
					System.out.print(eachType.getName() + ", ");
				}
				System.out.println();
			
				/* Invoke method */
				Object[] args = invokeReq.getArgs();
				Object returnValue = method.invoke(remoteObj, args);
				//TODO: Handle exception as well.
				RetMessage retMsg = new RetMessage(returnValue, false);
				out.writeObject(retMsg);
			
				in.close();
				out.close();
				socket.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String genObjectKey() {
		String rst = String.format("%20d", this.objectKeyCounter);
		this.objectKeyCounter++;
		return rst;
	}
	
	public void addRemoteObject(String objectKey, Remote440 remoteObj) {
		this.rorTable.put(objectKey, remoteObj);
	}

}
