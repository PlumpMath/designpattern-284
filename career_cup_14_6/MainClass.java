
class MainClass {
	public static void main(String args[]) {
		CountMap<Integer, String> map = new CountMap<Integer, String>();
		map.put(1, "111111");
		System.out.println(map.get(1));
		System.out.println(map.getCount());
		map = new CountMap<Integer, String>();
		map.put(3, "3333333");
		System.out.println(map.get(1));
		System.out.println(map.get(3));
		System.out.println(map.getCount());
	}
}