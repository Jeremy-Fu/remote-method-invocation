package message;

import ror.Remote440;
import ror.Stub440;

public class RebindMessage extends Message{
	
	private static final long serialVersionUID = 5129745242089473396L;
	private Stub440 stub;
	private String serviceName;

	public RebindMessage(String serviceName, Remote440 obj) {
		super(MessageType.QUERY, MessageOp.REBIND);
		this.stub = (Stub440)obj;
		this.serviceName = serviceName;
	}
	
	public Stub440 getStub() {
		return this.stub;
	}
	
	public String getService() {
		return this.serviceName;
	}
	
	

}
