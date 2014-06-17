package message;

import ror.RemoteObjectRef;

public class RebindMessage extends Message{
	
	private RemoteObjectRef<?> ror;
	private String serviceName;

	public RebindMessage(String serviceName, RemoteObjectRef<?> ref) {
		super(MessageType.QUERY, MessageOp.REBIND);
		this.ror = ref;
		this.serviceName = serviceName;
	}
	
	public RemoteObjectRef<?> getROR() {
		return this.ror;
	}
	
	public String getService() {
		return this.serviceName;
	}
	
	

}
