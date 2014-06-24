package example.person;

public class Person implements PersonInterface{

	private static final long serialVersionUID = -7012122403973779024L;
	private String name;
	private int age;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override 
	public int getAge() {
		return this.age;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
