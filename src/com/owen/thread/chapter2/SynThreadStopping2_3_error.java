package com.owen.thread.chapter2;

public class SynThreadStopping2_3_error
{
	public static void main(String[] args)
	{
		class StoppableThread extends Thread
		{
			private boolean stopped; // defaults to false

			public void run()
			{
			 synchronized(this)
			{
			   while(!stopped)
			  System.out.println("running");
			 }
			}
			synchronized void stopThread()
			{
				stopped = true;
			}
		}
		StoppableThread thd = new StoppableThread();
		thd.start();
		try
		{
			Thread.sleep(1000); // sleep for 1 second
		} catch (InterruptedException ie)
		{
		}
		thd.stopThread();
	}
}
