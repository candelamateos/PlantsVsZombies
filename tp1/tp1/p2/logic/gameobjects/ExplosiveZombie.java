package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionbyZombie;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie {
	
	private static final int DAMAGE = 1;
	private static final int EXPLOSION_DAMAGE = 3;
	
	public ExplosiveZombie(GameWorld game, int col, int row) {
		super(game, col, row);
		this.zombieIdx = 3;
	}
	

	public int getLifePoints() {
		return this.lifepoints;
	}

	public int getDamage() {
		return DAMAGE;
	}
	
	public String getName() {
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}

	@Override
	protected String getIcon() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}

	
	@Override
	public void onExit() {
		super.onExit();
		ExplosionbyZombie E = new ExplosionbyZombie(this.col, this.row, EXPLOSION_DAMAGE);
		game.addPendingAction(E);
	}


	//Creates an explosive Zombie
	@Override
	public ExplosiveZombie create(GameWorld game, int col, int row) {
		return new ExplosiveZombie(game, col, row);
	}
	
}
