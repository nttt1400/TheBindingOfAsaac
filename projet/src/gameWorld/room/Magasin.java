package gameWorld.room;

import java.util.ArrayList;
import java.util.Random;

import gameobjects.character.Hero;
import gameobjects.items.Item;
import gameobjects.items.activated.hearts.FullHeart;
import gameobjects.items.activated.hearts.HalfHeart;
import gameobjects.items.activated.hearts.Heart;
import gameobjects.items.passive.BloodOfTheMartyr;
import gameobjects.items.passive.Passive;
import gameobjects.items.passive.RedHeart;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;

public class Magasin extends Room {
	
	ArrayList<Item> items;
	public Magasin(Hero hero) {
		super(hero);
		Random rd = new Random();
		this.items=new ArrayList<Item>();
		
		//au moins objet passif à vendre  
		if(rd.nextBoolean())
			items.add(new BloodOfTheMartyr());
		else items.add(new RedHeart());
		items.get(0).setPosition(new Vector2(0.25,0.5));
	
		for(int i = 1; i < 3; i++) {
			if(rd.nextBoolean()) {
				if(rd.nextBoolean())
					items.add(new BloodOfTheMartyr());
				else items.add(new RedHeart());
			}
			else {
				if(rd.nextBoolean())
					items.add(new HalfHeart());
				else items.add(new FullHeart());
			}
			
			items.get(i).setPosition(new Vector2(0.25+i*0.25,0.5));
		}

	}
	
	/*
	 * affiche
	 */
	
	public void drawRoom()
	{	
		super.drawRoom();
		for(int i = 0; i < items.size(); i++) {
			items.get(i).drawGameObject();
			StdDraw.text(items.get(i).getPosition().getX(),0.5-0.05, String.valueOf(items.get(i).getValue()));	
		}
	}
	
	/*
	 * Make every entity that compose a room process one step
	 */

	public void updateRoom() {
		super.updateRoom();
		ArrayList<Item> pas2 = new ArrayList<Item>();
		
		//Si le héro a assez d'argent, il peut acheter des objets en les ramassant
		
		if(!items.isEmpty()) {
			for(int i=0;i<items.size();i++){
				if(hero.getCoins()>=items.get(i).getValue()
						&&(Physics.rectangleCollision(items.get(i).getPosition(),items.get(i).getSize(),hero.getPosition(),hero.getSize()))) {
					if(items.get(i) instanceof Heart
							&& (hero.getLivesMax()>hero.getLives())){
						if (items.get(i) instanceof FullHeart) {
							FullHeart coeur = (FullHeart)items.get(i);
							coeur.soigne(hero);
							hero.setCoins(hero.getCoins()-items.get(i).getValue());
							pas2.add(items.get(i));
						}
						else if (items.get(i) instanceof HalfHeart) {
							HalfHeart coeur = (HalfHeart)items.get(i);
							coeur.soigne(hero);
							hero.setCoins(hero.getCoins()-items.get(i).getValue());
							pas2.add(items.get(i));
						}
					}
					else if(items.get(i) instanceof Passive) {
						if(items.get(i) instanceof BloodOfTheMartyr) {
							BloodOfTheMartyr botm=(BloodOfTheMartyr)items.get(i);
							botm.buff(hero);
						}
						if(items.get(i) instanceof RedHeart) {
							RedHeart rh=(RedHeart)items.get(i);
							rh.buff(hero);
						}
						hero.setCoins(hero.getCoins()-items.get(i).getValue());
						pas2.add(items.get(i));
					}
				}
					
				
			}
			items.removeAll(pas2);	
		}	
			
	}
		
	

}
