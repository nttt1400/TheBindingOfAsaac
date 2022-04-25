package gameobjects.character;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import gameobjects.items.Item;
import gameobjects.items.activated.coins.Dime;
import gameobjects.items.activated.coins.Nickel;
import gameobjects.items.activated.coins.Penny;
import gameobjects.monsters.Boss;
import gameobjects.monsters.Fly;
import gameobjects.monsters.Monster;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Spikes;
import libraries.Physics;
import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Hero
{
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int livesMax;
	private int lives;
	private Tear tears;
	private double damage;
	private int coins;
	private int coinsMax;
	private LinkedList<Tear> larmes;
	private Instant instant;
	private boolean canGoUp;
	private boolean canGoDown;
	private boolean canGoLeft;
	private boolean canGoRight;
	private boolean immortal;
	private int reload;
	private int degat;

	public Hero(Vector2 position)
	{
		this.position = position;
		this.size = HeroInfos.ISAAC_SIZE;
		this.speed = HeroInfos.ISAAC_SPEED;
		this.imagePath = ImagePaths.ISAAC;
		this.direction = new Vector2();
		this.livesMax = 6;
		this.lives = 6;
		this.larmes= new LinkedList<Tear>();
		this.coinsMax = 50;
		this.coins = 5;
		this.reload=500;
		this.instant=Instant.now();
		this.canGoUp=true;
		this.canGoDown=true;
		this.canGoRight=true;
		this.canGoLeft=true;
		this.immortal=false;
		this.degat=1;
	}

	/*
	 * Make every entity that compose a hero process one step
	 */

	public void updateGameObject()
	{
		move();
		tir();
		portee();
	}

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	/*
	 * @param m un monstre s une pique
	 * Blesse le héros au contact ou au contact d'un tir
	 */

	public void hurt(Monster m) {
		if (Physics.rectangleCollision(this.position,this.size,m.getPosition(),m.getSize())==true) {
			this.setLives(this.getLives()-m.getDegat());
			}
		}
	public void hurt(Spikes s) {
		if (Physics.rectangleCollision(this.position,this.size,s.getPosition(),s.getSize())==true) {
			this.setLives(this.lives-s.getDegat());
			}
		}
	public void hurtF(Fly f) {
		List<Tear>a = f.getLarmes();
		if(a.isEmpty()==false) {
		for(int i =0;i<a.size();i++) {
		if (Physics.rectangleCollision(this.position,this.size,a.get(i).getPosition(),a.get(i).getSize())==true) {
			if(!this.isImmortal())
			this.setLives(lives-f.getDegat());
			a.remove(i);
		}
		}
		}
	}
	public void hurtB(Boss b) {
		List<Tear>a = b.getLarmes();
		if(a.isEmpty()==false) {
		for(int i =0;i<a.size();i++) {
		if (Physics.rectangleCollision(this.position,this.size,a.get(i).getPosition(),a.get(i).getSize())==true) {
			if(!this.isImmortal())
			this.setLives(lives-b.getDegat());
			a.remove(i);
		}
		}
		}
	}
	/*
	 * Détermine la portée du tir
	 */

	public void portee() {
		List<Tear>a=this.getLarmes();
		if(a.isEmpty()==false) {
			for(int i =0;i<a.size();i++) {
				Instant time=Instant.now();
				if((double) Duration.between(instant,time).toMillis()>480) {
				this.instant=time;
				a.remove(i);
			}
		}
	}
		
	}
	/*
	 * @param i une piece
	 * Ramasse une piece
	 */

	public boolean ramasseItem(Item i) {
		if (Physics.rectangleCollision(this.position, this.size, i.getPosition(), i.getSize())) {
			return true;
		}
		return false;	
	}

	public void ramasseDime(Dime d) {
		if (Physics.rectangleCollision(this.position, this.size, d.getPosition(), d.getSize())) {
			if(this.getCoins()+10 <= this.getCoinsMax())
				this.setCoins(this.getCoins()+10);
		}
	}
	public void ramasseNickel(Nickel n) {
		if (Physics.rectangleCollision(this.position, this.size, n.getPosition(), n.getSize())) {
			if(this.getCoins()+5 <= this.getCoinsMax())
				this.setCoins(this.getCoins()+5);
		}
	}
	public void ramassePenny(Penny p) {
		if (Physics.rectangleCollision(this.position, this.size, p.getPosition(), p.getSize())) {
			if(this.getCoins()+1 <= this.getCoinsMax())
				this.setCoins(this.getCoins()+1);
		}	
	}
	
	/*
	 *  @param t une larme
	 *  détermine la direction du tir
	 */

	public void tU(Tear t) {
		Instant time=Instant.now();
		if((double) Duration.between(instant,time).toMillis()>reload) {
		this.instant=time;
		t.getDirection().addY(1);
		this.larmes.add(t);
		}
		
	}
	public void tD(Tear t) {
		Instant time=Instant.now();
		if((double) Duration.between(instant,time).toMillis()>reload) {
		this.instant=time;
		t.getDirection().addY(-1);
		this.larmes.add(t);
		}
	
	}
	
	public void tL(Tear t) {
		Instant time=Instant.now();
		if((double) Duration.between(instant,time).toMillis()>reload) {
		this.instant=time;
		this.larmes.add(t);
		t.getDirection().addX(-1);
		}
		
	}
	public void tR(Tear t) {
		Instant time=Instant.now();
		if((double) Duration.between(instant,time).toMillis()>reload) {
		this.instant=time;
		this.larmes.add(t);
		t.getDirection().addX(1);
		}
	}
	
	private void tir() {
		for (int i=0;i<this.larmes.size();i++) {
			Vector2 direction=this.larmes.get(i).getNormalizedDirection();
			Vector2 position=this.larmes.get(i).getPosition().addVector(direction);
			if(larmes.get(i).getPosition().getX()>RoomInfos.TILE_WIDTH && larmes.get(i).getPosition().getX()<1-RoomInfos.TILE_WIDTH && larmes.get(i).getPosition().getY()>RoomInfos.TILE_WIDTH && larmes.get(i).getPosition().getY()<1-RoomInfos.TILE_WIDTH)
			this.larmes.get(i).setPosition(position);
			else larmes.remove(i);
		}
	}
	
	/*
	 * @parma r un rocher
	 * Check la collision entre rocher et héros
	 */

	public void collisionRocherUp(Rock r) {
		Vector2 test = new Vector2();
		test.setX(this.position.getX());
		test.setY(this.position.getY()+0.0067);
		if (Physics.rectangleCollision(test,this.size,r.getPosition(),r.getSize())){
			this.canGoUp=false;
		}
		else canGoUp=true;
	}
	
	public void collisionRocherDown(Rock r) {
		Vector2 test = new Vector2();
		test.setX(this.position.getX());
		test.setY(this.position.getY()-0.0067);
		if (Physics.rectangleCollision(test,this.size,r.getPosition(),r.getSize())){
			this.canGoDown=false;
		}
		else canGoDown=true;
	}
	public void collisionRocherLeft(Rock r) {
		Vector2 test = new Vector2();
		test.setX(this.position.getX()-0.0067);
		test.setY(this.position.getY());
		if (Physics.rectangleCollision(test,this.size,r.getPosition(),r.getSize())){
			this.canGoLeft=false;
		}
		else canGoLeft=true;
	}
	public void collisionRocherRight(Rock r) {
		Vector2 test = new Vector2();
		test.setX(this.position.getX()+0.0067);
		test.setY(this.position.getY());
		if (Physics.rectangleCollision(test,this.size,r.getPosition(),r.getSize())){
			this.canGoRight=false;
		}
		else canGoRight=true;
	}
	
	/*
	 * Affiche le héros et ses larmes
	 */

	public void drawGameObject()
	{
		System.out.println(this.getDegat());
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		if (larmes.isEmpty()==false) {
			for(int i=0; i<larmes.size();i++) {
				larmes.get(i).drawGameObject();
				
			}
		}
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
		int i = 0;
		for(; i < (getLives())/2; i++)
			StdDraw.picture(0.05+i*0.05, 1-0.05, ImagePaths.HEART_HUD);
		
		if(getLives()%2==1) {
			StdDraw.picture(0.05+i*0.05, 1-0.05, ImagePaths.HALF_HEART_HUD);
			i++;
		}	
		for (int j = 0; j < (getLivesMax()-getLives())/2; j++) {
			StdDraw.picture(0.05+(i+j)*0.05, 1-0.05, ImagePaths.EMPTY_HEART_HUD);
		}
		
		StdDraw.text(0.8, 0.95, "Coins: " + String.valueOf(getCoins()));

	}

	/*
	 * Déplacement
	 */
	public void goUpNext()
	{
		if(this.position.getY()<1-RoomInfos.TILE_WIDTH&&canGoUp)
		getDirection().addY(1);
		
	}

	public void goDownNext()
	{
		if(this.position.getY()>RoomInfos.TILE_WIDTH&&canGoDown)
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		if(this.position.getX()>RoomInfos.TILE_WIDTH&&canGoLeft)
			getDirection().addX(-1);
	}

	public void goRightNext()
	{
		if(this.position.getX()<1-RoomInfos.TILE_WIDTH&&canGoRight)
			getDirection().addX(1);
	}

	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}

	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getSize()
	{
		return size;
	}

	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public Vector2 getDirection()
	{
		return direction;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}

	public int getLives() {
		return lives;
	}

	public int getLivesMax() {
		return livesMax;
	}

	public void setLivesMax(int livesMax) {
		this.livesMax = livesMax;
	}

	public Tear getTears() {
		return tears;
	}

	public void setTears(Tear tears) {
		this.tears = tears;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public LinkedList<Tear> getLarmes() {
		return larmes;
	}

	public void setLarmes(LinkedList<Tear> larmes) {
		this.larmes = larmes;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public boolean isCanGoUp() {
		return canGoUp;
	}

	public void setCanGoUp(boolean canGoUp) {
		this.canGoUp = canGoUp;
	}

	public boolean isCanGoDown() {
		return canGoDown;
	}

	public void setCanGoDown(boolean canGoDown) {
		this.canGoDown = canGoDown;
	}

	public boolean isCanGoLeft() {
		return canGoLeft;
	}

	public void setCanGoLeft(boolean canGoLeft) {
		this.canGoLeft = canGoLeft;
	}

	public boolean isCanGoRight() {
		return canGoRight;
	}

	public void setCanGoRight(boolean canGoRight) {
		this.canGoRight = canGoRight;
	}

	public boolean isImmortal() {
		return immortal;
	}

	public void setImmortal(boolean immortal) {
		this.immortal = immortal;
	}

	public int getReload() {
		return reload;
	}

	public void setReload(int reload) {
		this.reload = reload;
	}

	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public int getCoinsMax() {
		return coinsMax;
	}

	public void setCoinsMax(int coinsMax) {
		this.coinsMax = coinsMax;
	}

	
}
