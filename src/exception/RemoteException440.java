package exception;

/**
 * A super class for all the exceptions related to communication through a 
 * remote method call
 * 
 */
public class RemoteException440 extends Exception{
	
	private static final long serialVersionUID = -8988086206149938750L;

	public RemoteException440 (){}
	
	public RemoteException440 (String s) {
		super(s);
	}
	
	public RemoteException440 (String s, Throwable cause) {
		super(s, cause);
	}
	
	public Throwable getCause() {
		return super.getCause();
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
