
public class SayHello implements RemoteSayHello{

	@Override
	public String sayHello(String name) {
		String rst = "Hi, " + name;
		return rst;
	}
	
	/* Test method */
	public void beHappy(String name) {
		String rst = name + " should be happy!";
		System.out.println(rst);
		return;
	}

}
