package tp1.p2.logic;

import tp1.p2.control.exceptions.GameException;

/**
 * Represents a game item and its allowed game actions.
 *
 */
public interface GameItem {

	/**
	 * Receive a zombie attack.
	 * 
	 * @param damage Received damage.
	 * @param explosion 
	 * 
	 * @return <code>true</code> if a plant has been attacked, <code>false</code>
	 *         otherwise.
	 */
	boolean receiveZombieAttack(int damage);

	/**
	 * Receive a plant attack.
	 * 
	 * @param damage Received damage.
	 * @param explosion 
	 * 
	 * @return <code>true</code> if a plant has been attacked, <code>false</code>
	 *         otherwise.
	 */
	boolean receivePlantAttack(int damage, boolean explosion);

	/**
	 * Checks if the game object fills its current position, that is, does not allow other {@link GameObject} to share its position.
	 * 
	 * @return
	 */
	boolean fillPosition();

	/**
	 * Try to catch a sun (if no other sun has been catched this cycle).
	 * 
	 * @return <code>true</code> if the sun has been catched, <code>false</code> otherwise.
	 * @throws GameException 
	 */
	boolean catchObject() throws GameException;

	boolean byExplosion();

}