package lesson200324;

public final class IvoryTower {
	
	static int count = 0;

	static {
		System.out.println("static init block");
	}
	
	{
		System.out.println("instance init block");
	}

	private IvoryTower() {
		System.out.println("constructor");
	}

	/**
	 * Static to class instance of the class.
	 */
	private static final IvoryTower INSTANCE = new IvoryTower();

	/**
	 * To be called by user to obtain instance of the class.
	 *
	 * @return instance of the singleton.
	 */
	public static IvoryTower getInstance() {
		System.out.println("getInstance invocation");
		return INSTANCE;
	}
}