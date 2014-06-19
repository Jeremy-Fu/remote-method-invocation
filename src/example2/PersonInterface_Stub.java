package example2;

import java.io.IOException;
import java.net.UnknownHostException;

import ror.RemoteObjectRef;
import ror.Stub440;

public class PersonInterface_Stub extends Stub440 implements PersonInterface{

	private static final long serialVersionUID = 7153111435712316422L;

	public PersonInterface_Stub(RemoteObjectRef ref) {
		super(ref);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	
		String ret = null;
		try {
			ret = (String)super.invokeMethod(methodName, new Object[0], new Class<?>[0]);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void setName(String name) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		/* save all arguments into an array */
		Object[] args = new Object[]{name};
		Class<?>[] argsType = new Class<?>[]{String.class};
		try {
			super.invokeMethod(methodName, args, argsType);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

}
