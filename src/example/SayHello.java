package example;

import example2.PersonInterface;

public class SayHello implements SayHelloInterface{

	@Override
	public String sayHello(PersonInterface person) {
		//param person is an instance of PersonInterface_Stub
		String rst = "Hi, " + person.getName() ;
		return rst;
	}
	
	/* Test method */
	public void beHappy(String name) {
		String rst = name + " should be happy!";
		System.out.println(rst);
		return;
	}

}
