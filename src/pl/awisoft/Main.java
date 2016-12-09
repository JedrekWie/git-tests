package pl.awisoft;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	private static Random RND = new Random();
	
	public static void main(String[] args) {
		System.out.println("This is enty point to the main app");
		
		int tCount = 10;
		if(args.length > 0 && args[0] != null) {
			tCount = Integer.parseInt(args[0]);
		}
		
		executeTasks(tCount);
		
		System.out.println("All threads finished executing.");
	}

	/**
	 * Executes threads
	 */
	private static void executeTasks(int tCount) {
		System.out.println("Starting " + tCount + " threads.");
		
		ExecutorService executor = Executors.newFixedThreadPool(tCount);
		
		for(int i = 0; i < tCount; i++) {
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
