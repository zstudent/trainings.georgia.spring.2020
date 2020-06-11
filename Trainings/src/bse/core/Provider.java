package bse.core;

import java.util.concurrent.CompletableFuture;

import bse.core.domain.Input;
import bse.core.domain.Output;

public interface Provider {
	
	CompletableFuture<Output> request(Input input);

}
