package gameobjects.obstacles;

import java.util.Random;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ListOfLists;
import resources.RoomInfos;

public class Obstacle {
	Vector2 size;
	private Vector2 position;
	String imagePath;
	private static boolean[][] occupe;
	
	public Obstacle() {
		super();
		Random rd = new Random();
	
		//choisir la position de l'obstacle au hasard
		int x = 0, y = 0;
		do {
			x = rd.nextInt(7);
			y = rd.nextInt(7);
		}
		//l'obstacle n'empêche pas l'accès une porte
		while(x==3||y==3||x==0||y==0||x==6||y==6||(ListOfLists.occupe[x][y]));	
		this.position = new Vector2((x+1.5)*RoomInfos.TILE_WIDTH, (y+1.5)*RoomInfos.TILE_HEIGHT);
		ListOfLists.occupe[x][y] = true;
		
	}

	public Obstacle(Vector2 position) {
		super();
		this.position = position;
	}
	
	/*
	 * affiche
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
	
	public static void setOccupe(boolean[][] occupe) {
		Obstacle.occupe = occupe;
	}
	
	public static boolean[][] getOccupe() {
		return occupe;
	}
}
