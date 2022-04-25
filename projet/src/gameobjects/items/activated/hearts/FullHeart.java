package gameobjects.items.activated.hearts;

import gameobjects.character.Hero;
import resources.ImagePaths;

public class FullHeart extends Heart {

	public FullHeart() {
		super(10, ImagePaths.HEART_PICKABLE);
	}
	
	/*
	*@param h un h�ros
	*remplie sa vie si elle est inf�rieure � sa vie maximum entre 1 coeur et 2 coeurs selon la n�cessit�
	*/
	
	public void soigne(Hero h) {
		if(h.getLivesMax()-h.getLives()==1)
			h.setLives(h.getLives()+1);
		else if(h.getLivesMax()-h.getLives()>1)
			h.setLives(h.getLives()+2);
	}
}
