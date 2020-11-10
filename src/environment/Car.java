package environment;

import java.awt.Color;
import java.util.Random;

import util.Case;

import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	//attribus
	protected Game game;
	protected Case leftPosition;
	protected boolean leftToRight;
	protected int length;
	protected final Color colorLtR = Color.BLACK;
	protected final Color colorRtL = Color.BLUE;
	//protected double speed; 

	//constructeur
	public Car(Game g, Case beforeFirst, boolean leftToRight) {
		this.game = g;
		this.leftToRight = leftToRight;
		//Attribuer une taille aleatoirement entre 1 et 3 
		int rand = 0;
		Random random = new Random();
		rand= random.nextInt(3) + 1;
		this.length = rand;
		
		this.leftPosition = new Case(beforeFirst.absc, beforeFirst.ord);
		
	}
	
	//methodes
	
	public void move() {
		if (this.leftToRight) {
			Case newPos = new Case(this.leftPosition.absc +1, this.leftPosition.ord);
			this.leftPosition= newPos;
			this.addToGraphics();
		}
		else {
			Case newPos = new Case(this.leftPosition.absc -1, this.leftPosition.ord);
			this.leftPosition= newPos;
			this.addToGraphics();
			
		}
	}
	
	

	
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
