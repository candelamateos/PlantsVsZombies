
package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject {

	private static int INITIAL = 10 + 1;
	private static int caughtSuns = 0;
	private static int generatedSuns = 0;

	private boolean caught = false;

	public Sun(GameWorld game, int col, int row) {
		super(game, col, row);
		this.lifepoints = this.INITIAL;
		this.caught = false;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage,  boolean explosion) {
		return false;
	}

	@Override
	public boolean catchObject() {
		if (this.isAlive()) {
			this.lifepoints = 0;
			this.caught = true;
			onExit();
			return true;
		} else
			return false;
	}

	@Override
	public String getSymbol() {
		return Messages.status(getIcon(), getLifePoints());
	}

	@Override
	protected String getName() {
		return Messages.SUN_DESCRIPTION;
	}

	private String getIcon() {
		return Messages.SUN_SYMBOL;
	}

	@Override
	public boolean isAlive() {
		return this.lifepoints > 0;
	}

	@Override
	public void update() {
		this.lifepoints--;
		this.cycles++;
	}

	@Override
	public boolean fillPosition() {
		return false;
	}

	@Override
	public String getShortcut() {
		return Messages.SUNFLOWER_NAME_SHORTCUT;
	}

	@Override
	public void onEnter() {
		Sun.generatedSuns++;
	}

	@Override
	public void onExit() {
		if (this.caught) {
			game.increasesunCoins();
			Sun.caughtSuns++;
			this.caught = false;
		}
	}

	@Override
	public GameObject create(GameWorld game, int i, int row) {
		return null;
	}

	public static int getCaughtSuns() {
		return Sun.caughtSuns;
	}

	public static int getGeneratedSuns() {
		return Sun.generatedSuns;
	}

	public static void setCaughtSuns(int caughtSuns) {
		Sun.caughtSuns = caughtSuns;
	}

	public static void setGeneratedSuns(int generatedSuns) {
		Sun.generatedSuns = generatedSuns;
	}

	@Override
	public boolean byExplosion() {
		return false;
	}

}