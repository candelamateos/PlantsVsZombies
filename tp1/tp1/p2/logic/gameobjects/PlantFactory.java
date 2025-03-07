package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPosition;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class PlantFactory {

	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(new Sunflower(null, 0, 0),
			new Peashooter(null, 0, 0), new WallNut(null, 0, 0), new CherryBomb(null, 0, 0));

	public PlantFactory() {
	}

	public static boolean isValidPlant(String string) throws GameException {
		for (Plant p : AVAILABLE_PLANTS) {
			if (string.equals(p.getName()) || string.equals(p.getIcon()) || string.equals(p.getIcon().toLowerCase())
					|| string.equals(p.getName().toLowerCase()))
				return true;
		}
		throw new CommandParseException(Messages.INVALID_GAME_OBJECT);

	}

	private static boolean matchPlant(String plantName, Plant p) {
		if (plantName.equals(p.getName()) || plantName.equals(p.getIcon())
				|| plantName.equals(p.getIcon().toLowerCase()) || plantName.equals(p.getName().toLowerCase()))
			return true;
		return false;
	}

	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row) {
		for (Plant plant : AVAILABLE_PLANTS) {
			if (matchPlant(plantName, plant)) {
				Plant p = plant.create(game, col, row);
				return p;
			}
		}
		return null;
	}

	public static List<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

}