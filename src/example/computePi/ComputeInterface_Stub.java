package example.computePi;

import java.io.IOException;
import java.net.UnknownHostException;

import ror.RemoteObjectRef;
import ror.Stub440;
import exception.RemoteException440;

public class ComputeInterface_Stub extends Stub440 implements ComputeInterface {

	public ComputeInterface_Stub(RemoteObjectRef ror) {
		super(ror);
	}
	
	public <T> T executeTask(Task<T> t) throws RemoteException440 {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		/* save all arguments into an array */
		Object[] args = new Object[]{t};
		Class<?>[] argsType = new Class<?>[]{Task.class};
		/* declare return value */
		T ret = null;
		
		/* invoke method */
		try {
			ret = (T) super.invokeMethod(methodName, args, argsType);
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
