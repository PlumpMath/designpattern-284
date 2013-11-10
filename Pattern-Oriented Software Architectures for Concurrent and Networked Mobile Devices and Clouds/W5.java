import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class W5 implements Runnable {
	private static Lock[] locks = new ReentrantLock[5];
	private static Condition[] notInUsingCondition = new Condition[5];
	private static boolean[] isInUsing = new boolean[5];
	static {
		for (int i=0; i<5; i++) {
			isInUsing[i] = false;
			locks[i] = new ReentrantLock();
			notInUsingCondition[i] = locks[i].newCondition();
		}
	}
	private int id;
	public W5 (int id) {
		this.id = id;
	}

	public void pickUpChopstick (int chopstickid) throws InterruptedException {
		locks[chopstickid].lock();
		try {
			while (isInUsing[chopstickid]) {
				notInUsingCondition[chopstickid].await();
			}
			String chopstickSide = "left";
			if (chopstickid == (id+1)%5)
				chopstickSide = "right";
			System.out.println("Philosopher " + (id+1) + " picks up " + chopstickSide + " chopstick.");
			isInUsing[chopstickid] = true;
		} finally {
			locks[chopstickid].unlock();
		}
	}

	public void putsDownChopstick (int chopstickid) {
		locks[chopstickid].lock();
		String chopstickSide = "left";
		if (chopstickid == (id+1)%5)
			chopstickSide = "right";
		System.out.println("Philosopher " + (id+1) + " puts down " + chopstickSide + " chopstick.");
		isInUsing[chopstickid] = false;
		notInUsingCondition[chopstickid].signal();
		locks[chopstickid].unlock();
	}

	public void run () {
		try {
			pickUpChopstick((id+(id%2))%5);
			pickUpChopstick((id+(id+1)%2)%5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Philosopher " + (id+1) + " eats");
		putsDownChopstick((id+(id%2))%5);
		putsDownChopstick((id+(id+1)%2)%5);
	}

	public static void main (String args[]) {
		Thread[] thread = new Thread[5];
		for (int j=0; j<3; j++) {
			for (int i=0; i<5; i++) {
				thread[i] = new Thread(new W5(i));
				thread[i].start();
			}
		}
	}
}