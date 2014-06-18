package registry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import message.ListMessage;
import message.LookUpMessage;
import message.Message;
import message.MessageCode;
import message.RebindMessage;
import message.UnbindMessage;
import ror.RemoteObjectRef;

public class Registry440_Stub implements Registry440{
	private String host;
	private int port;
	
	public Registry440_Stub(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public Registry440_Stub() {
		this.host = "localhost";
		this.port = 1099;
	}
	
	public Registry440_Stub(int port) {
		this.host = "localhost";
		this.port = 1099;
	}

	@Override
	public RemoteObjectRef<?> lookup(String serviceName) throws Exception {
		LookUpMessage lookupMsg = new LookUpMessage(serviceName);
		lookupMsg = (LookUpMessage) messageHandler(lookupMsg);
		if (lookupMsg.getCode() == MessageCode.DENY) {
			//TODO:thrwo an exception
			throw new Exception(serviceName + " not bound");
		}
		
		return lookupMsg.getROR();
	}
	
	@Override
	public String[] list() {
		ListMessage listMsg = new ListMessage();
		listMsg = (ListMessage) messageHandler(listMsg);
		return listMsg.getServices();
	}

	@Override
	public void rebind(String serviceName, RemoteObjectRef<?> ror) {
		RebindMessage rebindMsg = new RebindMessage(serviceName, ror);
		rebindMsg = (RebindMessage) messageHandler(rebindMsg);
		return;
	}

	@Override
	public void unbind(String serviceName) {
		UnbindMessage unbindMsg = new UnbindMessage(serviceName);
		unbindMsg = (UnbindMessage) messageHandler(unbindMsg);
		return;
	}
	
	/**
	 *  Communication module for registry stub to send
	 *  and receive message between local and RMIregistry
	 *  @param Message the message sent to RMIregistry
	 *  @return Message the message received from RMIregistry
	 *  which contains invocation result or exception.
	 */
	private Message messageHandler(Message msg) {
		Message reply = null;
		try {
			Socket soc = new Socket(host, port);
			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
			out.writeObject(msg);
			reply = (Message) in.readObject();
			in.close();
			out.close();
			soc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return reply;
	}
	
	
}
