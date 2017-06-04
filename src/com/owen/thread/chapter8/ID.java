package com.owen.thread.chapter8;

import java.util.concurrent.atomic.AtomicLong;

public class ID
{

	private static AtomicLong nextID = new AtomicLong(1);
	static long getNextID()
	{
	return nextID.getAndIncrement();
	}
}
