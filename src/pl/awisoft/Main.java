package pl.awisoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	private static Random RND = new Random();
	
	public static void main(String[] args) {
		System.out.println("This is enty point to the main app");
		
		List<Thread> threads = new ArrayList<>();

		for(int i = 0; i < 10; i++) {
			// Lambda Runnable
	        Runnable task = () -> {
	            String name = Thread.currentThread().getName();
				System.out.println(name + " is running");
				long sleep = (long) (RND.nextDouble() * 10000);
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					// Suppress
				}
				System.out.println(name + " finished running (sleep time: "
						+ sleep + ")");
	        };
	        
	        Thread t = new Thread(task);
	        threads.add(t);
	        t.start();
		}
		
		for(Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				System.err.println("Failed joining thread " + t.getName());
			}
		}
	}

}
