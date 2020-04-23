package lesson200422;

import java.util.LinkedList;
import java.util.Queue;

import utils.Utils;

public class WorkerThread2 implements Runnable {
	
	Queue<Runnable> tasks = new LinkedList<>();

	@Override
	public void run() {
		System.out.println("starting " + this);
		long c = 0;
		while (true) {
			Runnable task = null;
			synchronized (this) {
				// here the thread reads all from global memory into local
				task = tasks.poll();
			}
			if (task != null) 
				task.run();
		}
	}
	
	public void execute(Runnable task) {
		synchronized (this) {
			tasks.add(task);
		}
	}
	
}
