package tp1.p2.logic.gameobjects;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.NonCatchablePositionException;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public abstract class GameObject implements GameItem {

	protected GameWorld game;

	protected int col;

	protected int row;
	
	protected int lifepoints; 
	
	protected int cycles;

	GameObject(GameWorld game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.cycles = 0;
	}
	
	GameObject(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}
	
	public int getLifePoints () {
		return this.lifepoints;
	}
	
	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}
	
	public boolean fillPosition() {
		return true;
	}
	
	public boolean catchObject() throws GameException {
		return false;
	}

	public abstract String getSymbol();

	abstract public boolean isAlive();

	abstract public void onEnter();
	
	abstract public void onExit();
	
	protected abstract String getName();

	public abstract void update();

	public abstract String getShortcut();

	public abstract GameObject create(GameWorld game, int i, int row);

}