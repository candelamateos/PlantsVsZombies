package tp1.p2.logic.actions;

import java.util.List;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.GameObject;

public abstract class ExplosionAction implements GameAction {

	private int col;

	private int row;

	protected int damage;

	public ExplosionAction(int col, int row, int damage) {
		this.col = col;
		this.row = row;
		this.damage = damage;
	}

	@Override
	public void execute(GameWorld game) {
		for (int i = row - 1; i < row + 2; i++) {
			for (int j = col - 1; j < col + 2; j++) {
				List<GameItem> out = null;
				if (game.getGameObjectsinPos(j, i) != null) {
					out = game.getGameObjectsinPos(j, i);
					hurt(out);
					for (GameItem g : out)
						if (g.byExplosion())
							game.increasePoints(10);
				}
			}
		}
	}

	abstract protected void hurt(List<GameItem> g);
	// Se sobreescribe en cada clase según si estamos atacando a una planta o a un
	// zombie
}