package bse;

import java.util.concurrent.ExecutionException;

import bse.core.Processor;
import bse.core.Provider;
import bse.core.Storage;
import bse.core.domain.Input;

public class BSE {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Storage storage = new MapStorage();
		
		Provider provider = new Processor(storage);
		
		var result = provider.request(new Input.Find("One"));
		System.out.println(result.get().getData());

		result = provider.request(new Input.FindAll());
		System.out.println(result.get().getData());
		                
	}

}
