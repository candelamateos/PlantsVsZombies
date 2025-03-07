package tp1.p2.logic;

public interface GameStatus {

	int getCycle();

	int getSuncoins();
	
	boolean getzombiesWin();

	public boolean isPlayerQuits();
	
	int getRemainingZombies();

	boolean isFullyOcuppied(int col, int row);

	boolean getPlayerWins();

	int getGeneratedSuns();

	int getCaughtSuns();

	int getPoints();

	String positionToString(int col, int row);
	
	public boolean isThereANewRecord();
	
}