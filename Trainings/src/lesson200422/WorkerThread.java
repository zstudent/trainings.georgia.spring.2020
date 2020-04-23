package lesson200422;

import java.util.LinkedList;
import java.util.Queue;

import utils.Utils;

public class WorkerThread implements Runnable {
	
	Queue<Runnable> list = new LinkedList<>();

	@Override
	public void run() {
		System.out.println("starting " + this);
		long c = 0;
		while (true) {
			Runnable task = list.poll();
			c++;
			if (c % 100_000_000 == 0)
//				System.out.println(c);
			if (task != null) 
				task.run();
		}
	}
	
	public void execute(Runnable task) {
		list.add(task);
	}
	
}
