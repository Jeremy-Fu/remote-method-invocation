package registry;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import message.HandshakeMessage;
import message.MessageCode;
import message.MessageOp;
import message.MessageType;

public class LocateRegistry { 
    
	public static Registry getRegistry(String host, int port) {
		try{
			Socket soc = new Socket(host, port);
			ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			//ask
			HandshakeMessage handshkMsg = new HandshakeMessage();
			out.writeObject(handshkMsg);
			handshkMsg = (HandshakeMessage)in.readObject();
			if (handshkMsg.getType() == MessageType.REPLY &&
					handshkMsg.getOp() == MessageOp.HANDSHAKE) {
				return new Registry_Stub(host, port);
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