package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionbyPlant;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant{

	private static final int endurance = 2;
	private static final int cost = 50;
	private static final int INITIAL = 2;
	private static final int EXPLOSION_DAMAGE = 10;

	private ExplosionbyPlant explosion;

	public CherryBomb(GameWorld game, int col, int row) {
		super(game, col, row);
		this.lifepoints = INITIAL;
		this.explosion = new ExplosionbyPlant(col, row, EXPLOSION_DAMAGE);
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	protected int getInitial() {
		return this.INITIAL;
	}

	@Override
	public int getDamage() {
		return this.EXPLOSION_DAMAGE;
	}

	@Override
	protected int getlifePoints() {
		return this.lifepoints;
	}

	@Override
	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}

	@Override
	protected String getIcon() {
		if (this.cycles == 2) {
		return Messages.CHERRY_BOMB_SYMBOL.toUpperCase();
		}
		else
			return Messages.CHERRY_BOMB_SYMBOL;
	}

	@Override
	public String getShortcut() {
		return Messages.CHERRY_BOMB_NAME_SHORTCUT;
	}

	@Override
	public void update() {
		if (this.cycles == 2) {
			this.cycles = 0;
			this.lifepoints = 0;
		}
		this.cycles++;
	}

	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}

	public boolean isCherryBombAlive() {
		return this.lifepoints > 0;
	}

	public void killCherryBombs() {
		this.lifepoints = 0;
	}

	public CherryBomb create(GameWorld game, int col, int row) {
		return new CherryBomb(game, col, row); 
	}

	@Override
	public boolean receivePlantAttack(int damage, boolean explosion) {
		return false;
	}
	
	@Override
	public boolean catchObject() {
		return false;
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		this.lifepoints = this.lifepoints - damage;
		return true;
	}
	
	private void explosion() {
		explosion.execute(this.game);
	}
	
	@Override
	public void onExit() {
		game.addPendingAction(new ExplosionbyPlant(col, row, EXPLOSION_DAMAGE));
	}

}
