package gameobjects.items.activated.hearts;

import gameobjects.character.Hero;
import resources.ImagePaths;

public class HalfHeart extends Heart{

	public HalfHeart() {
		super(5, ImagePaths.HALF_HEART_PICKABLE);
	}
	
	/*
	*@param h a hero
	*remplie sa vie si elle est inférieure à sa vie maximum entre 1
	*/
	
	public void soigne(Hero h) {
		if(h.getLivesMax()-h.getLives()>=1)
			h.setLives(h.getLives()+1);
		}
	
}
