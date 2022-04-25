package gameloop;

import gameWorld.GameWorld;
import gameobjects.character.Hero;
import libraries.StdDraw;
import libraries.Timer;
import resources.DisplaySettings;
import resources.ImagePaths;
import resources.ListOfLists;
import resources.RoomInfos;

public class Main
{
	public static Hero isaac;	
	public static ListOfLists list;


	public static void main(String[] args) 
	{	

		//Caratéristiques de Isaac
		isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM);
		list = new ListOfLists();

		// Hero, world and display initialisation.
		initializeDisplay();
		
		GameWorld world = new GameWorld(isaac);	


		// Main loop of the game
		while (!world.gameOver())
		{
			processNextStep(world);
		}
		if(world.gameOver()) {
		StdDraw.clear();
		if(isaac.getLives()<=0)
		StdDraw.picture(0.5, 0.5, ImagePaths.LOSE_SCREEN, 1.0, 1.0);
		else	
		StdDraw.picture(0.5, 0.5, ImagePaths.WIN_SCREEN, 1.0, 1.0);
		StdDraw.show();
		}
		
	}

	private static void processNextStep(GameWorld world)
	{
		Timer.beginTimer();
		StdDraw.clear();
		StdDraw.picture(0.5, 0.5, ImagePaths.BASEMENT, 1.0/9*7, 1.0/9*7);
		world.processUserInput();
		world.updateGameObjects();
		world.drawGameObjects();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}

	private static void initializeDisplay()
	{
		// Set the window's size, in pixels.
		// It is strongly recommended to keep a square window.
		StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
				RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);
		
		// Enables double-buffering.
		// https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
		StdDraw.enableDoubleBuffering();
	}
}
