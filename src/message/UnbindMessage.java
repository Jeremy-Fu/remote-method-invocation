package message;

public class UnbindMessage extends Message {

	private String serviceName;
	
	public UnbindMessage(String serviceName) {
		super(MessageType.QUERY, MessageOp.UNBIND);
		this.serviceName = serviceName;
	}
	
	public String getService() {
		return this.serviceName;
	}

}
