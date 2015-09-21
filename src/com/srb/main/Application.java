package com.srb.main;

import java.util.concurrent.atomic.AtomicBoolean;

import com.srb.main.operation.OperationA;
import com.srb.main.operation.OperationB;
import com.srb.main.operation.OperationC;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application application = new Application();
		AtomicBoolean operationA = new AtomicBoolean(true);
		AtomicBoolean operationB = new AtomicBoolean(false);
		AtomicBoolean operationC = new AtomicBoolean(false);
		
		Thread threadA = new Thread(new OperationA(application, operationA, operationB));
		Thread threadB = new Thread(new OperationB(application, operationB, operationC));
		Thread threadC = new Thread(new OperationC(application, operationC, operationA));
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
