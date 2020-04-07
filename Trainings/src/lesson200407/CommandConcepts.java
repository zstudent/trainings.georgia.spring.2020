package lesson200407;

import java.util.Arrays;
import java.util.List;

import lesson200407.Command.Shrink;

public class CommandConcepts {

	public static void main(String[] args) {
		Goblin a = new Goblin();
		a.shrink();
		a.invisible();
		a.flyable();
		a.invisible();
		a.shrink();

		List<Command> actions = Arrays.asList(new Command.Shrink(), new Command.Flyable(), new Command.Invisible());
		
		process(a, actions);
		
		Shrink shrink = new Command.Shrink();

	}

	private static void process(Goblin a, List<Command> actions) {
		actions.forEach(action -> action.execute(a));
	}

}

class Goblin {
	void shrink() {
	}

	void invisible() {
	}

	void flyable() {
	}
}

abstract class Command {
	static class Shrink extends Command {
		@Override
		void execute(Goblin goblin) {
			System.out.println("shrinks");
		}
	}

	static class Invisible extends Command {
		@Override
		void execute(Goblin goblin) {
			System.out.println("makes invisible");
		}
	}

	static class Flyable extends Command {
		@Override
		void execute(Goblin goblin) {
			System.out.println("makes flyable");
		}
	}

	abstract void execute(Goblin goblin);
}
