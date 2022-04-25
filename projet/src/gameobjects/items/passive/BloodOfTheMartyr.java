package gameobjects.items.passive;

import gameobjects.character.Hero;
import libraries.Physics;
import resources.ImagePaths;
import resources.RoomInfos;

public class BloodOfTheMartyr extends Passive {

	public BloodOfTheMartyr() {
		super();
		setValue(15);
		setSize(RoomInfos.TILE_SIZE.scalarMultiplication(0.7));
		setImagePath(ImagePaths.BLOOD_OF_THE_MARTYR);
	}
	
	/*
	*@param h a hero
	*augmente les dégats du héros lorsqu'il est ramassé
	*/
	
	public void buff(Hero h) {
		if(Physics.rectangleCollision(h.getPosition(), h.getSize(), this.getPosition(), this.getSize()))
			h.setDegat(h.getDegat()+1);
	}

}
