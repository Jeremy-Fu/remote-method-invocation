import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;


public class testInvokeMethod {
	public static void main(String[] args) {
		Hashtable<String, Object> tbl = new Hashtable<String, Object>();
		SayHello input = new SayHello();
		tbl.put("123",input);
		Object output = tbl.get("123");
		System.out.println(output.getClass().getName());
//		Method[] methods = output.getClass().getMethods();
//		for (int i = 0; i < methods.length; i++) {
//			System.out.print(methods[i].getName() + "\t");
//		}
		try {
			Class<?>[] argsPara = {String.class};
			Method method = output.getClass().getMethod("sayHello", argsPara);
			System.out.println("Find Method:" + method.getName());
			System.out.println(method.invoke(output, "FUGENG"));
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
