package gameWorld;

import gameWorld.room.Room;
import gameloop.Main;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Door {
	Vector2 position;
	Vector2 size;
	String imagePath;
	Room nextRoom;
	boolean open;

	public Door(Vector2 position, Room nextRoom, boolean open) {
		super();
		this.size = new Vector2(RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT/119*81).scalarMultiplication(1.2);
		this.position = position;
		this.nextRoom = nextRoom;
		this.open = open;
		this.imagePath = ImagePaths.OPENED_DOOR;
		if (open)
			this.imagePath = ImagePaths.OPENED_DOOR;
		else this.imagePath = ImagePaths.CLOSED_DOOR;
	}
	
	/*
	 * Si Isaac atteint une porte ouverte, il sera dirig� vers une autre salle qui est derrière cette porte-là
	 */
	
	public void accesALaPorte() {
		if(this.open && Physics.rectangleCollision(Main.isaac.getPosition(), Main.isaac.getSize(), this.position, this.size)) {
			GameWorld.setCurrentRoom(this.nextRoom);
			if(this.position == RoomInfos.POSITION_EAST_DOOR)
				Main.isaac.setPosition(new Vector2(1.0/9*3, 0.5));
			else if(this.position == RoomInfos.POSITION_WEST_DOOR)
				Main.isaac.setPosition(new Vector2(1.0-(1.0/9*3), 0.5));
			else if(this.position == RoomInfos.POSITION_NORTH_DOOR)
				Main.isaac.setPosition(new Vector2(0.5, 1.0/9*3));
			else if(this.position == RoomInfos.POSITION_SOUTH_DOOR)
				Main.isaac.setPosition(new Vector2(0.5, 1.0-(1.0/9*3)));
			
		}				
	}
	
	/*
	 * affiche
	 */
	
	public void drawGameObject()
	{	
		if(getPosition() == RoomInfos.POSITION_NORTH_DOOR)
			StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		
		else if(getPosition() == RoomInfos.POSITION_SOUTH_DOOR)
			StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
					180);
		
		else if(getPosition() == RoomInfos.POSITION_WEST_DOOR)
			StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
					90);
		
		else if(getPosition() == RoomInfos.POSITION_EAST_DOOR)
			StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
					-90);
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

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
