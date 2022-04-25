package gameobjects.items.activated.hearts;

import gameobjects.items.activated.Activated;
import libraries.Vector2;
import resources.RoomInfos;

public class Heart extends Activated{
	int value;
	String imagePath;
	
	public Heart(int value, String imagePath) {
		super.setSize(new Vector2(RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT/28*26).scalarMultiplication(0.4));
		this.value = value;
		this.imagePath = imagePath;
	}

	/*
	 * Getters and Setters
	 */
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
