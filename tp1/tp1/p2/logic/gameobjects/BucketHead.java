package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie{
	
	private static final int FREQUENCY = 4;
	private static final int INITIAL = 8;
	private static final int DAMAGE = 1;
	

	public BucketHead(GameWorld game, int col, int row) {
		super(game, col, row);
		this.lifepoints = this.INITIAL;
		this.zombieIdx = 1;
	}

	@Override
	public int getLifePoints() {
		return this.lifepoints;
	}

	@Override
	public String getName() {
		return Messages.BUCKET_HEAD_ZOMBIE_NAME;
	}

	@Override
	protected String getIcon() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
	}
	
	@Override
	protected int getFrequency() {
		return this.FREQUENCY;
	}
	

	//Creates a new buckethead
	@Override
	public BucketHead create(GameWorld game, int col, int row) {
		return new BucketHead(game, col, row);
	}

}
