package environment;

import java.util.ArrayList;
import java.util.Random;


import util.Case; 
import gameCommons.Game;


public class Lane  {
	
	//attribus
	protected Game game;
	protected int speed;
	protected int ord;
	protected ArrayList<Car> cars = new ArrayList<>();
	protected boolean leftToRight;
	protected double density;
	
	//constructeur
	public Lane(Game g) {
		this.game = new Game(g.getGraphic(), g.width,g.height,g.minSpeedInTimerLoops,g.defaultDensity);
		
		this.cars = new ArrayList<Car>();
		
		//Choisit al�atoirement soit 1 soit 2 
		int rand = 0;
		Random random = new Random();
		rand= random.nextInt(2) + 1;
		// on a decid� de choisir le sens de cette mani�re
		if (rand == 1)
			this.leftToRight = true;
		if (rand == 2)
			this.leftToRight = false;
		
		density = g.defaultDensity;
		speed = g.minSpeedInTimerLoops;
			
		//On remplie this.cars de voitures
		//Nous avons apport� des modifications � la m�thode mayAddCar() et nous l'avons appell� mayAddCarInt
		int i =0;
		while(i < game.width) {
			i = i + this.mayAddCarInt();
		}
	}


		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		 	
		
		


	// TODO : ajout de methodes

	
	public void updateLane() {
		for (int tic=0; tic < this.speed; tic++) {
			if (tic == speed) {
				for (int i =0; i< cars.size(); i++) {
					cars.get(i).move();
				}
			}
			this.mayAddCar();
		}
	}
	
	
	
	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	
	protected boolean isSafe(Case c) {
		if (c.ord == this.ord ) {
			for (int i = 0; i < cars.size(); i++) {
				if (c.absc == cars.get(i).leftPosition.absc 
					|| (c.absc >= cars.get(i).leftPosition.absc 
						&& c.absc <= cars.get(i).leftPosition.absc + cars.get(i).length))
					return false;
			}
		}
		return true;
	}
	
	
	

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}
	
	
	/**On a modifi� cette la methode mayAddCar pour qu'elle renvoie :
	 * la taille de la voiture cr�e s'il y a cr�ation de voiture
	 * un nombre al�atoire entre 1 et 3 si aucune voiture n'est cr�e
	 */
	protected int mayAddCarInt() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
				return cars.get(cars.size()-1).length;
			}
		}
		//Attribuer une taille aleatoirement entre 1 et 3 
		int rand = 0;
		Random random = new Random();
		rand= random.nextInt(3) + 1;
		return rand;
	}
	
	

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
