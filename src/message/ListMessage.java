package message;


public class ListMessage extends Message {
	private static final long serialVersionUID = -7333353894123723849L;
	private String[] services;
	
	public ListMessage() {
		super(MessageType.QUERY, MessageOp.LIST);
	}
	
	public void setServices(String[] services) {
		this.services = services;
	}
	
	public String[] getServices() {
		return this.services;
	}

}
