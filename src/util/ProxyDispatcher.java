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
import exception.PortUsedException440;


public class ProxyDispatcher implements Runnable{
	private long objectKeyCounter;
	private Hashtable<String, Object> rorTable;  //Object-Key -> RemoteObject
	private int dispatcherPort;
	private final boolean DEBUG = false;
	private ServerSocket serverSoc = null;
	
	public ProxyDispatcher (int port)  throws PortUsedException440 {
		this.rorTable = new Hashtable<String, Object>();
		this.objectKeyCounter = (new Random()).nextLong();
		this.dispatcherPort = port;
		try {
			this.serverSoc = new ServerSocket(dispatcherPort);
		} catch (IOException e) {
			throw new PortUsedException440("port has been used");
		}
	}
	
	
	@Override
	public void run () {

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
				if (DEBUG) {
					System.out.println("ProxyDispatcher.run():\tInvoke " + remoteObj.getClass().getName() + "." + method.getName()+"()");
				}
				
				/* Get objects from invoke message and check type */
				Object[] args = invokeReq.getArgs();
				RMIParamCheck.paramInvokeCheck(args);
				RetMessage retMsg = null;
				
				/* Invoke method */
				try {
					/* Check the return object, return associate stub if it is an exported remote object */
					Object returnValue = RMIParamCheck.retCheck(method.invoke(remoteObj, args));
					/* Construct return value */
					retMsg = new RetMessage(returnValue);
				} catch (InvocationTargetException e) {
					Exception cause = (Exception) e.getCause();
					if (cause instanceof RuntimeException) {
						retMsg = new RetMessage(cause, true);
					} else {
						retMsg = new RetMessage(cause, false);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (RuntimeException e) {
					e.printStackTrace();
					Exception cause = new Exception(e.getCause());
					/* Construct thrown exception */
					retMsg = new RetMessage(cause, true);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} finally {
					out.writeObject(retMsg);
				}
				
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
