package message;



public class RetMessage extends Message{
	
	private static final long serialVersionUID = 1L;
	private Object retValue;
	
	public RetMessage(Object retValue, boolean isException) {
		super(MessageType.REPLY, MessageOp.INVOKE);
		this.retValue = retValue;
	}

	public Object getRet() {
		return retValue;
	}
}
