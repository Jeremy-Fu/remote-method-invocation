package registry;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import message.HandshakeMessage;
import message.MessageOp;
import message.MessageType;

public class LocateRegistry440 { 
    
	/**
	 * Returns a registry stub of the specific host and port.
	 * 
	 * @param host host of the remote registry
	 * @param port port on which the registry accepts requests
	 * @return a stub of the registry
	 */
	public static Registry440 getRegistry(String host, int port) {
		try{
			Socket soc = new Socket(host, port);
			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
			HandshakeMessage handshkMsg = new HandshakeMessage();
			out.writeObject(handshkMsg);
			handshkMsg = (HandshakeMessage)in.readObject();
			if (handshkMsg.getType() == MessageType.REPLY &&
					handshkMsg.getOp() == MessageOp.HANDSHAKE) {
				return new Registry440_Stub(host, port);
			} else {
				return null;
			}
		}  catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	    
    }
}