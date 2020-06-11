package bse.core.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Output {


	public static Output of(Optional<Data> data) {
		return new Output(data.stream().collect(Collectors.toList()));
	}
	
	public static Output of(List<Data> list) {
		return new Output(list);
	}
	
	private List<Data> data;

	private Output(List<Data> list) {
		this.data = list;
	}

	public List<Data> getData() {
		return data;
	}

}
