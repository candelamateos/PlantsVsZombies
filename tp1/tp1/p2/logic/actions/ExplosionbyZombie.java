package tp1.p2.logic.actions;

import java.util.List;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;

public class ExplosionbyZombie extends ExplosionAction {

	
	public ExplosionbyZombie(int col, int row, int damage) {
		super(col, row, damage);
		}
	
	@Override
	protected void hurt(List<GameItem> objects) { 
			for (GameItem g : objects) {
				g.receiveZombieAttack(damage);
			}
		}

}