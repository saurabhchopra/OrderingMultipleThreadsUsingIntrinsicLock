package com.srb.main.operation;

import java.util.concurrent.atomic.AtomicBoolean;

import com.srb.main.Application;

public class OperationC implements Runnable {

	Application application = null;
	AtomicBoolean firstOperation = null;
	AtomicBoolean secondOperation = null;

	/**
	 * @param application
	 * @param lock
	 * @param firstOperation
	 * @param secondOperation
	 */
	public OperationC(Application application, AtomicBoolean firstOperation, AtomicBoolean secondOperation) {
		this.application = application;
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
			performOperation();
		}
	}

	/**
	 * 
	 */
	private void performOperation() {
		synchronized (application) {
			if (firstOperation.get()) {
				try {
					Thread.sleep(1000);
					firstOperation.set(false);
					System.out.println("Operation C");
					secondOperation.set(true);
					application.notifyAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					application.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
