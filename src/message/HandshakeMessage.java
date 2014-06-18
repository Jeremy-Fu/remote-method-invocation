package message;

/**
 * HandshakeMessage is a subclass of Message functioning to reach out
 * to RMIregistry. 
 */
public class HandshakeMessage extends Message {

	private static final long serialVersionUID = 3782931048259016465L;
	
	public HandshakeMessage() {
		super(MessageType.QUERY, MessageOp.HANDSHAKE);
	}
	
}
