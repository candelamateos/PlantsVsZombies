package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.Records;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ShowRecordCommand extends Command {

	@Override
	protected String getName() {
		return Messages.COMMAND_SHOW_RECORD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SHOW_RECORD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_SHOW_RECORD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_SHOW_RECORD_HELP;
	}

	@Override
	public boolean execute(GameWorld gameWorld) throws GameException {
		String level = gameWorld.getlevel().toString();
		int rec = Records.getForLevel(gameWorld.getlevel());
		System.out.printf(Messages.CURRENT_RECORD, level, rec);
		System.out.printf(Messages.LINE_SEPARATOR);
		return false;
	}

}
