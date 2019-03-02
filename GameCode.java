import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.event.MouseListener;
import java.awt.Dimension;

import java.lang.System;


public class GameCode extends JComponent {
	//for fpsInfo(); 
	int fpsah=0;
	int currentFps=0;
	long savedTime = 0;
	//
	public int windowWidth;
	public int windowHeight;
	
	private int gameState = 1;
	
	Player player;
	Entity entity;
	Map1 map1;
	ActionBar actionBar;
	ModelLoop modelLoop;
	KeyBinds keyBinds;
	Attacks attacks;
	Cast casting;
	StartMenu startMenu;
	
	Host host;
	Client client;
	
	public GameCode(int winWidth, int winHeight){
		windowWidth = winWidth;
		windowHeight = winHeight;
		setFocusable(true);
		requestFocusInWindow();
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		
		init();
		
	}
	
	
	
	private void init(){
		
		host = new Host();
		client = new Client();
		
		
		map1 = new Map1(windowHeight,windowWidth);  // for size
		player = new Player(map1);  // for map border collision and map1 for collision
		startMenu = new StartMenu(this, player, host, client, windowWidth, windowHeight); // change ALOT
		entity = new Entity(player,map1); //for player position, map collision, and map borders collsion
		attacks = new Attacks (this,map1,player,entity);
		casting = new Cast (player,attacks, map1);
		actionBar = new ActionBar(this, windowWidth, windowHeight, casting);
		keyBinds = new KeyBinds(this,startMenu,player,casting, actionBar);  // keybinds for this jcomponent focus and player for his movements
		modelLoop = new ModelLoop(this,startMenu,player,entity,casting,attacks,actionBar);  // separate thread for calculating player and entity actions
		
		modelLoop.start();  //starts action thread
	}
	
	private void drawGame(Graphics g){
		map1.draw(g);
		entity.draw(g);
		player.draw(g);
		attacks.draw(g);
		casting.draw(g);
		actionBar.draw(g);
		
		g.drawString("fps: "+fpsInfo(),60,80);
		modelLoop.draw(g);
	}
	
	public void paintComponent(Graphics g){  // called when window opens/resizes. That's where all graphics should be and it's a loop if there is a repaint inside
		switch (gameState){
			case 1: startMenu.draw(g);
					break;
			case 2: drawGame(g);
					break;
		}
		
		repaint();
		fps(60);
	}
	

	
	//GETTERS SETTERS
	public int getState(){
		return gameState;
	}
	public void setState(int state){
		gameState = state;
	}
	
	
	//TEMPORAL HELPING METHODS
	private String fpsInfo(){
		if (System.currentTimeMillis() > savedTime){
			savedTime = System.currentTimeMillis()+1000;
			currentFps = fpsah;
			fpsah = 0;
		}
		else
			fpsah++;
		
		return Integer.toString(currentFps);
	}
	
	private void fps (int meh){
		try{
			Thread.sleep(1000/meh);
		}catch (Exception e){}
	}
	
}


