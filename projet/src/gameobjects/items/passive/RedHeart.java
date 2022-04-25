package gameobjects.items.passive;

import gameobjects.character.Hero;
import libraries.Physics;
import resources.ImagePaths;
import resources.RoomInfos;

public class RedHeart extends Passive{
	public RedHeart() {
		super();
		setValue(15);
		super.setSize(RoomInfos.TILE_SIZE.scalarMultiplication(0.7));
		super.setImagePath(ImagePaths.HP_UP);
	}
	
	/*
	*@param h a hero
	*augmente la vie max et soigne le héros
	*/
	
	public void buff(Hero h) {
		if(Physics.rectangleCollision(h.getPosition(), h.getSize(), this.getPosition(), this.getSize())) {
			h.setLivesMax(h.getLivesMax()+2);
			h.setLives(h.getLivesMax());
		}
	}
	
	
}
