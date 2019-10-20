package mulicupteas.concurrency;

public class DriveACarRideTheBikesAndSailABoatSimultaneously {
	public static void main(String[] args) {

		//Extending the Thread
		Car carThread = new Car();
		Thread bikeThread = new Bike();
		carThread.start();
		bikeThread.start();

		//Implementing Runnable
		Runnable boatRunnable = new Boat();

		//telling thread to execute run method from given Runnable
		Thread boatThread = new Thread(boatRunnable);
		Thread boatAnonymousThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i =  0; i < 2; i++) {
					System.out.println("I am sailing an anonymous boat in ocean" + i);
				}
			}
		});

		//using lamda
		Thread boatLambdaThread = new Thread(() -> {
			for (int i = 0; i < 2; i++) {
				System.out.println("I am sailing an lambda boat in ocean" + i);
			}
		});


		boatThread.setName("Boat1");

		boatThread.start();
		boatAnonymousThread.start();
		boatLambdaThread.start();

	}
}

class Car extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("I am driving a Car on road " + i);
		}
	}
}

class Bike extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("I am riding a Bike on road " + i);
		}
	}
}

class Boat implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println("I am sailing a " + Thread.currentThread().getName() + " in ocean" + i);
		}
	}
}

