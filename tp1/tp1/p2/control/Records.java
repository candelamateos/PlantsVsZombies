package tp1.p2.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import tp1.p2.control.exceptions.RecordException;
import tp1.p2.view.Messages;

public class Records {

	private static int easy;
	private static int hard;
	private static int insane;

	public static void read() throws RecordException, FileNotFoundException, IOException {
		try (BufferedReader file = new BufferedReader(new FileReader(Messages.RECORD_FILENAME))) {
			ArrayList<String> l = new ArrayList<String>();
			String line = file.readLine();
			while (line != null) {
				l.add(line);
				line = file.readLine();
			}
			if (l.size() > 3)
				throw new RecordException(Messages.RECORD_READ_ERROR);
			for (String L : l) {
				String[] words = L.trim().split(":");
				if (words.length != 2)
					throw new RecordException(Messages.RECORD_READ_ERROR);
				Level actualL = Level.valueOfIgnoreCase(words[0]);
				if (actualL == null)
					throw new RecordException(Messages.RECORD_READ_ERROR);
				int pointsOfLevel = Integer.parseInt(words[1]);
				switch (actualL) {
				case EASY:
					easy = pointsOfLevel;
					break;
				case HARD:
					hard = pointsOfLevel;
					break;
				case INSANE:
					insane = pointsOfLevel;
					break;
				default:
					throw new RecordException(Messages.RECORD_READ_ERROR);
				}
			}
		}
		catch (IOException ioe) {
			throw new RecordException(Messages.RECORD_READ_ERROR, ioe); 
		}
		catch (NumberFormatException nfe) {
			throw new RecordException(Messages.RECORD_READ_ERROR, nfe);
		}
	}

	public static int getForLevel(Level level) {
		switch (level) {
		case EASY:
			return easy;
		case HARD:
			return hard;
		case INSANE:
			return insane;
		default:
			return 0;
		}
	}

	public static void update(Level level, int points) {
		for (Level l : Level.values()) {
			if (level.toString().equalsIgnoreCase(level.toString()) && points > getForLevel(level)) {
				switch (l) {
				case EASY:
					easy = points;
					break;
				case HARD:
					hard = points;
					break;
				case INSANE:
					insane = points;
					break;
				}
			}
		}
	}

	public static void save() throws RecordException {
		try (BufferedWriter outChars = new BufferedWriter(new FileWriter(Messages.RECORD_FILENAME))) {
			StringBuilder str = new StringBuilder();
			for (Level l : Level.values()) {
				switch (l) {
				case EASY : str.append(l.name()).append(": ").append(easy);
				case HARD : str.append(l.name()).append(": ").append(hard);
				case INSANE : str.append(l.name()).append(": ").append(insane);
				}
				outChars.write(str.toString());
			}
		} catch (IOException e) {
			throw new RecordException(Messages.RECORD_WRITE_ERROR);
		}
	}
}
