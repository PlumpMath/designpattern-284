public class DeadLock{
	private static Thread thread1, thread2;
	private static Object lock1 = new Object(), lock2 = new Object();

	public static void main (String args[]) {
		thread1 = new Thread(new Runnable() {
			public void run () {
				synchronized (lock1) {
					try {
						lock2.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					int i=0;
					while (i<5) {
						System.out.println("1");
						i++;
					}
					lock1.notify();
				}
			}
		});
		thread2 = new Thread(new Runnable() {
			public void run () {
				synchronized (lock2) {
					try {
						lock1.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					int i=0;
					while (i<5) {
						System.out.println("2");
						i++;
					}
					lock2.notify();
				}
			}
		});
		thread1.start();
		thread2.start();
	}
}