package message;

import ror.Remote440;
import ror.Stub440;

public class LookUpMessage extends Message {
	private static final long serialVersionUID = -6239943451201484355L;
	private Stub440 stub;
	private String serviceName;
	
	public LookUpMessage(String serviceName) {
		super(MessageType.QUERY, MessageOp.LOOKUP);
		this.serviceName = serviceName;
	}
	
	public void setStub(Remote440 obj) {
		this.stub = (Stub440) obj;
	}
	
	public Remote440 getStub() {
		return this.stub;
	}
	
	public String getService() {
		return serviceName;
	}
}
