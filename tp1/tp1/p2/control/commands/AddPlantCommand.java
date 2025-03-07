
package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command {

	protected int col;

	protected int row;

	protected String plantName;

	public AddPlantCommand() {
	}

	public AddPlantCommand(String plantName, int col, int row) {
		this.plantName = plantName;
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	public static void isValidIntegers(String a, String b) throws GameException {

		try {
			Integer.parseInt(a);
			Integer.parseInt(b);
		} catch (NumberFormatException excepcion) {
			throw new CommandParseException(Messages.INVALID_POSITION.formatted(a, b));
		}
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		Plant addedPlant = PlantFactory.spawnPlant(this.plantName, game, this.col, this.row);
		game.tryToBuy(addedPlant.getCost());
		game.addGameObject(addedPlant);
		game.update();
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		if (parameters.length < 4) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		PlantFactory.isValidPlant(parameters[1]);
		isValidIntegers(parameters[2], parameters[3]);
		this.plantName = parameters[1];
		this.col = Integer.parseInt(parameters[2]);
		this.row = Integer.parseInt(parameters[3]);
		return this;
	}
}