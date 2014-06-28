package exception;

/**
 * A RegistryNotFoundException will be thrown when an error occurs on the 
 * RMI registry server, e.g., connection cannot be made with registry server
 *
 */
public class RegistryNotFoundException440 extends RemoteException440{

	private static final long serialVersionUID = 8773032899916501236L;
	
	public RegistryNotFoundException440(String message) {
		super(message);
	}
}
