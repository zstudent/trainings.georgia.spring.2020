package bse.core.domain;

import java.util.concurrent.CompletableFuture;

import bse.core.domain.Input.Find;
import bse.core.domain.Input.FindAll;

public interface Dispatcher {

	void visit(Find find, CompletableFuture<Output> future);

	void visit(FindAll findAll, CompletableFuture<Output> future);

}
