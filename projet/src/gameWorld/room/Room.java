package gameWorld.room;

import java.util.ArrayList;

import gameWorld.Door;
import gameobjects.character.Hero;
import libraries.Vector2;
import resources.RoomInfos;

public class Room
{
	protected Hero hero;
	ArrayList<Door> doors;


	public Room(Hero hero)
	{
		this.hero = hero;		
		doors = new ArrayList<Door>();
	}


	/*
	 * Make every entity that compose a room process one step
	 */

	public void updateRoom()
	{
		updateDoor();
		makeHeroPlay();
	}

	
	private void updateDoor() {
		for(Door d: doors) {
			d.accesALaPorte();
		}
	}

	private void makeHeroPlay()
	{
		hero.updateGameObject();
	}

	/*
	 * affiche
	 */
	
	public void drawRoom()
	{
		for(Door d: doors) {
			d.drawGameObject();
		}
		hero.drawGameObject();
	}
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	protected static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}
	
	/*
	 * Getters and Setters
	 */
	
	public ArrayList<Door> getDoors() {
		return doors;
	}


	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}


	public Hero getHero() {
		return hero;
	}


	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	
}
