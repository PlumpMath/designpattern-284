interface PersonBean {
	public static final int a = 1;

	public void setName(String name);
	public void setRating(int rating);

	public String getName();
	public int getRating();
	public PersonBean getProxy(PersonBean person);
}