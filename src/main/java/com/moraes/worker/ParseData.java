package com.moraes.worker;

import com.moraes.controler.ContolQueue;

public class ParseData implements Runnable{
	private String nameThread;

	 public ParseData(String nameThread) {
		this.nameThread = nameThread;
	}
	
	
	@Override
	public void run() {
		do {
			
			String task = ContolQueue.getTask();
			
			if(task == null) {
				synchronized (this) {
					try {
						this.wait(500);
						System.out.println(this.nameThread + " foi para Wait.");
					} catch (InterruptedException e) {
						System.out.println("ParseDara: " + e.getMessage());
					}
				}
			}else {
				System.out.println("Parse realizado pela thread "+ this.nameThread );
			}
						
		}while(ContolQueue.isTerminated());
		System.out.println(nameThread + " terminou");
	}

}
