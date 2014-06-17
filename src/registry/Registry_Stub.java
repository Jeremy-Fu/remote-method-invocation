package registry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import message.ListMessage;
import message.LookUpMessage;
import message.Message;
import ror.RemoteObjectRef;

public class Registry_Stub implements Registry{
	private String host;
	private int port;
	
	public Registry_Stub(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public Registry_Stub() {
		this.host = "localhost";
		this.port = 1099;
	}
	
	public Registry_Stub(int port) {
		this.host = "localhost";
		this.port = 1099;
	}

	@Override
	public RemoteObjectRef<?> lookup(String serviceName) {
		LookUpMessage lookupMsg = new LookUpMessage(serviceName);
		lookupMsg = (LookUpMessage) messageHandler(lookupMsg);
		return lookupMsg.getROR();
	}
	
	@Override
	public String[] list() {
		ListMessage listMsg = new ListMessage();
		listMsg = (ListMessage) messageHandler(listMsg);
		return listMsg.getServices();
	}

	@Override
	public void rebind(String serviceName, RemoteObjectRef ror) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unbind(String serviceName) {
		// TODO Auto-generated method stub
		
	}
	
	
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
