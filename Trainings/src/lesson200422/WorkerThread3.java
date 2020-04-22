package lesson200422;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkerThread3 implements Runnable {

	BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();

	@Override
	public void run() {
		System.out.println("starting " + this);
		long c = 0;
		while (true) {
			Runnable task = null;
			try {
				task = tasks.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (task != null)
				task.run();
		}
	}

	public void execute(Runnable task) {
		tasks.offer(task);
	}

}
