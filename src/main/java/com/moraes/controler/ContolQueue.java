package com.moraes.controler;

import java.util.List;
import java.util.Vector;

import com.moraes.worker.ParseData;

public class ContolQueue {

	private static List<String> TaskQueue;
	private static boolean lendoDados = true;
	
	

	public ContolQueue() {
		TaskQueue = new Vector<String>();
		new Thread(new ParseData("Thread 01")).start();
		new Thread(new ParseData("Thread 02")).start();
		new Thread(new ParseData("Thread 03")).start();
		new Thread(new ParseData("Thread 04")).start();
		
		this.receivesData();
	}

	public static synchronized String getTask() {
		if (TaskQueue.size() > 0)
			return TaskQueue.remove(0);
		return null;
	}
	
	
	private void addTask(String task) {
		TaskQueue.add(task);
	}
	
	
	
	public static boolean isTerminated() {
		
		return lendoDados || TaskQueue.size() > 0;
		
	}
	
	
	
	private void receivesData() {
		int qtdeTotalRegistro = 100;
		int qtdeRegistros = 0;
		do {
			
			qtdeRegistros++;
			String task = "Task "+ qtdeRegistros;
			addTask(task);
			System.out.println(task);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(qtdeRegistros <= qtdeTotalRegistro );
		this.lendoDados = false;
	}

}
