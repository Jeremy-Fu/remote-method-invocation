import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class SayHello_Stub implements RemoteSayHello{
	private RemoteObjectRef ror;
	
	public SayHello_Stub(RemoteObjectRef remoteObject) {
		ror = remoteObject;
	}
	
	public String sayHello(String name) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		/* save all arguments into an array */
		Object[] args = new Object[]{name};
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

	private Object invokeMethod(String methodName, Object[] args) throws UnknownHostException, IOException {
		InvokeMessage message = new InvokeMessage(ror, methodName, args);
		
		String inetAddr = ror.getIP();
		int port = ror.getPort();
		Socket clientToServer = new Socket(inetAddr, port);
		
		/* Send method invocation message to proxy dispatcher */
		ObjectOutputStream objectOut = new ObjectOutputStream(clientToServer.getOutputStream());
		objectOut.writeObject(message);
		
		/* read return message from proxy dispatcher */
		ObjectInputStream objectIn = new ObjectInputStream(clientToServer.getInputStream());
		Object object = null;
		try {
			object = objectIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		objectOut.close();
		objectIn.close();
		clientToServer.close();
		return object;
	}

}
