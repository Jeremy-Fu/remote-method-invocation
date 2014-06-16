import java.util.ArrayList;
import java.util.List;


public class SayHello_Stub implements RemoteSayHello{
	private RemoteObjectRef ror;
	
	public SayHello_Stub(RemoteObjectRef remoteObject) {
		ror = remoteObject;
	}
	
	public String sayHello(String name) {
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		/* save all arguments into a list */
		List<Object> args = new ArrayList<Object>();
		args.add(name);
		String ret = invokeMethod(methodName, args);
		return ret;
	}

	private String invokeMethod(String methodName, List<Object> args) {
		InvokeMessage message = new InvokeMessage(ror, methodName, args);
		String inetAddr = ror.getIP();
		int port = ror.getPort();
		Socket clietToServer = new Socket(inetAddr, port);
		
		return null;
	}

}
