package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ZombieFactory {

	/* @formatter:off */
	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
		new Zombie(null, 0,0),
		new BucketHead(null, 0,0),
		new Sporty(null, 0,0),
		new ExplosiveZombie(null, 0,0)
	);
	/* @formatter:on */

	public ZombieFactory() {
	}

	public static boolean isValidZombie(int zombieIdx) throws GameException {
		boolean ok = (zombieIdx >= 0 && zombieIdx < AVAILABLE_ZOMBIES.size());
		if (!ok)
			throw new CommandParseException(Messages.INVALID_GAME_OBJECT);
		return ok;
	}

	public static Zombie spawnZombie(int zombieIdx, GameWorld game, int col, int row) {
			for (Zombie zombie : AVAILABLE_ZOMBIES) {
				if (zombieIdx == zombie.zombieIdx) {
					Zombie out = zombie.create(game, col, row);
					return out;
				}
			}
			return null;
	}

	public static List<Zombie> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}

}