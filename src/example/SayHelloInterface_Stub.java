package example;

import java.io.IOException;
import java.net.UnknownHostException;

import ror.RemoteObjectRef;
import ror.Stub440;
import example2.PersonInterface;

public class SayHelloInterface_Stub extends Stub440 implements SayHelloInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3317375476953942584L;

	public SayHelloInterface_Stub(RemoteObjectRef ref) {
		super(ref);
	}

	public String sayHello(PersonInterface person) throws Exception{
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		/* save all arguments into an array */
		Object[] args = new Object[]{person};
		Class<?>[] argsType = new Class<?>[]{PersonInterface.class};
		String ret = null;
		try {
			ret = (String)super.invokeMethod(methodName, args, argsType);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw e;
		}
		return ret;
	}

	@Override
	public PersonInterface createPerson() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		/* save all arguments into an array */
		Object[] args = new Object[0];
		Class<?>[] argsType = new Class<?>[0];
		PersonInterface ret = null;
		try {
			ret = (PersonInterface)super.invokeMethod(methodName, args, argsType);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;

	}
	
	
}
