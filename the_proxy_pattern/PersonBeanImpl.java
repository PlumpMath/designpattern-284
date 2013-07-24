import java.lang.reflect.*;

class PersonBeanImpl implements PersonBean{
	private String name;
	private int rating = 0;
	private int rateCount = 0;

	PersonBeanImpl (String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRating (int rating) {
		this.rateCount++;
		this.rating += rating;
	}

	public int getRating() {
		if (rateCount == 0) return 0;
		else return rating/rateCount;
	}

	public PersonBean getProxy (PersonBean person) {
		if (person == this) {
			return getOwnerProxy(person);
		} else {
			return getNonOwnerProxy(person);
		}
	}

	private PersonBean getOwnerProxy(PersonBean person) {
		return (PersonBean) Proxy.newProxyInstance(
			person.getClass().getClassLoader(),
			person.getClass().getInterfaces(),
			new OwnerInvocationHandler(person));
	}

	private PersonBean getNonOwnerProxy(PersonBean person) {
		return (PersonBean) Proxy.newProxyInstance(
			person.getClass().getClassLoader(),
			person.getClass().getInterfaces(),
			new NonOwnerInvocationHandler(person));
	}
}