package ror;

import java.util.HashMap;


public class RemoteObjectRefTable {
	
	private HashMap<String, Object> remoteObjectMap;
	
	public RemoteObjectRefTable() {
		this.remoteObjectMap = new HashMap<String, Object>();
	}
	
	public void addObject(String objectKey, Object obj) {
		this.remoteObjectMap.put(objectKey, obj);
		return;
	}
	
	public Object getObject(String objectKey) {
		return this.remoteObjectMap.get(objectKey);
	}
}
