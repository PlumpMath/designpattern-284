public class W4 extends Thread {
	private String ring;
	private static Object lock = new Object();	// lock should be static so that t1 & t2 will share the same object.
	private static int i = 0;

	public W4 (String ring) {
		super();
		this.ring = ring;
	}

	public void run () {
		synchronized (lock) {
			while (i<10) {
				System.out.println(i);
				System.out.println(this.ring);
				if (i > 0) {
					lock.notify();	// next thread will not enter the synchronized block after notify function, it is just waked up and ready to run.
				}
				i++;
				if (i<10) {
					try {
						lock.wait();	// after call wait function, next waked up thread will enter this synchronized block
					} catch (Exception e) {	
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main (String[] args) {
		System.out.println(Thread.currentThread().getName());
		Thread t1 = new W4("ping");
		Thread t2 = new W4("pong");
		t1.start();	// one object of Thread class could not call the start() function for more than one time
		t2.start();
	}
}