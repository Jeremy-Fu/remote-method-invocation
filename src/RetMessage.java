import java.io.Serializable;


public class RetMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object retValue;
	private boolean exceptionFlag;
	
	public RetMessage(Object retValue, boolean isException) {
		this.retValue = retValue;
		this.exceptionFlag = isException;
	}
	
	public boolean isException() {
		return exceptionFlag;
	}
	
	public Object getRet() {
		return retValue;
	}
	
	public Object getRetValue() {
		return this.retValue;
	}
}
