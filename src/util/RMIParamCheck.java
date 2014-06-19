package util;

import ror.Remote440;
import ror.RemoteObjectRef;
import ror.Stub440;

public class RMIParamCheck {
	public static void paramCheck(Object[] objs) {
		for (int i = 0; i < objs.length; i++) {
			if ((objs[i] instanceof Remote440) && !(objs[i] instanceof Stub440)) {
				RemoteObjectRef ror = Naming.getROR((Remote440)objs[i]);
				if (ror != null) {
					objs[i] = ror.localise();
				}
			}
		}
	}
}
