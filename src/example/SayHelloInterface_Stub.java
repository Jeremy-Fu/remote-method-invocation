package example;

import java.io.IOException;
import java.net.UnknownHostException;
import example2.Person;
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
			ret = (String)super.invokeMethod(methodName, args);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
}
