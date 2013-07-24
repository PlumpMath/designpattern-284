import java.lang.reflect.*;

class OwnerInvocationHandler implements InvocationHandler {
	private PersonBean person;

	public OwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
		try {
			if (method.getName().equals("setRating")) {
				throw new IllegalAccessException();
			} else {
				return method.invoke(this.person, args);
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}