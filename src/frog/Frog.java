package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Case position;
	private Direction dir;
	
	
	public Frog(Game g) {
		this.game = g;
		this.position = new Case(g.width/2,0);
		this.dir = Direction.up;
	}
	
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key) {
		
		if (key == Direction.up) {
			Case pos = new Case(this.position.absc, this.position.ord+1);
			this.position = pos;
			this.dir = key;
		}
		if (key == Direction.down && this.position.ord > 0) {
			Case pos = new Case(this.position.absc, this.position.ord-1);
			this.position = pos;
			this.dir = key;
		}
		if (key == Direction.right && this.position.absc < this.game.width) {
			Case pos = new Case(this.position.absc+1, this.position.ord);
			this.position = pos;
			this.dir = key;
		}
		if (key == Direction.left && this.position.absc > 0) {
			Case pos = new Case(this.position.absc-1, this.position.ord);
			this.position = pos;
			this.dir = key;
		}
	}
	
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection() {
		return this.dir;
	}
	
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition() {
		return this.position;
	}
	

}
