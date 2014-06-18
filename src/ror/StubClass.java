package ror;

public abstract class StubClass {
	public RemoteObjectRef<?> ror;
	
	public StubClass(RemoteObjectRef<?> ref) {
		this.ror = ref;
	}
	
}
