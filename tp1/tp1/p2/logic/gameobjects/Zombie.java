
package tp1.p2.logic.gameobjects;

import java.util.List;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Zombie extends GameObject {

	private static final int DAMAGE = 1;
	private static final int INITIAL = 5;
	private static final int FREQUENCY = 2;
	private static final int POINTS = 10;

	protected int zombieIdx = 0;
	protected boolean byexplosion = false;

	public Zombie(GameWorld game, int col, int row) {
		super(game, col, row);
		this.lifepoints = this.INITIAL;
	}

	protected int getFrequency() {
		return FREQUENCY;
	}

	protected int getDamage() {
		return DAMAGE;
	}

	protected int getPoints() {
		return POINTS;
	}

	@Override
	public String getShortcut() {
		return Messages.ZOMBIE_SYMBOL;
	}

	@Override
	public String getName() {
		return Messages.ZOMBIE_NAME;
	}

	@Override
	public String getSymbol() {
		return Messages.status(getIcon(), getLifePoints());
	}

	protected String getIcon() {
		return Messages.ZOMBIE_SYMBOL;
	}

	public String getDescription(Zombie z) {
		return Messages.zombieDescription(z.getName(), z.getFrequency(), z.getDamage(), z.getLifePoints());
	}

	// METHODS

	public boolean isZombieAlive() {
		return this.lifepoints > 0;
	}

	public void killZombie() {
		this.lifepoints = 0;
	}

	public String ZombieinPosString(int col, int row) {
		if (this.getCol() == col && this.getRow() == row)
			return Messages.ZOMBIE_SYMBOL.formatted(INITIAL);
		else
			return "";
	}

	@Override
	public boolean isAlive() {
		return (this.lifepoints > 0);
	}

	// Crea un zombie común
	@Override
	public Zombie create(GameWorld game, int i, int row) {
		Zombie z = new Zombie(game, i, row);
		return z;
	}

	// GAMEITEM

	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage, boolean explosion) {
		this.lifepoints = this.lifepoints - damage;
		if (explosion) this.byexplosion = true;
		return true;
	}

	public void moveZombie() {
		if (game.isPositionEmpty(col - 1, row) || !game.isFullyOcuppied (this.col - 1, this.row)) {
			if (this.cycles % this.getFrequency() == 0 && this.cycles != 0 && this.cycles != 1) {
				this.col--;
			}
		}
	}

	public void update() {
		moveZombie();
		if (game.getGameObjectsinPos(col - 1, row) != null) {
			List<GameItem> objectsInPos;
			objectsInPos = game.getGameObjectsinPos(col - 1, row);
			for (GameItem g : objectsInPos)
				g.receiveZombieAttack(this.DAMAGE);
		}
		if (this.col == -1) {
			game.zombieArrivesHome();
		}
		this.cycles++;
	}

	@Override
	public void onEnter() {
		game.increaseZombiesAlive();
	}

	@Override
	public void onExit() {
		game.killZombie();
		game.increasePoints(this.getPoints());
	}

	@Override
	public boolean byExplosion() {
		return this.byexplosion;
	}

}