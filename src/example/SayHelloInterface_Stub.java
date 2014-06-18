package example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import example2.Person;
import example2.PersonInterface;
import message.InvokeMessage;
import message.MessageCode;
import message.RetMessage;
import ror.RemoteObjectRef;
import ror.Stub440;

public class SayHelloInterface_Stub extends Stub440 implements SayHelloInterface{

	public SayHelloInterface_Stub(RemoteObjectRef<?> ref) {
		super(ref);
	}

	public String sayHello(Person person) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		/* save all arguments into an array */
		Object[] args = new Object[]{person};
		String ret = null;
		try {
			ret = (String)invokeMethod(methodName, args);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
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
	private Object invokeMethod(String methodName, Object[] args) 
					throws UnknownHostException, IOException {
		InvokeMessage message = new InvokeMessage(ror, methodName, args);
		
		String inetAddr = ror.getIP();
		int port = ror.getPort();
		Socket clientToServer = new Socket(inetAddr, port);
		
		/* Send method invocation message to proxy dispatcher */
		ObjectOutputStream objectOut = new ObjectOutputStream(clientToServer.getOutputStream());
		objectOut.writeObject(message);
		
		/* read return message from proxy dispatcher */
		ObjectInputStream objectIn = new ObjectInputStream(clientToServer.getInputStream());
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
