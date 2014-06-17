package registry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import message.ListMessage;
import message.LookUpMessage;
import message.Message;
import message.MessageCode;
import message.MessageOp;
import message.MessageType;
import ror.RemoteObjectRef;


public class RegistryServer implements Runnable {
	private Hashtable<String, RemoteObjectRef<?>> tbl = 
			new Hashtable<String, RemoteObjectRef<?>>();
	private int port;
	
	public RegistryServer() {
		this.port = 1099;
	}
	
	public RegistryServer (int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			ServerSocket serverSoc = new ServerSocket(this.port);
			while (true) {
				Socket soc = serverSoc.accept();
				RegistryServerHandler rsh = new RegistryServerHandler(soc);
				Thread thread = new Thread(rsh);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private class RegistryServerHandler implements Runnable {
		private Socket soc;
		
		public RegistryServerHandler(Socket soc) {
			this.soc = soc;
		}
		
		@Override
		public void run() {
			try {
				ObjectInputStream in = new ObjectInputStream(this.soc.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(this.soc.getOutputStream());
				Message msg = (Message) in.readObject();
				if (msg.getType() == MessageType.QUERY) {
					if (msg.getOp() == MessageOp.HANDSHAKE) {
						msg.setType(MessageType.REPLY);
						msg.setCode(MessageCode.OKAY);
					} else if (msg.getOp() == MessageOp.LOOKUP) {
						LookUpMessage lookUpMsg = (LookUpMessage)msg;
						lookUpMsg.setType(MessageType.REPLY);
						lookUpMsg.setCode(MessageCode.OKAY);
						lookUpMsg.setROR(tbl.get(lookUpMsg.getService()));
					} else if (msg.getOp() == MessageOp.LIST) {
						ListMessage listMsg = (ListMessage)msg;
						listMsg.setType(MessageType.REPLY);
						listMsg.setCode(MessageCode.OKAY);
						listMsg.setServices((String[])tbl.keySet().toArray());
					} else if (msg.getOp() == MessageOp.REBIND) {
						
					} else if (msg.getOp() == MessageOp.UNBIND) {
						
					} else{
						//TODO DENY
					}
					
				} else {
					//TODO DENY
				}
				
				out.writeObject(msg);
				in.close();
				out.close();
				soc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	
	
}
