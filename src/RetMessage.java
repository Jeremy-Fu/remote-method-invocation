import java.io.Serializable;


public class RetMessage implements Serializable{
	private Object retValue;
	private boolean isException;
	
	public RetMessage(Object retValue, boolean isException) {
		this.retValue = retValue;
		this.isException = isException;
	}
}
