package registry;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import exception.RegistryNotFoundException440;
import exception.RemoteException440;
import message.HandshakeMessage;
import message.MessageOp;
import message.MessageType;

/**
 * Provides a static method getRegistry to get the rmi registry's local stub,
 * it will communicate with the rmi registry host with HANDSHAKE message to
 * ensure the existence of registry
 *
 */
public class LocateRegistry440 { 
    
	/**
	 * Returns a registry stub of the specific host and port.
	 * 
	 * @param host host of the remote registry
	 * @param port port on which the registry accepts requests
	 * @return a stub of the registry
	 */
	public static Registry440 getRegistry(String host, int port) throws RemoteException440{
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
		}  catch (SocketException e) {
			throw new RegistryNotFoundException440("Unknown registry");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	    
    }
}