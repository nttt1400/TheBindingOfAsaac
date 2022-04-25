package resources;

import java.util.ArrayList;

import gameWorld.Door;
import gameWorld.room.BossRoom;
import gameWorld.room.Magasin;
import gameWorld.room.MonsterRoom;
import gameWorld.room.Room;
import gameloop.Main;
import gameobjects.items.activated.Activated;
import gameobjects.items.activated.coins.Dime;
import gameobjects.items.activated.coins.Nickel;
import gameobjects.items.activated.coins.Penny;
import gameobjects.items.activated.hearts.FullHeart;
import gameobjects.items.activated.hearts.HalfHeart;
import gameobjects.items.passive.BloodOfTheMartyr;
import gameobjects.items.passive.Passive;
import gameobjects.items.passive.RedHeart;
import gameobjects.monsters.Fly;
import gameobjects.monsters.Monster;
import gameobjects.monsters.Spider;
import gameobjects.obstacles.Obstacle;
import gameobjects.obstacles.Rock;
import gameobjects.obstacles.Spikes;

public class ListOfLists {
	public static boolean[][] occupe;
	protected static ArrayList<Activated> listActivatedItems;
	protected static ArrayList<Passive> listPassiveItems;
	protected static ArrayList<Monster> listMonters;
	protected static ArrayList<Obstacle> listObstacles;
	protected static ArrayList<Room> listRooms;
	
	public ListOfLists() {
		
		occupe = new boolean[7][7];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				occupe[i][j] = false;
			}
		}	
		
		//Stocker des monstres dans une liste
		listMonters = new ArrayList<Monster>();
		
		listMonters.add(new Spider());
		listMonters.add(new Spider());
		listMonters.add(new Spider());
		listMonters.add(new Fly());
		listMonters.add(new Fly());
		
		//Stocker des obstacles dans une liste
		listObstacles = new ArrayList<Obstacle>();
		
		listObstacles.add(new Rock());
		listObstacles.add(new Spikes());
		listObstacles.add(new Rock());
		listObstacles.add(new Spikes());		
		listObstacles.add(new Rock());
		listObstacles.add(new Spikes());
		listObstacles.add(new Rock());
		listObstacles.add(new Spikes());
		
		//Stocker des objects consommatifs dans une liste
		listActivatedItems = new ArrayList<Activated>();
		
		listActivatedItems.add(new Penny());
		listActivatedItems.add(new Nickel());
		listActivatedItems.add(new Dime());
		
		listActivatedItems.add(new HalfHeart());
		listActivatedItems.add(new FullHeart());

		listActivatedItems.add(new Penny());
		listActivatedItems.add(new Nickel());
		listActivatedItems.add(new Dime());
		
		listActivatedItems.add(new HalfHeart());
		listActivatedItems.add(new FullHeart());

		//Stocker des objects passifs dans une liste
		listPassiveItems = new ArrayList<Passive>();
		
		listPassiveItems.add(new BloodOfTheMartyr());
		listPassiveItems.add(new RedHeart());
		listPassiveItems.add(new BloodOfTheMartyr());
		listPassiveItems.add(new RedHeart());
		

		
		//Stocker des salles dans une liste
		listRooms = new ArrayList<Room>(); 
		Room room1 = new Room(Main.isaac);
		MonsterRoom room2 = new MonsterRoom(Main.isaac);
		MonsterRoom room3 = new MonsterRoom(Main.isaac);
		Magasin room4 = new Magasin(Main.isaac);
		BossRoom room5 = new BossRoom(Main.isaac);
	
		//Stocker des portes dans chaque salle
		Door doorR101 = new Door(RoomInfos.POSITION_EAST_DOOR, room2, true);
		room1.getDoors().add(doorR101);
		
		Door doorR201 = new Door(RoomInfos.POSITION_WEST_DOOR, room1, false);
		Door doorR202 = new Door(RoomInfos.POSITION_SOUTH_DOOR, room3, false);
		Door doorR203 = new Door(RoomInfos.POSITION_NORTH_DOOR, room4, false);
		room2.getDoors().add(doorR201);
		room2.getDoors().add(doorR202);
		room2.getDoors().add(doorR203);
		
		Door doorR301 = new Door(RoomInfos.POSITION_NORTH_DOOR, room2, false);
		room3.getDoors().add(doorR301);

		Door doorR501 = new Door(RoomInfos.POSITION_SOUTH_DOOR, room4, false);
		room5.getDoors().add(doorR501);

		Door doorR401 = new Door(RoomInfos.POSITION_NORTH_DOOR, room5, true);
		Door doorR402 = new Door(RoomInfos.POSITION_SOUTH_DOOR, room2, true);
		room4.getDoors().add(doorR401);
		room4.getDoors().add(doorR402);

		listRooms.add(room1);
		listRooms.add(room2);		
		listRooms.add(room3);
		listRooms.add(room4);
		listRooms.add(room5);
		
		
	}

	public static ArrayList<Activated> getListActivatedItems() {
		return listActivatedItems;
	}

	public static void setListActivatedItems(ArrayList<Activated> listActivatedItems) {
		ListOfLists.listActivatedItems = listActivatedItems;
	}

	public static ArrayList<Passive> getListPassiveItems() {
		return listPassiveItems;
	}

	public static void setListPassiveItems(ArrayList<Passive> listPassiveItems) {
		ListOfLists.listPassiveItems = listPassiveItems;
	}

	public static ArrayList<Monster> getListMonters() {
		return listMonters;
	}

	public static void setListMonters(ArrayList<Monster> listMonters) {
		ListOfLists.listMonters = listMonters;
	}

	public static ArrayList<Obstacle> getListObstacles() {
		return listObstacles;
	}

	public static void setListObstacles(ArrayList<Obstacle> listObstacles) {
		ListOfLists.listObstacles = listObstacles;
	}

	public static boolean[][] getOccupe() {
		return occupe;
	}

	public static void setOccupe(boolean[][] occupe) {
		ListOfLists.occupe = occupe;
	}

	public static ArrayList<Room> getListRooms() {
		return listRooms;
		
	}

	public static void setListRooms(ArrayList<Room> listRooms) {
		ListOfLists.listRooms = listRooms;
	}

	

}