package com.zhixinzhoubian.net;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CoreThread extends Thread {

	private boolean isStop = false;

	private BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(50);

	@Override
	public void run() {

		while(!isStop){
			try {
				
				Runnable task = taskQueue.take();
				task.run();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		super.run();
	}

	public void addTask(Runnable task) {
		try {
			
			this.taskQueue.put(task);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
			try {
				
				Thread.sleep(500);
				this.taskQueue.put(task);
				
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
			
		}
	}
	
	public void stopThread(){
		isStop = true;
	}
	

}
