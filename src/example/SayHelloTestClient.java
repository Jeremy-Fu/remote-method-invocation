package example;

import ror.RemoteObjectRef;

public class SayHelloTestClient {
	public static void main(String[] args) {
		RemoteObjectRef<?> ror = new RemoteObjectRef("localhost", 55555, "hehe", "RemoteSayHello");
		RemoteSayHello_Stub stub = null;
		
		try {
			stub = (RemoteSayHello_Stub) ror.localise();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String ret = stub.sayHello("MOTO!");
		System.out.println(ret);
	}
}
