public class Concurrency1 implements Runnable {
	public void run () {
		System.out.print(Thread.currentThread().getName() + " ");
	}

	public static void main (String args[]) {
		Runnable runnable = new Concurrency1();
		for (int i=0; i<25; i++) {
			Thread thread = new Thread(runnable);
			thread.setName(""+i);
			thread.start();
		}
	}
}