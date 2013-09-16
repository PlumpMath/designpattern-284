import java.lang.reflect.*;

class Reflection {
	public void say() {
		System.out.println("Nobody say nothing");
	}

	public void sayLove(String boy, String girl) {
		System.out.println(boy + " love " + girl);
	}

	public static void main(String args[]) {
		// System.out.println("Hello world!");
		try {
			Class c = Class.forName("MethodClass");
			Method methods[] = c.getDeclaredMethods();
			for (int i=0; i<c.getDeclaredMethods().length; i++) {
				System.out.println(methods[i].toString());
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
		Reflection methodReflection = new Reflection();
		MethodClass reflectionManager = new MethodClass(methodReflection);
		reflectionManager.bindMethod("say");
		reflectionManager.doMethod();
		reflectionManager.bindMethod("sayLove", "Jawinton", "Yiliu");
		reflectionManager.doMethod();
	}
}