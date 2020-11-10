package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
	//attributs
	private Game game;
	ArrayList<Lane> road = new ArrayList<>();
	
	//Constructeur
	public Environment(Game g) {
		this.game = new Game(g.getGraphic(), g.width,g.height,g.minSpeedInTimerLoops,g.defaultDensity);
		road = new ArrayList<Lane>(g.height);
		for (int i = 0; i< g.height; i++) {
			road.add(new Lane(g));
			road.get(i).ord = i;
		}
	}
	
	//Methodes
	
	
	
	
	/**public boolean isSafe(Case c) {
		for(int i=0; i< road.get(c.ord).cars.size(); i++){
			if (c.absc == road.get(c.ord).cars.get(i).leftPosition.absc 
					&& c.ord == road.get(c.ord).cars.get(i).leftPosition.ord) {
				return false;
			}
		}
		return true ;
	}**/
	
	
	public boolean isSafe(Case c) {
		return road.get(c.ord).isSafe(c);
	}
	
	public boolean isWinningPosition(Case c) {
		if (c.ord == game.height)
			return true;
		return false;
	}
	
	public void update() {
		for (int i=0; i< this.game.height; i++) {
			road.get(i).updateLane();
		}
	}
	
	
}
