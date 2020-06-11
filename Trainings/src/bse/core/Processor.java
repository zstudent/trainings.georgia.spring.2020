package bse.core;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import bse.core.domain.Data;
import bse.core.domain.Dispatcher;
import bse.core.domain.Input;
import bse.core.domain.Output;

public class Processor implements Provider, Dispatcher {
	
	Storage storage;

	public Processor(Storage storage) {
		this.storage = storage;
	}

	@Override
	public CompletableFuture<Output> request(Input input) {
		CompletableFuture<Output> future = new CompletableFuture<Output>();
		input.accept(this, future);
		return future;
	}

	@Override
	public void visit(Input.Find find, CompletableFuture<Output> future) {
		Optional<Data> data = storage.find(find.getKey());
		future.complete(Output.of(data));
	}

	@Override
	public void visit(Input.FindAll findAll, CompletableFuture<Output> future) {
		List<Data> list = storage.findAll();
		future.complete(Output.of(list));
	}

	

}
