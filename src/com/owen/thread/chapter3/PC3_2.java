package com.owen.thread.chapter3;

public class PC3_2
{
	public static void main(String[] args)
	{
		Shared3_2 s = new Shared3_2();
		new Producer3_2(s).start();
		new Consumer3_2(s).start();
	}
}

class Shared3_2
{
	private char c;
	private volatile boolean writeable = true;

	synchronized void setSharedChar(char c)
	{
		while (!writeable)
			try
			{
				wait();
			} catch (InterruptedException ie)
			{
			}
		this.c = c;
		writeable = false;
		notify();
	}

	synchronized char getSharedChar()
	{
		while (writeable)
			try
			{
				wait();
			} catch (InterruptedException ie)
			{
			}
		writeable = true;
		notify();
		return c;
	}
}

class Producer3_2 extends Thread
{
	private final Shared3_2 s;

	Producer3_2(Shared3_2 s)
	{
		this.s = s;
	}

	@Override
	public void run()
	{
		for (char ch = 'A'; ch <= 'Z'; ch++)
		{
			synchronized (s)
			{
				
				s.setSharedChar(ch);
				System.out.println(ch + " produced by producer.");
			}
		}
	}
}

class Consumer3_2 extends Thread
{
	private final Shared3_2 s;

	Consumer3_2(Shared3_2 s)
	{
		this.s = s;
	}

	@Override
	public void run()
	{
		char ch;
		do
		{
			synchronized (s)
			{
				
				ch = s.getSharedChar();
				System.out.println(ch + " consumed by consumer.");
			}
		} while (ch != 'Z');
	}
}