package lesson200408;

import java.util.function.Consumer;

public class UseCats {
	
	public static void main(String[] args) {
		CatData cat = new CatData();
		
		CatFunctions.fall(cat);
		CatFunctions.feed(cat);
		CatFunctions.timePasses(cat);
		
		CatFunctions.change(cat, CatFunctions::fall);
		
		CatFunctions.change(CatData::new, CatFunctions::fall);
		
		// consumers
		CatFunctions.change(cat, CatFunctions::fall);
		CatFunctions.change(cat, c -> System.out.println(c));
		CatFunctions.change(cat, new Consumer<CatData>() {
			@Override
			public void accept(CatData c) {
				System.out.println(c);
			}
		});
		CatFunctions.change(cat, new CatProcessor());
		
	}

}


class CatProcessor implements Consumer<CatData> {
	@Override
	public void accept(CatData c) {
		System.out.println(c);
	}
}






