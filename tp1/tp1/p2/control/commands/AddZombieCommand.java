package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command {

	private int row;
	private int col;
	private int zombieIdx;

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}

	@Override
	public boolean execute(GameWorld gameWorld) throws GameException {
		Zombie addedZombie = ZombieFactory.spawnZombie(this.zombieIdx, gameWorld, this.col, this.row);
		if (gameWorld.isPositionEmpty(this.col, this.row)) {
			gameWorld.addGameObject(addedZombie);
			gameWorld.update();
			return true;
		} else {
			throw new GameException(Messages.INVALID_POSITION);
		}

	}

	@Override
	public Command create(String[] parameters) throws GameException {
		if (parameters.length < 4) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		ZombieFactory.isValidZombie(Integer.parseInt(parameters[3]));
		this.zombieIdx = Integer.parseInt(parameters[3]);
		this.col = Integer.parseInt(parameters[2]);
		this.row = Integer.parseInt(parameters[1]);
		return this;
	}
}
