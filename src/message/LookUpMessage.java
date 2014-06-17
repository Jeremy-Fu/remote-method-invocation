package message;

import ror.RemoteObjectRef;

public class LookUpMessage extends Message {
	private static final long serialVersionUID = -6239943451201484355L;
	private RemoteObjectRef<?> ror;
	private String serviceName;
	
	public LookUpMessage(String serviceName) {
		super(MessageType.QUERY, MessageOp.LOOKUP);
		this.serviceName = serviceName;
	}
	
	public void setROR(RemoteObjectRef<?> ref) {
		this.ror = ref;
	}
	
	public RemoteObjectRef<?> getROR() {
		return ror;
	}
	
	public String getService() {
		return serviceName;
	}


}
