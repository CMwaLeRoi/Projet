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
		
		//Choisit aléatoirement soit 1 soit 2 
		int rand = 0;
		Random random = new Random();
		rand= random.nextInt(2) + 1;
		// on a decidé de choisir le sens de cette manière
		if (rand == 1)
			this.leftToRight = true;
		if (rand == 2)
			this.leftToRight = false;
		
		density = g.defaultDensity;
		speed = g.minSpeedInTimerLoops;
			
		//On remplie this.cars de voitures
		//Nous avons apporté des modifications à la méthode mayAddCar() et nous l'avons appellé mayAddCarInt
		int i =0;
		while(i < game.width) {
			i = i + this.mayAddCarInt();
		}
	}


		// Toutes les voitures se dï¿½placent d'une case au bout d'un nombre "tic
		// d'horloge" ï¿½gal ï¿½ leur vitesse
		// Notez que cette mï¿½thode est appelï¿½e ï¿½ chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut ï¿½tre ajoutï¿½e
		 	
		
		


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
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}
	
	
	/**On a modifié cette la methode mayAddCar pour qu'elle renvoie :
	 * la taille de la voiture crée s'il y a création de voiture
	 * un nombre aléatoire entre 1 et 3 si aucune voiture n'est crée
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
