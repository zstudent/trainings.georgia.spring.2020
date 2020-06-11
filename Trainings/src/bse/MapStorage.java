package bse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import bse.core.Storage;
import bse.core.domain.Data;

public class MapStorage implements Storage {
	
	Map<String, Data> map;
	
	// instance initializer 
	{
		map = Map.of("One", new Data(), "Two", new Data());
		
	}

	@Override
	public Optional<Data> find(String key) {
		return Optional.ofNullable(map.get(key));
	}

	@Override
	public List<Data> findAll() {
		return new ArrayList<Data>(map.values());
	}

}
