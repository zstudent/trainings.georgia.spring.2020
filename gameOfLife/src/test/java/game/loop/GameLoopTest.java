package game.loop;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameLoopTest {

	@Test
	public void doesNothingIfGameIsNotRunning() {
		TestGame testGame = new TestGame();
		GameLoop uut = new GameLoop();
		
		uut.run();
		
		assertFalse(testGame.isUpdated);
	}
	
	public class TestGame implements Game {
		
		public boolean isUpdated;
		
		public void setRunning(boolean b) {
			
		}
	}

}
