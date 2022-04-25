package gameobjects.items;

import java.util.Random;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ListOfLists;
import resources.RoomInfos;

public class Item {

	String imagePath;
	Vector2 size;
	Vector2 position;
	int value;
	
	
	public Item() {
		
		Random rd = new Random();

		//choisir la position de l'obstacle au hasard
		int x = 0, y = 0;
		do {
			x = rd.nextInt(7);
			y = rd.nextInt(7);
		}
		//l'obstacle n'empêche pas l'accès une porte
		while(x<1||y<1||x>5||y>5||ListOfLists.occupe[x][y]);
		this.position = new Vector2((x+1.5)*RoomInfos.TILE_WIDTH, (y+1.5)*RoomInfos.TILE_HEIGHT);
		ListOfLists.occupe[x][y] = true;
	}
	
	/*
	 * Make every entity related with item that compose a hero process one step
	 */
	
	public void updateHero() {
		
	}
	
	/*
	 * affiche
	 */
	
	public void drawGameObject()
	{	
			StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	/*
	 * Getters and Setters
	 */
	
	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = new Vector2(position);
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
