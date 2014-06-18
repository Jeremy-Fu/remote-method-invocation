package example2;

import ror.RemoteInterface;

public interface PersonInterface extends RemoteInterface {
	public String getName();
	public void setName(String name);
}
