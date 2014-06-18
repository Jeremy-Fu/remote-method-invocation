package message;


import ror.RemoteObjectRef;

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
	
	public RemoteObjectRef<?> getROR() {
		return this.ror;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
	
	public Object[] getArgs() {
		return this.args;
	}
	
	/**@brief Obtain the class for each argument
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
