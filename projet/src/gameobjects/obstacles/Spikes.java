package gameobjects.obstacles;

import resources.HeroInfos;
import resources.ImagePaths;

public class Spikes extends Obstacle {

	private int degat;
	public Spikes() {
		super();
		this.degat=1;
		size = HeroInfos.ISAAC_SIZE.scalarMultiplication(0.8);
		this.imagePath = ImagePaths.SPIKES;
	}
	
	/*
	 * Getters and Setters
	 */
	
	public int getDegat() {
		return degat;
	}
	public void setDegat(int degat) {
		this.degat = degat;
	}
	
}
