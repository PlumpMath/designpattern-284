import java.lang.reflect.*;

class MatchMakingTestDrive {

	public static void main (String[] args) {
		PersonBean joe = new PersonBeanImpl("joe");
		PersonBean michael = new PersonBeanImpl("michael");
		PersonBean joeProxy = joe.getProxy(joe);
		PersonBean michaelProxy = michael.getProxy(joe);
		System.out.println("Name is " + joeProxy.getName());
		System.out.println(PersonBean.a);
		try {
			joeProxy.setRating(10);
		} catch (Exception e) {
			System.out.println("Can't set rating from owner proxy.");
		}
		System.out.println(joeProxy.getRating());

		try {
			michaelProxy.setRating(5);
		} catch (Exception e) {
			System.out.println("Can't set rating from owner proxy.");
		}
		System.out.println(joeProxy.getRating());
	}
}