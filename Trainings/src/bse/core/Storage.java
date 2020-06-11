package bse.core;

import java.util.List;
import java.util.Optional;

import bse.core.domain.Data;

public interface Storage {
	
	Optional<Data> find(String key);
	List<Data> findAll();

}
