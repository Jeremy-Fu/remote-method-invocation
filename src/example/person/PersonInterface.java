package example.person;

import ror.Remote440;

public interface PersonInterface extends Remote440 {
	public String getName();
	public void setName(String name);
	public int getAge();
	public void setAge(int age);
}
