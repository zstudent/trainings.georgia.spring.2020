package lesson200415;

@FunctionalInterface
public interface Broadcaster {
	// functional description:  String - > ()  T -> ()
	void broadcast(String message);

}
