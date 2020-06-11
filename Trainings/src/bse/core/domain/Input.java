package bse.core.domain;

import java.util.concurrent.CompletableFuture;

public abstract class Input {

	abstract public void accept(Dispatcher dispatcher, CompletableFuture<Output> future);
	
	public static class Find extends Input {
		
		String key;

		public Find(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		@Override
		public void accept(Dispatcher dispatcher,
				CompletableFuture<Output> future) {
			dispatcher.visit(this, future);
		}
		
	}

	public static class FindAll extends Input {
		
		@Override
		public void accept(Dispatcher dispatcher,
				CompletableFuture<Output> future) {
			dispatcher.visit(this, future);
		}
		
	}
	
}
