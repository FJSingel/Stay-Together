package com.reduber.backend.base;

public class Util {

	public static long ns = 1;
	public static long us = 1000;
	public static long ms = 1000000;
	public static long s = 1000000000;
	
	/**
	 * Blocks thread as long as signal is equal to invalid.
	 * @param monitor monitor of object to compare to invalid.
	 * @param invalid object to compoare to signal.
	 * @param timeout timeout in nanoseconds.
	 * @throws InterruptedException if timeout is reached.
	 */
	public static void block(Monitor monitor, Object invalid, long timeout) throws InterruptedException{
		long time = System.nanoTime();
		long elapsed = 0;
		while(monitor.target() == invalid){
			long t = System.nanoTime();
			elapsed += t - time;
			time = t;
			if (elapsed > timeout)
				throw new InterruptedException("Blocking Operation timed out after " + elapsed*0.000001 + "ms.");
			Thread.sleep(16);
		}
	}
	
}
