
package tp1.p2.logic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;
import tp1.p2.control.Records;

public class Game implements GameStatus, GameWorld {

	private static final int INITIAL_SUNCOINS = 50;

	private long seed;

	private Level level;

	private int cycle;

	private GameObjectContainer container;

	private Deque<GameAction> actions;

	private int sunCoins;

	private ZombiesManager zombies;

	private SunsManager suns;

	private boolean playerQuits;

	private boolean zombiesWin;

	private boolean playerWins;

	private int points;

	private boolean isThereANewRecord;

	public Game(long seed, Level level) throws GameException {
		this.seed = seed;
		this.level = level;
		reset();
	}

	// RESET

	public void reset() throws GameException {
		reset(this.level, this.seed);
	}

	@Override
	public void reset(Level level, long seed) throws GameException {
		this.level = level;
		this.seed = seed;
		Random rand = new Random(seed);
		this.zombies = new ZombiesManager(this, level, rand);
		this.suns = new SunsManager(this, rand);
		this.container = new GameObjectContainer();
		suns.setCaughtSuns();
		suns.setGeneratedSuns();
		this.sunCoins = INITIAL_SUNCOINS;
		this.cycle = 0;
		this.actions = new ArrayDeque<>();
		this.points = 0;
		this.isThereANewRecord = false;
		System.out.println(String.format(Messages.CONFIGURED_LEVEL, level.name()));
		System.out.println(String.format(Messages.CONFIGURED_SEED, seed));
	}

	// GETTERS (GameStatus)

	public int getCycle() {
		return this.cycle;
	}

	public int getSuncoins() {
		return this.sunCoins;
	}

	public int getRemainingZombies() {
		return zombies.getRemainingZombies();
	}

	public boolean getzombiesWin() {
		return this.zombiesWin;
	}

	public boolean getPlayerWins() {
		return this.playerWins;
	}

	public boolean isPlayerQuits() {
		return this.playerQuits;
	}

	public Level getlevel() {
		return this.level;
	}

	public long getseed() {
		return this.seed;
	}

	// EXECUTE COMMAND

	public boolean execute(Command command) throws GameException {
		boolean a = command.execute(this);
		return a;
	}

	// UPDATE

	@Override
	public void update() throws GameException {

		executePendingActions();
		Command.newCycle();
		// 3. Game object updates
		zombies.update();
		suns.update();
		container.update();

		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.container.removeDead();

			// 5. execute pending actions
			executePendingActions();
		}

		this.isFinished();
		this.cycle++;

		// 7. Update record

		if (this.points > 0) {
			for (Level l : Level.values()) {
				if (l.toString().equals(this.level.toString())) {
					if (this.points > Records.getForLevel(l))
						this.isThereANewRecord = true;
				}
			}
		}

	}

	@Override
	public boolean isPositionEmpty(int col, int row) {
		if (container.findObjects(col, row).size() == 0)
			return true;
		else
			return false;
	}

	@Override
	public List<GameItem> getGameObjectsinPos(int col, int row) {
		return container.findObjects(col, row);
	}

	@Override
	public void increasesunCoins() {
		this.sunCoins += 10;
	}

	// ADD GAMEOBJECTS
	@Override
	public void addGameObject(GameObject g) {
		container.add(g);
		g.onEnter();
	}

	@Override
	public void tryToBuy(int cost) throws GameException {
		if (cost > this.sunCoins)
			throw new CommandExecuteException(Messages.NOT_ENOUGH_COINS);
		else
			this.sunCoins = this.sunCoins - cost;
	}

	// EXPLOSION

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
			this.container.removeDead();
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}

	@Override
	public void addPendingAction(GameAction action) {
		actions.add(action);
	}

	public String positionToString(int col, int row) {
		return container.positionToString(col, row);
	}

	public void zombieArrivesHome() {
		this.zombiesWin = true;
	}

	public boolean isFinished() {
		if (this.zombiesWin) {
			return true;
		} else if (zombies.zombiesLose()) {
			this.playerWins = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean isFullyOcuppied(int col, int row) {
		return this.container.isFullyOccupied(col, row);
	}

	@Override
	public void setPlayerQuits() {
		this.playerQuits = true;
	}

//OnEnter y OnExit de los zombies
	@Override
	public void killZombie() {
		zombies.killZombie();
	}

//SUNS
	@Override
	public void addSun() {
		suns.addSun();
	}

	@Override
	public void catchObject(int col, int row) throws GameException {
		container.catchObject(col, row);
	}

	@Override
	public int getGeneratedSuns() {
		return suns.getGeneratedSuns();
	}

	@Override
	public int getCaughtSuns() {
		return suns.getCaughtSuns();
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void increasePoints(int points) {
		this.points += points;
	}

	@Override
	public void increaseZombiesAlive() {
		zombies.increaseZombiesAlive();
	}

		// Gestión record
	public void setRecord() {
		Records.update(this.level,  this.points);
	}

	@Override
	public boolean isThereANewRecord() {
		return isThereANewRecord;
	}
}