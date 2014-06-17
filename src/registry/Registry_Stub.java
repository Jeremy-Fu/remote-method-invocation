package registry;

import ror.RemoteObjectRef;

public class Registry_Stub implements Registry{
	private String host;
	private int port;
	
	public Registry_Stub(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public Registry_Stub() {
		this.host = "localhost";
		this.port = 1099;
	}
	
	public Registry_Stub(int port) {
		this.host = "localhost";
		this.port = 1099;
	}

	@Override
	public RemoteObjectRef<?> lookup(String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rebind(String serviceName, RemoteObjectRef ror) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unbind(String serviceName) {
		// TODO Auto-generated method stub
		
	}
	
	
}
