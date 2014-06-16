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

		try {
			Class<?>[] argsPara = {String.class};
			Method method = output.getClass().getMethod("sayHello", argsPara);
			System.out.println("Find Method:" + method.getName());
			Object returnValue = method.invoke(output, "FUGEG");
			System.out.println("Catch return value type: " + returnValue.getClass().getName());
			System.out.println(returnValue);
			System.out.println("Test2:");
			Method method2 = output.getClass().getMethod("beHappy", argsPara);
			System.out.println("Find method2:" + method2.getName());
			System.out.println("Return value type:" + method2.getReturnType().getName());
			Object returnValue2 = method2.invoke(output, "Geng Fu");
			if (returnValue2 == null) {
				System.out.println("Catch return value value == null");
			} else {
				System.out.println("Catch return value type: " + returnValue2.getClass().getName());
			}
			
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
