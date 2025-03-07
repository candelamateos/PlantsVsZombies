package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant{
	
	private static final int INITIAL = 10;
	private static final int COST = 50;
	
	public WallNut(GameWorld game, int col, int row) {
		super(game, col, row);
		this.lifepoints = INITIAL;
	}

	@Override
	public boolean receivePlantAttack(int damage,  boolean explosion) {
		return false;
	}

	@Override
	public int getCost() {
		return this.COST;
	}

	@Override
	protected int getInitial() {
		return this.INITIAL;
	}

	@Override
	protected int getlifePoints() {
		return this.lifepoints;
	}

	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}

	@Override
	protected String getIcon() {
		return Messages.WALL_NUT_SYMBOL;
	}
	
	@Override
	public String getShortcut() {
		return Messages.WALL_NUT_NAME_SHORTCUT;
	}

	@Override
	public Plant create(GameWorld game, int col, int row) {
		return new WallNut(game, col, row); 
	}

	@Override
	public void update() {
		this.cycles++;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {
		this.lifepoints = this.lifepoints - damage;
		return true;
	}

	@Override
	protected int getDamage() {
		return 0;
	}
}
