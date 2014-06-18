package message;


import ror.RemoteObjectRef;

/**
 * InvokeMessage is a subclass of Message functioning to invoke methods
 * on remote object. Parameters and return value are marshalled.
 * 
 */
public class InvokeMessage extends Message {

	private static final long serialVersionUID = 4488185998485866404L;
	private RemoteObjectRef<?> ror;
	private String methodName;
	private Object[] args;
	

	public InvokeMessage(RemoteObjectRef<?> ror, String methodName, Object[] args) {
		super(MessageType.QUERY, MessageOp.INVOKE);
		this.ror = ror;
		this.methodName = methodName;
		this.args = args;
	}
	
	/**
	 *  Get the Remote object reference
	 *  @return RemoteObjectRef a reference to a remote object
	 */
	public RemoteObjectRef<?> getROR() {
		return this.ror;
	}
	
	/**
	 *  Get the method name of which will be invoked on remote object
	 *  @return String the method name 
	 */
	public String getMethodName() {
		return this.methodName;
	}
	
	/**
	 *  Get the arguments passed to the method
	 *  @return a array of arguments
	 */
	public Object[] getArgs() {
		return this.args;
	}
	
	/**
	 * Get the class for each argument
	 * @return The class array of arguments.
	 */
	public Class<?>[] getArgsType() {
		Class<?>[] argsType = new Class<?>[this.args.length];
		for (int i = 0; i < this.args.length; i++) {
			argsType[i] = this.args[i].getClass();
		}
		return argsType;
	}
}
