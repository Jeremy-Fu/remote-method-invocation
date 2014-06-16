
public class RemoteObjectRef {
	String ipAddr;
	int port;
	String objectKey;
	
	public RemoteObjectRef(String ip, int portNum, String key) {
		ipAddr = ip;
		port = portNum;
		objectKey = key;
	}
}
