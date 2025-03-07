package tp1.p2.control.commands;


import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		if(null != this.level) game.reset(this.level, this.seed);
		else game.reset();
		return true;
	}
	
	@Override
	public Command create(String[] parameters) throws GameException{ 
		if (parameters.length == 1) {
			return this;
		}
		else {
			Level l = Level.valueOfIgnoreCase(parameters[1]);
			this.level = l;
			if (l == null) {
				throw new CommandParseException(Messages.INVALID_COMMAND);
			}
			try {
				Long s = Long.parseLong(parameters[2]);
				this.seed = s;
			}
			catch (NumberFormatException excepcion) { 
				throw new CommandParseException(Messages.INVALID_COMMAND);
			}
			return this;
		}
		
	}


}