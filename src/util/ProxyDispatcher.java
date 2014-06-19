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
		ServerSocket serverSoc = null;
		try {
			serverSoc = new ServerSocket(dispatcherPort);
		} catch (IOException e) {
			//TODO: Throw a exception to UnicastRemoteObject
			e.printStackTrace();
		}
		while (true) {
			Socket socket;
			ObjectInputStream in;
			ObjectOutputStream out;
			
			try {
				socket = serverSoc.accept();
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
				
				/* Receive invocation request */
				InvokeMessage invokeReq = (InvokeMessage) in.readObject();
					
				/* Obtain the remote object to function */
				Object remoteObj = rorTable.get(invokeReq.getROR().getObjectKey());
					
				/* Obtain the method to be invoked */
				Class<?>[] argsType = invokeReq.getArgsType();
				Method method = remoteObj.getClass().getMethod(invokeReq.getMethodName(), argsType);
				System.out.println("ProxyDispatcher.run():\tInvoke " + remoteObj.getClass().getName() + "." + method.getName()+"()");
				
				/* Get objects from invoke message and check type */
				Object[] args = invokeReq.getArgs();
				RMIParamCheck.paramInvokeCheck(args);
				RetMessage retMsg = null;
				
				/* Invoke method */
				try {
					Object returnValue = method.invoke(remoteObj, args);
					/* Construct return value */
					retMsg = new RetMessage(returnValue);
				} catch (InvocationTargetException e) {
					Exception cause = new Exception(e.getCause());
					/* Construct thrown exception */
					retMsg = new RetMessage(cause, true);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}  catch (IllegalArgumentException e) {
					e.printStackTrace();
				} 
				out.writeObject(retMsg);
				in.close();
				out.close();
				socket.close();	
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
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
