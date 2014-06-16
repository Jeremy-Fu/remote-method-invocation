import java.util.HashMap;


public class RemoteObjectRefTable {
	
	private HashMap<RemoteObjectRef, Object> remoteObjectMap;
	
	public RemoteObjectRefTable() {
		this.remoteObjectMap = new HashMap<RemoteObjectRef, Object>();
	}
	
	public void addObject(RemoteObjectRef ror, Object obj) {
		this.remoteObjectMap.put(ror, obj);
		return;
	}
	
	public Object getObject(RemoteObjectRef ror) {
		return this.remoteObjectMap.get(ror);
	}
}
