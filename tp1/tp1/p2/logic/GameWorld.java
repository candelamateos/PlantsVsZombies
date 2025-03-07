
package tp1.p2.logic;

import java.util.List;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.logic.gameobjects.ZombieFactory;

public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	PlantFactory Plants = new PlantFactory();
	ZombieFactory Zombies = new ZombieFactory();
	ZombiesManager ZombiesManager = null;

	
	//Resets the game with parameters
	
	public void reset(Level level, long seed) throws GameException;
	
	//Resets the game
	
	public void reset() throws GameException;
	
	//Executes the game
	
	public boolean isPlayerQuits();
	
	public void  setPlayerQuits();
	
	void tryToBuy(int cost) throws GameException;
	

	/**
	 * Executes the game actions and update the game objects in the board.
	 * @throws GameException 
	 * 
	 */
	void update() throws GameException;

	public int getSuncoins();

	public boolean isPositionEmpty(int i, int row);

	public List<GameItem> getGameObjectsinPos(int i, int row);

	public void increasesunCoins();

	public void addGameObject(GameObject g);

	public void killZombie();

	public Level getlevel();

	public long getseed();

	void addPendingAction(GameAction action);

	public void addSun();

	public void catchObject(int col, int row) throws GameException;
	
	boolean isFullyOcuppied(int col, int row);

	public void increasePoints(int points);

	public void zombieArrivesHome();

	public void increaseZombiesAlive();
	
}