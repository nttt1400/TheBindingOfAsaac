package gameWorld.room;

import java.time.Duration;
import java.time.Instant;

import gameobjects.character.Hero;
import gameobjects.character.Tear;
import gameobjects.monsters.Boss;

public class BossRoom extends Room{
	
	private Boss boss;
	private Instant instant;

	public BossRoom(Hero hero) {
		super(hero);
		boss = new Boss();
		this.instant=Instant.now();
	}
	/*
	 * @return True quand le boss est mort
	 */
	public boolean BossDead() {
		if(this.getBoss().getLives()<=0)
			return true;
		else return false;
	}
	public boolean PlayerDead() {
		if(this.hero.getLives()<=0)
			return true;
		else return false;
	}

	/*
	 * affiche
	 */
	
	public void drawRoom()
	{	
		super.drawRoom();
		boss.drawGameObject();

	}
	
	/*
	 * Make every entity that compose a room process one step
	 */

	public void updateRoom() {
		super.updateRoom();
		hero.hurtB(this.boss);
		boss.updateGameObject();
		boss.larme(new Tear(boss.getPosition()),hero);
		for(Tear t: boss.getLarmes()) {
			t.drawGameObject();
		}
		boss.hurt(hero);
		Instant time=Instant.now();
		if(!hero.isImmortal()) {
			if((double) Duration.between(instant,time).toMillis()>1100) {
				this.instant=time;
				hero.hurt(boss);
				}
			}
	}
	
	/*
	 * Getters and Setters
	 */
	
	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}
}