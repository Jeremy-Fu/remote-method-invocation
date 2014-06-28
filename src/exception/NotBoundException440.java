package exception;

/**
 * A remote exception which will be thrown If the registry cannot find the 
 * specific record associate with the name of the service in the process 
 * of lookup or unbind
 *
 */
public class NotBoundException440 extends RemoteException440{

	private static final long serialVersionUID = 3391127620612420106L;
	public NotBoundException440(String message) {
		super(message);
	}
}
