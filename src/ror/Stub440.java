package ror;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import util.RMIParamCheck;
import message.InvokeMessage;
import message.MessageCode;
import message.RetMessage;

public abstract class Stub440 implements Serializable, Remote440 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1552360315883773048L;
	public RemoteObjectRef ror;
	
	public Stub440(RemoteObjectRef ref) {
		this.ror = ref;
	}
	
	/**
	 * Create a Method Invocation Message according to method name and its
	 * arguments array, send message to proxy dispatcher and get reply
	 * 
	 * @param methodName 
	 * @param args array of method's parameters
	 * @return the return value of the method to invoke, or exception if it is
	 * 		   thrown
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	protected Object invokeMethod(String methodName, Object[] args, Class<?>[] argsType) 
					throws UnknownHostException, IOException {
		InvokeMessage message = new InvokeMessage(ror, methodName, args, argsType);
		
		String inetAddr = ror.getIP();
		int port = ror.getPort();
		System.out.println("DEBUG:\tStub440.invokeMethod():\tdstIP=" + inetAddr + ":" + port);
		Socket clientToServer = new Socket(inetAddr, port);
		System.out.println("DEBUG:\tStub440.invokeMethod():\tSetup an connection...");
		/* Send method invocation message to proxy dispatcher */
		ObjectOutputStream objectOut = new ObjectOutputStream(clientToServer.getOutputStream());
		/* Check and replace exported remote object with its stub */
		RMIParamCheck.paramSendCheck(message.getArgs());	
		objectOut.writeObject(message);
		System.out.println("DEBUG:\tStub440.invokeMethod():\tWrite invoke message...");
		/* read return message from proxy dispatcher */
		ObjectInputStream objectIn = new ObjectInputStream(clientToServer.getInputStream());
		System.out.println("DEBUG:\tStub440.invokeMethod():\tRead return message...");
		RetMessage retMessage = null;
		try {
			retMessage = (RetMessage) objectIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		objectOut.close();
		objectIn.close();
		clientToServer.close();
		
		//TODO check if exception flag is set in retMessage
		if (retMessage.getCode() == MessageCode.EXCEPTION) {
			
		} else {
			
		}
		
		return retMessage.getRet();
	}
	
}
