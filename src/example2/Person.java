package example2;

public class Person implements PersonInterface{

	private static final long serialVersionUID = -7012122403973779024L;
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
