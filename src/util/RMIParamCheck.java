package util;

import ror.Remote440;
import ror.RemoteObjectRef;
import ror.Stub440;

public class RMIParamCheck {
	public static void paramSendCheck(Object[] objs) {
		for (int i = 0; i < objs.length; i++) {
			if ((objs[i] instanceof Remote440) && !(objs[i] instanceof Stub440)) {
				RemoteObjectRef ror = Naming.getROR((Remote440)objs[i]);
				if (ror != null) {
					objs[i] = ror.localise();
				}
			}
		}
	}
	
	public static Object retCheck(Object obj) {
		Object ret = obj;
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
				Remote440 remoteObj = Naming.getObject(((Stub440)objs[i]).ror);
				if (remoteObj != null) {
					objs[i] = remoteObj;
				}
				
			}
		}		
	}
}
