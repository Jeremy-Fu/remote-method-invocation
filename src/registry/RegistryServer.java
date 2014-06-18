package registry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Set;

import message.ListMessage;
import message.LookUpMessage;
import message.Message;
import message.MessageCode;
import message.MessageOp;
import message.MessageType;
import message.RebindMessage;
import message.UnbindMessage;
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
						System.out.println("RegistryServer.log: Receive a handshake message from " + soc.getInetAddress().getHostAddress());
					} else if (msg.getOp() == MessageOp.LOOKUP) {
						LookUpMessage lookUpMsg = (LookUpMessage)msg;
						RemoteObjectRef<?> ror = tbl.get(lookUpMsg.getService());
						if (ror == null) {
							lookUpMsg.setCode(MessageCode.DENY);
						} else {
							lookUpMsg.setCode(MessageCode.OKAY);
							lookUpMsg.setROR(ror);
						}
						lookUpMsg.setType(MessageType.REPLY);
						System.out.println("RegistryServer.log: Receive a lookup message from " + soc.getInetAddress().getHostAddress());
					} else if (msg.getOp() == MessageOp.LIST) {
						ListMessage listMsg = (ListMessage)msg;
						listMsg.setType(MessageType.REPLY);
						listMsg.setCode(MessageCode.OKAY);
						Set<String> keySet = tbl.keySet();
						String[] array = (String[])keySet.toArray(new String[keySet.size()]);
						listMsg.setServices(array);
						System.out.println("RegistryServer.log: Receive a list message from " + soc.getInetAddress().getHostAddress());
					} else if (msg.getOp() == MessageOp.REBIND) {
						RebindMessage rebindMsg = (RebindMessage)msg;
						RegistryServer.this.tbl.put(rebindMsg.getService(), rebindMsg.getROR());
						rebindMsg.setType(MessageType.REPLY);
						rebindMsg.setCode(MessageCode.OKAY);
						System.out.println("RegistryServer.log: Receive a rebind message from " + soc.getInetAddress().getHostAddress());
					} else if (msg.getOp() == MessageOp.UNBIND) {
						UnbindMessage unbindMsg = (UnbindMessage)msg;
						RegistryServer.this.tbl.remove(unbindMsg.getService());
						unbindMsg.setType(MessageType.REPLY);
						unbindMsg.setCode(MessageCode.OKAY);
						System.out.println("log: Receive an unbind message from " + soc.getInetAddress().getHostAddress());
					} else{
						msg.setType(MessageType.REPLY);
						msg.setCode(MessageCode.DENY);
						System.out.println("RegistryServer.log: Receive an unknown message from " + soc.getInetAddress().getHostAddress());
					}
					
				} else {
					msg.setType(MessageType.REPLY);
					msg.setCode(MessageCode.DENY);
					System.out.println("log: Receive an unknown message from " + soc.getInetAddress().getHostAddress());
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
