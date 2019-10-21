package multicupteas.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	public static void main(String[] args) {
		//To match the output of the post, call the following methods onw by one by commenting other.
//		newSingleThreadExecutor();
//		newFixedThreadPool();
//		newCachedThreadPool();
	}

	static void pring5Times(String statement) {
		for (int i = 0; i < 5; i++) {
			System.out.println(statement + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * usage of Executors.newSingleThreadExecutor().
	 * This will reuse the single thread to all the task.
	 */
	static void newSingleThreadExecutor() {
		System.out.println("Running Executors.newSingleThreadExecutor()");
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		singleThreadPool.execute(new Car1());
		// bike and boat will wait till car driving is completed.
		singleThreadPool.execute(new Bike1());
		singleThreadPool.execute(new Boat1());

		//make sure to shitdown.
		singleThreadPool.shutdown();
	}

	/**
	 *usage of Executors.newFixedThreadPool(int size)
	 *
	 * This will use the 2 threads at the same time.
	 */
	static void newFixedThreadPool(){
		System.out.println("Running Executors.newFixedThreadPool(2)");
		ExecutorService fixedSizeThreadPool = Executors.newFixedThreadPool(2);
		fixedSizeThreadPool.execute(new Car1());
		fixedSizeThreadPool.execute(new Bike1());
		// this time Since threadpool only has 2 thread,
		// Boat will wait till car driving and bike riding is completed.
		fixedSizeThreadPool.execute(new Boat1());

		fixedSizeThreadPool.shutdown();
	}

	/**
	 * usage of Executors.newCachedThreadPool()
	 * Thread assignment will not wait for a thread to availability, It will create a new one if not available.
	 */
	static void newCachedThreadPool(){
		System.out.println("Running Executors.newCachedThreadPool()");
		ExecutorService chachedThreadPool = Executors.newCachedThreadPool();
		chachedThreadPool.execute(new Car1());
		chachedThreadPool.execute(new Bike1());
		chachedThreadPool.execute(new Boat1());

		chachedThreadPool.shutdown();
	}
}

class Car1 implements Runnable {
	@Override
	public void run() {
		ThreadPool.pring5Times("I am driving a Car on road ");
	}
}

class Bike1 implements Runnable {
	@Override
	public void run() {
		ThreadPool.pring5Times("I am riding a Bike on road ");
	}
}

class Boat1 implements Runnable {
	@Override
	public void run() {
		ThreadPool.pring5Times("I am Sailing the boat on sea ");
	}
}

