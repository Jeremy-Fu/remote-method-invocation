package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import message.InvokeMessage;
import message.RetMessage;
import ror.RemoteInterface;
import ror.RemoteObjectRef;
import ror.RemoteObjectRefTable;


public class ProxyDispatcher implements Runnable{
	
	private RemoteObjectRefTable RORtbl;
	private String initClassName;
	private String registryHost;
	private int registryPort;
	private String serviceName;
	private String objectKey;
	
	public ProxyDispatcher (String[] args) {
		this.RORtbl = new RemoteObjectRefTable();
		this.initClassName = args[0];
		this.registryHost = args[1];
		this.registryPort = Integer.parseInt(args[2]);
		this.serviceName = args[3];
		this.objectKey = args[4];
	}
	
	@Override
	public void run () {
		Class<?> initClass = null;
		try {
			initClass = Class.forName(initClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Object obj = null;
		try {
			obj = initClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		String hostInetAddr = null;
		try {
			hostInetAddr = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(hostInetAddr);
		int hostPort = 1025;		
		RemoteObjectRef ror = new RemoteObjectRef(hostInetAddr, hostPort, objectKey, parseRemoteInterfaceName(initClass));
		//TODO: Post ror to registry
		this.RORtbl.addObject(objectKey, obj);
		
		// create a socket.
		try {
			ServerSocket serverSoc = new ServerSocket(hostPort);
			Socket socket = serverSoc.accept();
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
			/* Receive invocation request */
			InvokeMessage invokeReq = (InvokeMessage) in.readObject();
			
			/* Obtain the method to be invoked */
			Class<?>[] argsType = invokeReq.getArgsType();
			Object remoteObj = RORtbl.getObject(invokeReq.getROR().getObjectKey());
			Method method = remoteObj.getClass().getMethod(invokeReq.getMethodName(), argsType);
			
			
			/* Invoke method */
			Object[] args = invokeReq.getArgs();
			Object returnValue = method.invoke(remoteObj, args);
			//TODO: Handle exception as well.
			RetMessage retMsg = new RetMessage(returnValue, false);
			out.writeObject(retMsg);
			
			in.close();
			out.close();
			socket.close();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
	
	public static String parseRemoteInterfaceName (Class<?> initClass) {
		Class<?>[] interfaces = initClass.getInterfaces();
		Class remoteInterfaceName = null;
		boolean breakFlag = false;
		for (int i = 0; i < interfaces.length; i++) {
			Class<?>[] nestedInterfaces = interfaces[i].getInterfaces();
			for (int j = 0; j < nestedInterfaces.length; j++) {
				if (nestedInterfaces[j] == RemoteInterface.class) {
					remoteInterfaceName = interfaces[i];
					breakFlag = true;
					break;
				}
			}
			if (breakFlag) {
				break;
			}
		}
		return remoteInterfaceName.getName();
	}
	

}
