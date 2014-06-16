import java.io.Serializable;

public class InvokeMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4488185998485866404L;
	private RemoteObjectRef ror;
	private String methodName;
	private Object[] args;
	
	public InvokeMessage(RemoteObjectRef ror, String methodName, Object[] args) {
		this.ror = ror;
		this.methodName = methodName;
		this.args = args;
	}
	
	public RemoteObjectRef<?> getROR() {
		return this.ror;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
	
	public Object[] getArgs() {
		return this.args;
	}
	
	public Class<?>[] getArgsType() {
		Class<?>[] argsType = new Class<?>[this.args.length];
		for (int i = 0; i < this.args.length; i++) {
			argsType[i] = this.args[i].getClass();
		}
		return argsType;
	}
}
