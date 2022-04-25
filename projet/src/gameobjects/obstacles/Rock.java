package gameobjects.obstacles;

import java.util.LinkedList;

import gameobjects.character.Hero;
import gameobjects.character.Tear;
import gameobjects.monsters.Fly;
import gameobjects.monsters.Spider;
import libraries.Physics;
import resources.HeroInfos;
import resources.ImagePaths;

public class Rock extends Obstacle {
	public Rock() {	
		super();
		size = HeroInfos.ISAAC_SIZE;	
		this.imagePath = ImagePaths.ROCK;
	}
	
	/*
	 * lorsque des larmes de h�ros rentrent en collision avec le rocher elles disaparaissent
	 */

	public void collisionLarmesH(Hero h) {
		LinkedList<Tear> a = h.getLarmes();
		if(a.isEmpty()==false) {
			for(int i =0;i<a.size();i++) {
			if (Physics.rectangleCollision(this.getPosition(),this.size,a.get(i).getPosition(),a.get(i).getSize())==true) {
				a.remove(i);
			}
			}
			}
	}

	/*
	 * lorsque des larmes de mouche rentrent en collision avec le rocher elles disaparaissent
	 */
	
	public void collisionLarmesF(Fly f) {
		LinkedList<Tear> a = f.getLarmes();
		if(a.isEmpty()==false) {
			for(int i =0;i<a.size();i++) {
			if (Physics.rectangleCollision(this.getPosition(),this.size,a.get(i).getPosition(),a.get(i).getSize())==true) {
				a.remove(i);
			}
			}
			}
	}
	
	/*
	 * empeche les araign�es de passer sur les rochers
	 */

	public void collisionSpider(Spider s) {
		if (Physics.rectangleCollision(this.getPosition(),this.size,s.getPosition(),s.getSize())==true) {
			s.setDirection(s.getDirection().scalarMultiplication(-10));
		}
	}
	

}
