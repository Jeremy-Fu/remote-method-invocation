package util;

import java.io.NotSerializableException;
import java.io.Serializable;

import ror.Remote440;
import ror.RemoteObjectRef;
import ror.Stub440;

/**
 * This class provides functions to guarantee that the type of objects passed
 * between hosts during remote method invocation are valid.
 * 
 * It checks a remote method's parameters and its return value:
 * 1. Parameter: It throws an exception if the parameter is not serializable
 * 				 before sending to remote object's host
 * 
 * 				 It also checks the parameter on remote object's host, if the
 * 				 parameter is a remote object which is exported on local host,
 * 				 it replaces the stub with the remote object
 * 
 * 2. Return object: 
 *
 */
public class RMIParamCheck {
	public static void paramSendCheck(Object[] objs) throws NotSerializableException {
		for (int i = 0; i < objs.length; i++) {
			/* Check if parameters are all serializable */
			if ( (objs[i] != null) && !(objs[i] instanceof Serializable) ) {
				throw new NotSerializableException("Non-serializable parameter");
			}
			if ((objs[i] instanceof Remote440) && !(objs[i] instanceof Stub440)) {
				RemoteObjectRef ror = Naming.getROR((Remote440)objs[i]);
				
				if (ror != null) {
					objs[i] = ror.localise();
				}
			}
		}
	}
	
	public static Object retCheck(Object obj) throws NotSerializableException {
		Object ret = obj;
		/* Check if return object is serializable, if the return object is null,
		 * we should still pass it back, no matter a non-return method or the
		 * return value is null */
		if ( (obj != null) && !(obj instanceof Serializable) ) {
			throw new NotSerializableException("Non-serializable parameter");
		}
		if ((obj instanceof Remote440) && !(obj instanceof Stub440)) {
			RemoteObjectRef ror = Naming.getROR((Remote440)obj);
			if (ror != null) {
				ret = ror.localise();
			}
		}
		return ret;		
	}
	
	/**
	 * Check each parameter, if the parameter is a stub with remote object 
	 * on local, change the stub with its associate remote object
	 * 
	 * @param objs array of parameters to check
	 */
	public static void paramInvokeCheck(Object[] objs) {
		for (int i = 0; i < objs.length; i++) {
			if (objs[i] instanceof Stub440) {
				Remote440 remoteObj = Naming.getObject( ( (Stub440)objs[i] ).ror);
				if (remoteObj != null) {
					objs[i] = remoteObj;
				}
			}
		}		
	}
}
