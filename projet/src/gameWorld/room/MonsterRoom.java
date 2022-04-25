package gameWorld.room;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import gameWorld.Door;
import gameobjects.character.Hero;
import gameobjects.character.Tear;
import gameobjects.items.Item;
import gameobjects.items.activated.coins.Coin;
import gameobjects.items.activated.coins.Dime;
import gameobjects.items.activated.coins.Nickel;
import gameobjects.items.activated.coins.Penny;
import gameobjects.items.activated.hearts.FullHeart;
import gameobjects.items.activated.hearts.HalfHeart;
import gameobjects.items.activated.hearts.Heart;
import gameobjects.items.passive.BloodOfTheMartyr;
import gameobjects.items.passive.Passive;
import gameobjects.items.passive.RedHeart;
import gameobjects.monsters.Fly;
import gameobjects.monsters.Monster;
import gameobjects.monsters.Spider;
import gameobjects.obstacles.Obstacle;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Spikes;
import libraries.Physics;
import resources.ListOfLists;

public class MonsterRoom extends Room{
	
	ArrayList<Monster> monsters;
	ArrayList<Obstacle> obstacles;
	ArrayList<Item> items;
	
	ArrayList<Door> doors;
	private Instant instant;
	private boolean added;
	
	public MonsterRoom(Hero hero) {
		
		super(hero);
		
		Random rd = new Random();
		
		int totalMonsters = rd.nextInt(2)+2;
		int totalObstacles = rd.nextInt(2)+2;
		int totalItems = rd.nextInt(1)+2;
		
		this.monsters = new ArrayList<Monster>();
		this.obstacles = new ArrayList<Obstacle>();
		this.items = new ArrayList<Item>();
		this.doors = new ArrayList<Door>();
		this.instant=Instant.now();
		this.added = false;
	
		//Monstres
		
		for(int i = 0; i < totalMonsters; i++) {
			monsters.add(ListOfLists.getListMonters().get(rd.nextInt(ListOfLists.getListMonters().size())));			
		}
		
		//Obstacles
		
		for(int i = 0; i < totalObstacles; i++) {
			obstacles.add(ListOfLists.getListObstacles().get(rd.nextInt(ListOfLists.getListObstacles().size())));
		}
		
		//Objects consommatifs
		
		for(int i = 0; i < totalItems-1; i++) {
			items.add(ListOfLists.getListActivatedItems().get(rd.nextInt(ListOfLists.getListActivatedItems().size())));			
		}
		
	}
	
	/*
	 * affiche
	 */

	public void drawRoom()
	{	
		super.drawRoom();
		ArrayList<Monster> a = monsters;
		for(Monster m: a) {
			if(m instanceof Fly) {
				((Fly)m).drawGameObject();
				((Fly)m).larme(new Tear(m.getPosition()),hero);
				((Fly) m).portee();
				hero.hurtF((Fly)m);
				((Fly)m).hurt(hero);
				Instant time=Instant.now();
				if(!hero.isImmortal()) {
				if((double) Duration.between(instant,time).toMillis()>150) {
				this.instant=time;
				hero.hurt(m);}
				}
			}
			else if (m instanceof Spider) {
			((Spider)m).drawGameObject();
			((Spider)m).hurt(hero);
			Instant time=Instant.now();
			if(!hero.isImmortal()){
			if((double) Duration.between(instant,time).toMillis()>100) {
			this.instant=time;
			hero.hurt(m);}
			}
			}
		}
			
		
		for (Obstacle o: obstacles) {
			o.drawGameObject();
			if(o instanceof Rock) {
				hero.collisionRocherDown((Rock)o);
				hero.collisionRocherRight((Rock)o);
				hero.collisionRocherLeft((Rock)o);
				hero.collisionRocherUp((Rock)o);
				((Rock) o).collisionLarmesH(hero);
				for(Monster m: a) {
					if(m instanceof Fly) {
						((Rock) o).collisionLarmesF((Fly) m);

					}
					else if (m instanceof Spider)
						((Rock) o).collisionSpider((Spider)m);
				}
			}
			if(o instanceof Spikes) {
					Instant time=Instant.now();
					if(!hero.isImmortal()) {
					if((double) Duration.between(instant,time).toMillis()>200) {
						this.instant=time;
						hero.hurt((Spikes)o);
					}
				}
			}
		}
		
		for(Item i: items) {
			i.drawGameObject();
		}
		for(Door d: doors) {
			d.drawGameObject();
		}
			
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	
	public void updateRoom()
	{
		super.updateRoom();
		hero.portee();
		ArrayList<Monster> pas = new ArrayList<Monster>();
		if(!monsters.isEmpty()) {
		for(int i=0;i<monsters.size();i++){
			monsters.get(i).updateGameObject();
			if(monsters.get(i).getLives()<=0) {
				pas.add(monsters.get(i));
			}
		}
		}
		
		monsters.removeAll(pas);
		ArrayList<Item> pas2 = new ArrayList<Item>();
		if(!items.isEmpty()) {
			for(int i=0;i<items.size();i++){
				if(items.get(i) instanceof Coin) {
				if(hero.ramasseItem(items.get(i))) {
					
					if(items.get(i) instanceof Dime)
						hero.ramasseDime((Dime) items.get(i));
					if(items.get(i) instanceof Nickel)
						hero.ramasseNickel((Nickel) items.get(i));
					if(items.get(i) instanceof Penny)
						hero.ramassePenny((Penny)items.get(i));
					pas2.add(items.get(i));
				}
				}
				else if(items.get(i) instanceof Heart) {
					if(hero.getLivesMax()>hero.getLives()&&(Physics.rectangleCollision(items.get(i).getPosition(),items.get(i).getSize(),hero.getPosition(),hero.getSize()))) {
					if(items.get(i) instanceof FullHeart) {
						FullHeart coeur = (FullHeart)items.get(i);
						coeur.soigne(hero);
					}
					else if(items.get(i) instanceof HalfHeart) {
						HalfHeart coeur = (HalfHeart)items.get(i);
						coeur.soigne(hero);
				}
				pas2.add(items.get(i));
			}
			}
				else if(items.get(i) instanceof Passive) {
					if(Physics.rectangleCollision(items.get(i).getPosition(),items.get(i).getSize(),hero.getPosition(),hero.getSize())) {
					if(items.get(i) instanceof BloodOfTheMartyr) {
						BloodOfTheMartyr botm=(BloodOfTheMartyr)items.get(i);
						botm.buff(hero);
					}
					if(items.get(i) instanceof RedHeart) {
						RedHeart rh=(RedHeart)items.get(i);
						rh.buff(hero);
					}
					pas2.add(items.get(i));
					}
				}
			}
		}
		items.removeAll(pas2);
		
		//Si le héro a tué tous les monstres, toutes les portes seront ouvertes
        // et un objet passif al�atoire a une chance d'appaitre en terminant la salle
		
        if(monsters.isEmpty()) {
            for(Door d: doors) {
                d.setOpen(true);
                d.accesALaPorte();
            }
            if(!added) {
                Random rd = new Random();
                if(rd.nextBoolean())
                	items.add(ListOfLists.getListPassiveItems().get(rd.nextInt(ListOfLists.getListPassiveItems().size())));
                else 
                	items.add(ListOfLists.getListActivatedItems().get(rd.nextInt(ListOfLists.getListActivatedItems().size())));
                added = true;
            }

        }		
   					
			
	}

	/*
	 * Getters and Setters
	 */

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}	

}
