package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private GameWorld game;

	private Level level;

	private Random rand;

	private int remainingZombies;

	private int zombiesAlived;

	public ZombiesManager(GameWorld game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombiesAlived = 0;
	}

	// GETTERS

	public int getRemainingZombies() {
		return this.remainingZombies;
	}

	public int getZombiesAlived() {
		return this.zombiesAlived;
	}

	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}

	private int randomZombieRow() {
		return rand.nextInt(GameWorld.NUM_ROWS);
	}

	private int randomZombieType() {
		return rand.nextInt(ZombieFactory.getAvailableZombies().size());
	}

	public void update() throws GameException {
		addZombie();
	}

	// Se añaden cada cierto tiempo

	public boolean addZombie() throws GameException {
		int row = randomZombieRow();
		return addZombie(row);
	}

	// Usado además añadidos por comando

	public boolean addZombie(int row) {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie()
				&& (!game.isFullyOcuppied(GameWorld.NUM_COLS, row));
		int zombieType = randomZombieType();
		if (canAdd) {
			game.addGameObject(ZombieFactory.spawnZombie(zombieType, game, game.NUM_COLS, row));
			this.remainingZombies--;
		}
		return canAdd;
	}

	public boolean addZombie(int row, int col, int zombieIdx) {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie()
				&& (!game.isFullyOcuppied(GameWorld.NUM_COLS, row));
		if (canAdd) {
			game.addGameObject(ZombieFactory.spawnZombie(zombieIdx, game, col, row));
		}
		return canAdd;
	}

	public void killZombie() {
		this.zombiesAlived--;
	}

	public boolean zombiesLose() {
		return (this.zombiesAlived == 0 && this.remainingZombies == 0);
	}

	public void increaseZombiesAlive() {
		this.zombiesAlived++;
	}

}
