import java.lang.Thread;
import java.lang.System;
import java.awt.Graphics;
import javax.swing.JComponent;

public class ModelLoop extends Thread{
	
	Player player;
	Entity entity;
	Cast cast;
	Attacks attacks;
	ActionBar actionBar;
	GameCode gameWindow;
	StartMenu startMenu;
	
	public ModelLoop(GameCode gw, StartMenu sm, Player pl, Entity en, Cast cst, Attacks at, ActionBar ab){
		player = pl;
		entity = en;
		cast = cst;
		attacks = at;
		actionBar = ab;
		gameWindow = gw;
		startMenu = sm;
	}
	
	private void gameActions(){
		attacks.movement();
		player.movement();
		entity.action();
		cast.movement();
		actionBar.actions();
	}
	
	public void run(){
		while (true){
			switch (gameWindow.getState()){
				case 1: startMenu.actions();
						break;
				case 2: gameActions();
						break;
			}
			
			
		
			fpsInfo();
			try{
				Thread.sleep(1000/60);
			}catch(Exception e){};
		}
	}
	
	
	long savedTime=0;
	int fpsah = 0;
	int currentFps = 0;
	private void fpsInfo(){
			if (System.currentTimeMillis() > savedTime){
				savedTime = System.currentTimeMillis()+1000;
				currentFps = fpsah;
				fpsah = 0;
			}
			else
				fpsah++;
		}
		
	public void draw (Graphics g){
		g.drawString("fps: "+Integer.toString(currentFps),60,100);
	}
	
}

