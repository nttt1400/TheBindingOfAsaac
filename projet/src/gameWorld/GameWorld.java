package gameWorld;

import java.time.Duration;
import java.time.Instant;

import gameWorld.room.BossRoom;
import gameWorld.room.MonsterRoom;
import gameWorld.room.Room;
import gameloop.Main;
import gameobjects.character.Hero;
import gameobjects.character.Tear;
import libraries.StdDraw;
import resources.Controls;
import resources.ImagePaths;
import resources.ListOfLists;

public class GameWorld
{
	private static Room currentRoom;
	private Hero hero;
	private Instant instant;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		this.instant=Instant.now();
		currentRoom = ListOfLists.getListRooms().get(0);
		
	}

	public void processUserInput()
	{
		processKeysForMovement();
		processKeysForShot();
		Triche();
	}

	/*
	 * @return True quand le boss ou le joueur est mort 
	 */
	public boolean gameOver()
	{
		if(hero.getLives() == 0)
			return true;
		else if (currentRoom instanceof BossRoom)
			if(((BossRoom) currentRoom).BossDead()||((BossRoom) currentRoom).PlayerDead())
				return true;
		return false;
	}

	/*
	 * ouvre les portes d'une salle de monstre lorsqu'ils sont tous morts
	 */
	
	public void updateGameObjects()
	{
		currentRoom.updateRoom();
		if(currentRoom instanceof MonsterRoom) {
			if(((MonsterRoom)currentRoom).getMonsters().isEmpty()) {
				for(Door d:((MonsterRoom)currentRoom).getDoors()) {
					d.setOpen(true);
					d.setImagePath(ImagePaths.OPENED_DOOR);
					d.accesALaPorte();
				}
			}
		
		}
			currentRoom.getDoors();
	}

	/*
	 * Make every entity that compose a game world process one step
	 */
	
	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}
	

	/*
	 * toutes les touches de déplacement
	 */

	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp))
		{
			if(Main.isaac.getPosition().getY()+Main.isaac.getSize().getY()<(1.0/9*8))
				hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			if(Main.isaac.getPosition().getY()-Main.isaac.getSize().getY()/2>(1.0/9*1.5))
				hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			if(Main.isaac.getPosition().getX()+Main.isaac.getSize().getX()<(1.0/9*8))
				hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			if(Main.isaac.getPosition().getX()-Main.isaac.getSize().getX()/2>(1.0/9*1.5))
				hero.goLeftNext();
		}
		
	}
	/*
	 * toutes les touches de triches
	 */
	public void Triche() {
		if(StdDraw.isKeyPressed(Controls.gold)) {
			Instant time=Instant.now();
			if((double) Duration.between(instant,time).toMillis()>1000) {
			this.instant=time;
			if(Main.isaac.getCoins()+10 <= Main.isaac.getCoinsMax())
				Main.isaac.setCoins(Main.isaac.getCoins()+10);
			}
		}
		if(StdDraw.isKeyPressed(Controls.immortel)) {
			Main.isaac.setImmortal(true);
		}
		if(StdDraw.isKeyPressed(Controls.tueur)) {
			Main.isaac.setDegat(Main.isaac.getDegat()+1000);
		}
		if(StdDraw.isKeyPressed(Controls.vite)) {
			Instant time=Instant.now();
			if((double) Duration.between(instant,time).toMillis()>1000) {
			this.instant=time;
			Main.isaac.setSpeed(Main.isaac.getSpeed()+0.005);
			}
		}
		if(StdDraw.isKeyPressed(Controls.killAll)) {
			if (currentRoom instanceof MonsterRoom)
				((MonsterRoom)currentRoom).getMonsters().removeAll(((MonsterRoom)currentRoom).getMonsters());
			else if(currentRoom instanceof BossRoom)
				((BossRoom)currentRoom).getBoss().setLives(0);
		}
		
	}

	/*
	 * toutes les touches de tir
	 */
	public void processKeysForShot() {
		if(StdDraw.isKeyPressed(Controls.tirHaut))
			hero.tU(new Tear(this.hero.getPosition()));
		if(StdDraw.isKeyPressed(Controls.tirBas))
			hero.tD(new Tear(this.hero.getPosition()));
		if(StdDraw.isKeyPressed(Controls.tirGauche))
			hero.tL(new Tear(this.hero.getPosition()));
		if(StdDraw.isKeyPressed(Controls.tirDroite))
			hero.tR(new Tear(this.hero.getPosition()));
		
	}
	
	/*
	 * Getters and Setters
	 */
	
	public Room getCurrentRoom() {
		return currentRoom;
	}

	public static void setCurrentRoom(Room currentRoom) {
		GameWorld.currentRoom = currentRoom;
	}
}
