
package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public void add(GameObject gameObject) {
		this.gameObjects.add(gameObject);
	}

	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			if (g.isAlive() && g.getCol() == col && g.getRow() == row) {
				String objectText = g.getSymbol();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public void update() {
		// Can't use for-each loop (for(GameObject g : gameObjexts)) without errors.
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject g = gameObjects.get(i);
			g.update();
		}
	}

	public boolean isFullyOccupied(int col, int row) {
		int i = 0;
		boolean fullyOcuppied = false;

		while (i < gameObjects.size() && !fullyOcuppied) {
			GameObject g = gameObjects.get(i);
			if (g.isAlive() && g.isInPosition(col, row)) {
				fullyOcuppied = g.fillPosition();
			}
			i++;
		}
		return fullyOcuppied;
	}

	public boolean removeDead() { 

		for (int cont = 0; cont < gameObjects.size(); cont++) {
			GameObject g = gameObjects.get(cont);
			if (!g.isAlive()) {
				g.onExit();
				gameObjects.remove(cont);
				cont--;
			}
		}
		return false;
	}

	public List<GameItem> findObjects(int col, int row) {
		List<GameItem> out = null;
		out = new ArrayList<>();
		for (GameObject g : gameObjects) {
			if (g.isInPosition(col, row)) {
				out.add(g);
			}
		}
		return out;
	}

	public GameItem findSunInPos(int col, int row) {
		List<GameItem> out = findObjects(col, row);
		for (GameItem g: out) {
			if (!g.fillPosition()) return g;
		}
		return null;
	}

	public GameItem findFillingObject(int col, int row) {
		List<GameItem> out = findObjects(col, row);
		for (GameItem g: out) {
			if (g.fillPosition()) return g;
		}
		return null;
	}

	public void catchObject(int col, int row) throws GameException {
		List<GameItem> objects = findObjects(col, row);
		for (GameItem g: objects) {
			g.catchObject();
		}
	}
}