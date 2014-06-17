package message;


public class HandshakeMessage extends Message {

	private static final long serialVersionUID = 3782931048259016465L;

	public HandshakeMessage() {
		super(MessageType.QUERY, MessageOp.HANDSHAKE);
	}
	
}
