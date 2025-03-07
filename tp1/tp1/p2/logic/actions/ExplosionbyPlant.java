package tp1.p2.logic.actions;

import java.util.List;

import tp1.p2.logic.GameItem;

public class ExplosionbyPlant extends ExplosionAction {

	public ExplosionbyPlant(int col, int row, int damage) {
		super(col, row, damage);
	}

	@Override
	protected void hurt(List<GameItem> objects) {
		for (GameItem g : objects) {
			boolean explosion = true;
			g.receivePlantAttack(damage, explosion);
		}
	}

}