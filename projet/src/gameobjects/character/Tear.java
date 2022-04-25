package gameobjects.character;

import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Tear {
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	public Tear(Vector2 position) {
		this.position = position;
		this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.35);;
		this.imagePath = ImagePaths.TEAR;
		this.speed = HeroInfos.ISAAC_SPEED;
		this.direction = new Vector2();
		
	}
	
	/*
	 * Affiche
	 */
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),0);
		
	}
	
	/*
	 * Normalise the direction vector
	 */
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	/*
	 * Getters and Setters
	 */
	
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Vector2 getSize() {
		return size;
	}
	public void setSize(Vector2 size) {
		this.size = size;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Vector2 getDirection() {
		return direction;
	}
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	

}
