package message;

public class UnbindMessage extends Message {

	private static final long serialVersionUID = 705949575347961468L;
	private String serviceName;
	
	public UnbindMessage(String serviceName) {
		super(MessageType.QUERY, MessageOp.UNBIND);
		this.serviceName = serviceName;
	}
	
	public String getService() {
		return this.serviceName;
	}

}
