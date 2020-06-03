package game.loop;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameLoopTest {

	@Test
	public void doesNothingIfGameIsNotRunning() {
		GameLoop gameLoop = new GameLoop();
	}

}
