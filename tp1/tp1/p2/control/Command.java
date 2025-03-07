package tp1.p2.control;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.control.commands.AddPlantCheatCommand;
import tp1.p2.control.commands.AddPlantCommand;
import tp1.p2.control.commands.AddZombieCommand;
import tp1.p2.control.commands.CatchCommand;
import tp1.p2.control.commands.ShowRecordCommand;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.commands.ExitCommand;
import tp1.p2.control.commands.HelpCommand;
import tp1.p2.control.commands.ListPlantsCommand;
import tp1.p2.control.commands.ListZombiesCommand;
import tp1.p2.control.commands.NoneCommand;
import tp1.p2.control.commands.ResetCommand;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {

	static GameWorld gameWorld;

	private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
			new AddPlantCommand(), 
			new ListPlantsCommand(),
			new ResetCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new NoneCommand(),
			new ListZombiesCommand(),
			new AddZombieCommand(),
			new AddPlantCheatCommand(),
			new CatchCommand(),
			new ShowRecordCommand()
			);

	public Command() {
		this(false);
		//this.gameWorld = new GameWorld();
	}

	private static Command defaultCommand;

	public Command(boolean a) {
		if (a)
			defaultCommand = this;
	}

	public static Command parse(String[] commandWords) throws GameException {
		if (commandWords.length == 1 && commandWords[0].isEmpty()) {
			return defaultCommand;
			}
		for (Command command : AVAILABLE_COMMANDS) {
			if (command.matchCommand(commandWords[0])) {
				if (command.create(commandWords) == null) 
				return null;
				else {
				return command;
			}
			}
		}
		throw new CommandParseException(Messages.UNKNOWN_COMMAND);
	}

	public static Iterable<Command> getAvailableCommands() {
		return Collections.unmodifiableList(AVAILABLE_COMMANDS);
	}

	abstract protected String getName();

	abstract protected String getShortcut();

	abstract public String getDetails();

	abstract public String getHelp();

	public boolean isDefaultAction() {
		return Command.defaultCommand == this;
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public boolean matchCommand(String token) {
		String shortcut = getShortcut();
		String name = getName();
		return shortcut.equalsIgnoreCase(token) || name.equalsIgnoreCase(token)
				|| (isDefaultAction() && "".equals(token));
	}

	public abstract boolean execute(GameWorld gameWorld) throws GameException;
	public Command create(String[] parameters) throws GameException {
		return this;
	}

	public static void newCycle() {
	    for(Command c : AVAILABLE_COMMANDS) {
	        c.newCycleStarted();
	    }
	}

	/**
	   * Notifies the {@link Command} that a new cycle has started.
	   */
	protected void newCycleStarted() {
		
	}


}