package tp1.p2.control.commands;


import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCheatCommand extends AddPlantCommand {
	
	AddPlantCheatCommand(String plantName, int col, int row) {
		super(plantName, col, row);
	}

	public AddPlantCheatCommand() {
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CHEAT_PLANT_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CHEAT_PLANT_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		Plant addedPlant = PlantFactory.spawnPlant(this.plantName, game, this.col, this.row);
		game.addGameObject(addedPlant);
		game.update();
		return true;
	}
}
