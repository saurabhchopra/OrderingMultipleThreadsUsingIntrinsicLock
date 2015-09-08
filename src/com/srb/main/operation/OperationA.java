package com.srb.main.operation;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

public class OperationA implements Runnable {

	Lock lock = null;
	AtomicBoolean firstOperation = null;
	AtomicBoolean secondOperation = null;

	/**
	 * @param lock
	 * @param firstOperation
	 * @param secondOperation
	 */
	public OperationA(Lock lock, AtomicBoolean firstOperation, AtomicBoolean secondOperation) {
		this.lock = lock;
		this.firstOperation = firstOperation;
		this.secondOperation = secondOperation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			performOperationWithLock();
		}
	}

	/**
	 * 
	 */
	private void performOperationWithLock() {
		if (firstOperation.get()) {
			try {
				lock.lock();
				Thread.sleep(1000);
				firstOperation.set(false);
				System.out.println("Operation A");
				secondOperation.set(true);
				notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
