package example2;

public class Person implements PersonInterface{
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		System.out.println("set name to" + name);
		this.name = name;
	}

}
