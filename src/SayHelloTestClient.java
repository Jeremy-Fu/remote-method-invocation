
public class SayHelloTestClient {
	public static void main(String[] args) {
		RemoteObjectRef<?> ror = new RemoteObjectRef("128.237.217.119", 1025, "SayHello-1", "RemoteSayHello");
		RemoteSayHello_Stub stub = null;
		
		try {
			stub = (RemoteSayHello_Stub) ror.localise();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String ret = stub.sayHello("Kimwei");
		System.out.println(ret);
	}
}
