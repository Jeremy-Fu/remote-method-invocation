package exception;

/**
 * A ServerException440 exception is thrown when an error 
 * occurs on server side during the process of remote method invocation
 *
 */
public class ServerException440 extends RemoteException440{

	private static final long serialVersionUID = 2579495108534668779L;
	
	public ServerException440(String message) {
		super(message);
	}
}
