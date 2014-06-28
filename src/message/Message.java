package message;

import java.io.Serializable;

public abstract class Message implements Serializable{
	
	private static final long serialVersionUID = -1925835944527246262L;
	private MessageType type;	/* {QUERY, REPLY} */
	private MessageCode code;	/* {OKAY, DENY, EXCEPTION} */
	private MessageOp op;		/* {HANDSHAKE, REBIND, LOOKUP, UNBIND, LIST, INVOKE} */
	
	public Message(MessageType type, MessageOp op) {
		this.type = type;
		this.op = op;
	}
	
	public MessageType getType() {
		return this.type;
	}
	
	public MessageCode getCode() {
		return this.code;
	}
	
	public MessageOp getOp() {
		return this.op;
	}
	
	public void setOp(MessageOp op) {
		this.op = op;
		return;
	}
	
	public void setType(MessageType type) {
		this.type = type;
		return;
	}
	
	public void setCode(MessageCode code) {
		this.code = code;
		return;
	}

}
