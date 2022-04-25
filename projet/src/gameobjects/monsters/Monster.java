package gameobjects.monsters;

import java.util.List;
import java.util.Random;

import gameobjects.character.Hero;
import gameobjects.character.Tear;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public class Monster {
	Vector2 size;
	private Vector2 position;
	String imagePath;
	int lives;
	double speed;
	private Vector2 direction;
	protected int degat;

	
	
	public Monster(int lives, double speed) {
		super();
;
		Random rd = new Random();
		//choisir la position de l'obstacle au hasard
		int x = 0, y = 0;
		do {
			x = rd.nextInt(7);
			y = rd.nextInt(7);
		}
		while((x<1)||(y<1)||x>=6||y>=6);
		this.position = new Vector2((x+1.5)*RoomInfos.TILE_WIDTH, (y+1.5)*RoomInfos.TILE_HEIGHT);
		
		this.lives = lives;
		this.speed = speed;	
		this.degat = 1;
	}
	
	/*
	 * Make every entity that compose a monster process one step
	 */
	public void updateGameObject()
	{
		move();
	}
	public void hurt(Hero h) {
		List<Tear>a = h.getLarmes();
		if(a.isEmpty()==false) {
		for(int i =0;i<a.size();i++) {
		if (Physics.rectangleCollision(this.getPosition(),this.size,a.get(i).getPosition(),a.get(i).getSize())==true) {
			this.setLives((this.lives-h.getDegat()));
			a.remove(i);
		}
		}
		}
	}
	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
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
	 * Drawing
	 */
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY());
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getLives() {
		return lives;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	public int getDegat() {
		return degat;
	}
	public void setDegat(int degat) {
		this.degat = degat;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	
		
}


