
package tp1.p2.logic.gameobjects;

import java.util.List;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant {

	private final static int COST = 50;
	private static final int DAMAGE = 1;
	private static final int INITIAL = 3;
	private static final int FREQUENCY = 1;

	public Peashooter(GameWorld game, int col, int row) {
		super(game, col, row);
		this.lifepoints = INITIAL;
	}

	@Override
	public int getCost() {
		return COST;
	}

	@Override
	protected int getInitial() {
		return INITIAL;
	}

	@Override
	protected int getDamage() {
		return DAMAGE;
	}

	@Override
	protected int getlifePoints() {
		return this.lifepoints;
	}

	@Override
	public String getName() {
		return Messages.PEASHOOTER_NAME;
	}

	protected String getIcon() {
		return Messages.PEASHOOTER_SYMBOL;
	}

	@Override
	public String getShortcut() {
		return Messages.PEASHOOTER_NAME_SHORTCUT;
	}

	protected int getFrequency() {
		return FREQUENCY;
	}

	// METHODS

	public boolean isPeashooterAlive() {
		return this.lifepoints > 0;
	}

	public void killPeashooter() {
		this.lifepoints = 0;
	}

	public Peashooter create(GameWorld game, int col, int row) {
		return new Peashooter(game, col, row);
	}

	public boolean PeashooterAttack(int col, int row) {
		List<GameItem> list = game.getGameObjectsinPos(col, row);
		boolean attack = false;
		for (GameItem g : list)
		if (g != null) {
			if (g.receivePlantAttack(this.DAMAGE, false))
				attack = true;
		}
		return attack;
	}

	@Override
	public void update() {
		int i = this.col + 1;
		while (i < game.NUM_COLS && !PeashooterAttack(i, row)) {
			i++;
		}
		this.cycles++;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {
		this.lifepoints = this.lifepoints - damage;
		return true;
	}

	@Override
	public boolean receivePlantAttack(int damage,  boolean explosion) {
		return false;
	}

}