public class Concurrency2 implements Runnable {
	public void run () {
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main (String args[]) {
		Runnable runnable = new Concurrency2();
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		Thread thread3 = new Thread(runnable);
		System.out.println(System.currentTimeMillis());
		thread1.start();
		try {
			thread1.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
		thread2.start();
		try {
			thread2.join(1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
		thread3.start();
	}
}