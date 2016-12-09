package pl.awisoft;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	private static Random RND = new Random();
	
	public static void main(String[] args) {
		System.out.println("This is enty point to the main app");
		
		executeTasks();
		
		System.out.println("All threads finished executing.");
	}

	/**
	 * Executes threads
	 */
	private static void executeTasks() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
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
	        
	        executor.submit(task);
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			System.err.println("Failed joining executors.");
		}
	}

}
