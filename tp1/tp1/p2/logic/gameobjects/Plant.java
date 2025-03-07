package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public abstract class Plant extends GameObject {

	public Plant(GameWorld game, int col, int row) {
		super(game, col, row);
	}


	public String getDescription(Plant p) {
		return Messages.plantDescription(p.getShortcut(), p.getCost(), p.getDamage(), p.getLifePoints());
	}

	public String getSymbol() {
		return Messages.status(getIcon(), getlifePoints());
	}

	@Override
	public boolean isAlive() {
		return (this.lifepoints > 0);
	}

	@Override
	public void onEnter() {
	}

	@Override
	public void onExit() {
	}
	
	@Override
	public boolean byExplosion() {
		return false;
	}


	public abstract int getCost();

	abstract protected int getInitial();

	protected abstract int getDamage();

	abstract protected int getlifePoints();

	public abstract String getName();

	abstract protected String getIcon();

	public abstract Plant create(GameWorld game, int col, int row);

}