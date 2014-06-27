package ror;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import exception.RemoteException440;
import exception.ServerException440;
import util.RMIParamCheck;
import message.InvokeMessage;
import message.MessageCode;
import message.RetMessage;

public abstract class Stub440 implements Serializable, Remote440 {

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
	 * @throws Exception 
	 */
	protected Object invokeMethod(String methodName, Object[] args, Class<?>[] argsType) 
					throws UnknownHostException, IOException, RemoteException440, Exception {
		InvokeMessage message = new InvokeMessage(ror, methodName, args, argsType);
		
		String inetAddr = ror.getIP();
		int port = ror.getPort();
		
		Socket clientToServer = new Socket(inetAddr, port);
		
		/* Send method invocation message to proxy dispatcher */
		ObjectOutputStream objectOut = new ObjectOutputStream(clientToServer.getOutputStream());
		/* Check and replace exported remote object with its stub */
		RMIParamCheck.paramSendCheck(message.getArgs());		
		try{	
			objectOut.writeObject(message);
		} catch (IOException e) {	//an error accessing the socket
			throw new ServerException440("An error occurred while sending remote message");
		}
		/* read return message from proxy dispatcher */
		ObjectInputStream objectIn = new ObjectInputStream(clientToServer.getInputStream());
		RetMessage retMessage = null;
		try {
			retMessage = (RetMessage) objectIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new ServerException440("An error occurred on server side");
		}
		objectOut.close();
		objectIn.close();
		clientToServer.close();
		
		/* both checked and unchecked exception in RMI are thrown here */
		if (retMessage.getCode() == MessageCode.EXCEPTION) {
			Exception e = ((RetMessage)retMessage).getException();
			throw e;	
		} 
		
		return retMessage.getRet();
	}
	
}
