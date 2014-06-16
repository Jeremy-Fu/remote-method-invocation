import java.io.Serializable;


public class RetMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object retValue;
	private boolean isException;
	
	public RetMessage(Object retValue, boolean isException) {
		this.retValue = retValue;
		this.isException = isException;
	}
	
	public Object getRetValue() {
		return this.retValue;
	}
}
