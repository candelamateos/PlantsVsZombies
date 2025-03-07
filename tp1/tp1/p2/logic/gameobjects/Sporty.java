package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie {

	private static final int DAMAGE = 1;
	private static final int INITIAL = 2;
	private static final int FREQUENCY = 1;

	
	public Sporty(GameWorld game, int col, int row) {
		super(game, col, row);
		this.lifepoints = this.INITIAL;
		this.zombieIdx = 2;
	}

	public int getLifePoints() {
		return this.lifepoints;
	}

	@Override
	protected int getFrequency() {
		return this.FREQUENCY;
	}
	
	@Override
	public void moveZombie () {
		if (game.isPositionEmpty(col - 1, row) || !game.isFullyOcuppied (this.col - 1, this.row)) {
			if (this.cycles % this.getFrequency() == 0 && this.cycles != 0) {
				this.col--;
			}
		}
	}
	@Override
	public String getName() {
		return Messages.SPORTY_ZOMBIE_NAME;
	}

	@Override
	protected String getIcon() {
		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}
	
	@Override
	public Sporty create(GameWorld game, int col, int row) {
		return new Sporty(game, col, row);
	}

}
