package message;

import ror.RemoteObjectRef;

public class RebindMessage extends Message{
	
	private static final long serialVersionUID = 5129745242089473396L;
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
