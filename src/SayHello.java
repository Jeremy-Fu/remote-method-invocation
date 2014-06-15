
public class SayHello implements RemoteSayHello{

	@Override
	public String sayHello(String name) {
		String rst = "Hi, " + name;
		return rst;
	}

}
