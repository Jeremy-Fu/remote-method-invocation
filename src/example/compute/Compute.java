package example.compute;


public class Compute implements ComputeInterface {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7430317544810489953L;

	public Compute() {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }

}