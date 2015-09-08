package com.srb.main;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.srb.main.operation.OperationA;
import com.srb.main.operation.OperationB;
import com.srb.main.operation.OperationC;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		AtomicBoolean operationA = new AtomicBoolean(true);
		AtomicBoolean operationB = new AtomicBoolean(false);
		AtomicBoolean operationC = new AtomicBoolean(false);

		Thread threadA = new Thread(new OperationA(lock, operationA, operationB));
		Thread threadB = new Thread(new OperationB(lock, operationB, operationC));
		Thread threadC = new Thread(new OperationC(lock, operationC, operationA));
		threadA.start();
		threadB.start();
		threadC.start();
	}

}
