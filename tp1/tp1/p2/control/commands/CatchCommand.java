package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandExecuteException;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPosition;
import tp1.p2.control.exceptions.NonCatchablePositionException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}

	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		if (!caughtSunThisCycle) {
			game.catchObject(this.col, this.row);
			caughtSunThisCycle = true;
			return true;
		} else
			throw new CommandExecuteException(Messages.SUN_ALREADY_CAUGHT);
	}

	public static void isValidIntegers(String a, String b) throws GameException {

		try {
			Integer.parseInt(a);
			Integer.parseInt(b);
		} catch (NumberFormatException excepcion) {
			throw new CommandParseException(Messages.INVALID_POSITION.formatted(a,b));
		}
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		if (parameters.length < 3) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		isValidIntegers(parameters[1], parameters[2]);
		int col = Integer.parseInt(parameters[1]);
		int row = Integer.parseInt(parameters[2]);
		this.col = col;
		this.row = row;
		return this;
	}
}