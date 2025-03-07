package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant {

	private final static int COST = 20;
	private final static int INITIAL = 1;
	private final static int DAMAGE = 0;
	private final static int FREQUENCY = 3;

	public Sunflower(GameWorld game, int col, int row) {
		super(game, col, row);
		this.lifepoints = INITIAL;
	}

	private static int numberOfSunflowers = 0;

	public int getlifePoints() {
		return this.lifepoints;
	}

	public int getCost() {
		return COST;
	}

	public int getInitial() {
		return INITIAL;
	}

	public int getDamage() {
		return DAMAGE;
	}

	public int getFrequency() {
		return FREQUENCY;
	}

	public String getName() {
		return Messages.SUNFLOWER_NAME;
	}

	protected String getIcon() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	@Override
	public String getShortcut() {
		return Messages.SUNFLOWER_NAME_SHORTCUT;
	}

	public boolean isInPosition(int col, int row) {
		return this.getCol() == col && this.getRow() == row;
	}

	public static void addSunflower() {
		numberOfSunflowers++;
	}

	public Sunflower create(GameWorld game, int col, int row) {
		return new Sunflower(game, col, row);
	}

	public static void deleteSunflower() {
		numberOfSunflowers--;
	}

	public static void setNumberOfSunflowers(int tam) {
		numberOfSunflowers = tam;
	}

	public static int getNumberOfSunflowers() {
		return numberOfSunflowers;
	}

	public void killSunflower() {
		this.lifepoints = 0;
	}

	@Override
	public void update() {
		if (this.cycles % this.FREQUENCY == 0 && this.cycles != 0 && this.cycles != 2)
			addSun();
		this.cycles++;
	}

	public void addSun() {
		game.addSun();
	}

	@Override
	public boolean receivePlantAttack(int damage,  boolean explosion) {
		return false;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {
		this.lifepoints = this.lifepoints - damage;
		return true;
	}

}