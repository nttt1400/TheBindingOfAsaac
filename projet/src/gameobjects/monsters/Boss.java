package gameobjects.monsters;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import gameloop.Main;
import gameobjects.character.Hero;
import gameobjects.character.Tear;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Boss extends Monster {
	private Instant instant;
	private LinkedList<Tear> larmes;
	public Boss() {
		super(8,0.01/8);
		this.size = new Vector2(RoomInfos.TILE_WIDTH, RoomInfos.TILE_HEIGHT).scalarMultiplication(2);
		this.imagePath = ImagePaths.BOSS;
		this.larmes= new LinkedList<Tear>();
		this.instant=Instant.now();
		this.degat = 2;
	}
	
	/*
	*@param h un héros
	*enleve les pv infligés par les larmes du héros sur contact
	*/
	
	public void hurt(Hero h) {
		List<Tear>a = h.getLarmes();
		if(a.isEmpty()==false) {
		for(int i =0;i<a.size();i++) {
		if (Physics.rectangleCollision(this.getPosition(),this.size,a.get(i).getPosition(),a.get(i).getSize())==true) {
			this.setLives(lives-h.getDegat());
			a.remove(i);
		}
		}
		}
	}
	
	/*
	*@param h un héros, t une larme
	*vise et lance une larme en direction du héro
	*/
	
	public void larme(Tear t,Hero h) {
		Instant time=Instant.now();
		if((double) Duration.between(instant,time).toMillis()>1000) {
		this.instant=time;
		t.setDirection(new Vector2(h.getPosition().getX()-this.getPosition().getX(),h.getPosition().getY()-this.getPosition().getY()));
		this.larmes.add(t);
		}

	}

	public void tir() {
		for (int i=0;i<this.larmes.size();i++) {
			Vector2 direction=this.larmes.get(i).getNormalizedDirection();
			Vector2 position=this.larmes.get(i).getPosition().addVector(direction);
			if(larmes.get(i).getPosition().getX()>RoomInfos.TILE_WIDTH && larmes.get(i).getPosition().getX()<1-RoomInfos.TILE_WIDTH && larmes.get(i).getPosition().getY()>RoomInfos.TILE_WIDTH && larmes.get(i).getPosition().getY()<1-RoomInfos.TILE_WIDTH)
			this.larmes.get(i).setPosition(position);
			else larmes.remove(i);
		}
	}
	
	/*
	 * Make every entity that compose a boss process one step
	 */
	
	public void updateGameObject()
	{
		this.setDirection(Main.isaac.getPosition().subVector(getPosition()));
		tir();
		move();
	}

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		super.setDirection(new Vector2());
	}
	
	/*
	 * Normalise the direction vector
	 */
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(this.getDirection());
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	
	/*
	 * affiche
	 */
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY());
		if (larmes.isEmpty()==false) {
			for(int i=0; i<larmes.size();i++) {
				larmes.get(i).drawGameObject();
			}
		}
	}
	
	/*
	 * Getters and Setters
	 */
	
	public LinkedList<Tear> getLarmes() {
		return larmes;
	}
	public void setLarmes(LinkedList<Tear> larmes) {
		this.larmes = larmes;
	}
	
}