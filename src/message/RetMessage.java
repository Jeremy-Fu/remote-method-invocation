package message;



public class RetMessage extends Message{
	
	private static final long serialVersionUID = 1L;
	private Object retValue;
	private boolean isRuntimeException;
	private Exception exception;
	
	public RetMessage(Object retValue) {
		super(MessageType.REPLY, MessageOp.INVOKE);
		this.isRuntimeException = false;
		this.retValue = retValue;
		this.exception = null;
	}
	
	public RetMessage(Exception e, boolean flag) {
		super(MessageType.REPLY, MessageOp.INVOKE);
		this.isRuntimeException = flag;
		this.retValue = null;
		this.exception = e;
		super.setCode(MessageCode.EXCEPTION);
	}


	public Object getRet() {
		return retValue;
	}
	
	public boolean isRuntimException() {
		return this.isRuntimeException;
	}
	
	
	public Exception getException() {
		return this.exception;
	}
}
