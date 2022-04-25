package gameobjects.items.activated.coins;

import gameobjects.items.activated.Activated;
import libraries.Vector2;
import resources.RoomInfos;

public class Coin extends Activated {
	
	private int value;
	
	public Coin(int value, String imagePath) {
		setSize(new Vector2(RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT/18*13).scalarMultiplication(0.5));
		setValue(value);
		setImagePath(imagePath);
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
	
	
	

	
	

}
