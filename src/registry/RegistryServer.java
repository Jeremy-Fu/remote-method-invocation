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
import ror.Stub440;

/**
 * A runnable class serves as a registry server. After it is stared by a
 * thread, it listens on a specific port for incoming requests and handles them
 * correspondingly. By default it just supports 4 queries:
 * 		a. lookup: Look up a specific service on rmi registry.
 * 		b. list: Return a list of all the current registered services' names. 
 *		c. rebind: Rebind a remote object's stub with its service name.
 *		d. unbind: Deregister the entry of a specific service name on registry.
 */

public class RegistryServer implements Runnable {
	private Hashtable<String, Stub440> stubTbl = 
			new Hashtable<String, Stub440>();
	private int port;
	private final boolean DEBUG = false;
	
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
						if (DEBUG) {
							System.out.println("RegistryServer.log: Receive a handshake message from " + soc.getInetAddress().getHostAddress());
						}
					} 
					
					else if (msg.getOp() == MessageOp.LOOKUP) {
						LookUpMessage lookUpMsg = (LookUpMessage)msg;
						Stub440 stub = stubTbl.get(lookUpMsg.getService());
						if (stub == null) {
							lookUpMsg.setCode(MessageCode.DENY);
						} else {
							lookUpMsg.setCode(MessageCode.OKAY);
							lookUpMsg.setStub(stub);
						}
						lookUpMsg.setType(MessageType.REPLY);
						if (DEBUG) {
							System.out.println("RegistryServer.log: Receive a lookup message from " + soc.getInetAddress().getHostAddress());
						}
					} 
					
					else if (msg.getOp() == MessageOp.LIST) {
						ListMessage listMsg = (ListMessage)msg;
						listMsg.setType(MessageType.REPLY);
						listMsg.setCode(MessageCode.OKAY);
						Set<String> keySet = stubTbl.keySet();
						String[] array = (String[])keySet.toArray(new String[keySet.size()]);
						listMsg.setServices(array);
						if (DEBUG) {
							System.out.println("RegistryServer.log: Receive a list message from " + soc.getInetAddress().getHostAddress());
						}
					} 
					
					else if (msg.getOp() == MessageOp.REBIND) {
						RebindMessage rebindMsg = (RebindMessage)msg;
						RegistryServer.this.stubTbl.put(rebindMsg.getService(), rebindMsg.getStub());
						rebindMsg.setType(MessageType.REPLY);
						rebindMsg.setCode(MessageCode.OKAY);
						if (DEBUG) {
							System.out.println("RegistryServer.log: Receive a rebind message from " + soc.getInetAddress().getHostAddress());
						}
					} 
					
					else if (msg.getOp() == MessageOp.UNBIND) {
						UnbindMessage unbindMsg = (UnbindMessage)msg;
						RegistryServer.this.stubTbl.remove(unbindMsg.getService());
						unbindMsg.setType(MessageType.REPLY);
						unbindMsg.setCode(MessageCode.OKAY);
						if (DEBUG) {
							System.out.println("log: Receive an unbind message from " + soc.getInetAddress().getHostAddress());
						}
					} else{
						msg.setType(MessageType.REPLY);
						msg.setCode(MessageCode.DENY);
						if (DEBUG) {
							System.out.println("RegistryServer.log: Receive an unknown message from " + soc.getInetAddress().getHostAddress());
						}
					}
					
				} else {
					msg.setType(MessageType.REPLY);
					msg.setCode(MessageCode.DENY);
					if (DEBUG) {
						System.out.println("log: Receive an unknown message from " + soc.getInetAddress().getHostAddress());
					}
				}
				
				out.writeObject(msg);
				in.close();
				out.close();
				soc.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	
	
}
