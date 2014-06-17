package registry;

import ror.RemoteObjectRef;

public interface Registry {
	
	public RemoteObjectRef<?> lookup(String serviceName);
	public void rebind(String serviceName, RemoteObjectRef<?> ror);
	public String[] list();
	public void unbind(String serviceName); 
}
