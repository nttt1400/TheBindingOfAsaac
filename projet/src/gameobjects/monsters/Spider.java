package gameobjects.monsters;

import java.util.Random;

import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Spider extends Monster {
	
	int step = 0;
	boolean deplacer;

	public Spider() {
		super(5,0.005);
		this.size = new Vector2(RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT/2).scalarMultiplication(0.5);
		this.imagePath = ImagePaths.SPIDER;
		this.setDirection(new Vector2(1,0));
	}
	
	/*
	 * Make every entity that compose a spider process one step
	 */
	
	public void updateGameObject()
	{	
		deplacement();
		
		if(getPosition().getY()+getSize().getY()>=(1.0/9*7.5)&& getDirection().getY()==1)
			return;
		if(getPosition().getY()-getSize().getY()/2<=(1.0/9*1.5)&& getDirection().getY()==-1)
			return;
		if(getPosition().getX()+getSize().getX()>=(1.0/9*7.5)&& getDirection().getX()==1)
			return;
		if(getPosition().getX()-getSize().getX()/2<=(1.0/9*1.5)&& getDirection().getX()==-1)
			return;
	
		super.updateGameObject();
		
		
	}
	
	/*
	 * déplace l'araignée
	 */
	
	public void deplacement() {
		if(step > 60) {
			setDirection(new Vector2());
		}
		if(step > 120) {
			step = 0;
			setDirectionRandomly();
		}	
		
		step++;
	}
	
	private void setDirectionRandomly() {
		Random rd = new Random();
		int x = 0, y =0;
		do {
			x = rd.nextInt(3)-1;
			y = rd.nextInt(3)-1;
		}
		while((x==0&&y==0));
		super.setDirection(new Vector2(x,y));
	}
	
}

