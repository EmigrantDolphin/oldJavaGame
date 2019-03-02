import java.awt.Graphics;
import java.lang.Math;
import java.awt.Color;
import java.lang.System;

public class FireBall extends SkillMovement{
	
	Entity entity;
	Player player;
	Map1 map1;
	
	private int ID = 1;
	
	private int posX = 0;
	private int posY = 0;
	private double doublePosX = 0.0;
	private double doublePosY = 0.0;
	private int checkingPosX = 0;
	private int checkingPosY = 0;
	
	private int width = 7;
	private int length = 7;
	private int speed = 5; //real projectile speed , leaves the loop at this iterator
	private final int checkingSpeed = 1; // checks the path of projectile by every pixel for possible collision etc
	private double xCheckingSpeed = 0;
	private double yCheckingSpeed = 0;
	private int damage = 3;
	private String description = "A very powerfull fireball. \n Damage: "+Integer.toString(damage);
	
	private int launchedTime = 0;
	private int endTime = 100;
	
	private double castTime = 1.0;  //seconds 
	
	
	
	public FireBall(){
		//deez nutz
		
	}
	
	
	
	public void cast(Map1 mp, Player pl, int mouseX, int mouseY, Entity et){
		entity = et;
		player = pl;
		map1 = mp;
		
		mouseX -= (int) width/2; // so projectile is in the middle;
		mouseY -= (int) length/2;
		
		if (mouseX == player.getPosX())
			mouseX++;
		if (mouseY == player.getPosY())
			mouseY++;
	
		posX = player.getPosX();
		posY = player.getPosY();
		doublePosX = posX;
		doublePosY = posY;
		
		
		mouseX -= posX;
		mouseY -= posY;
		
		xCheckingSpeed = ((checkingSpeed * mouseX) / Math.sqrt( (mouseX * mouseX) + (mouseY * mouseY) ) );
		yCheckingSpeed = ((checkingSpeed * mouseY) / Math.sqrt( (mouseX * mouseX) + (mouseY * mouseY) ) );
	
		launchedTime = 1;
	
	}
	
	public void movement(){
		
		if (launchedTime < 100 && launchedTime > 0) {
			
			for (int i=0; i<speed; i++){  // skips to req speed, but checks every pixel for anything u like ex. collision
				doublePosX += xCheckingSpeed;
				doublePosY += yCheckingSpeed;
				
				checkingPosX = (int) doublePosX;
				checkingPosY = (int) doublePosY;
				
				if (hitEntity()){
					entity.dealDamage(damage);
					launchedTime = -100;
					doublePosX = -20;
					doublePosY = -20;
					checkingPosX = -20;
					checkingPosY = -20;
					break;
				}
				
				if (map1.isColliding(checkingPosX, checkingPosY, width, length)){
					launchedTime = -100;
					doublePosX = -20;
					doublePosY = -20;
					checkingPosX = -20;
					checkingPosY = -20;
					break;
				}
				
			}//leaves for loop  // if no hit or collision, transfers doublePosX to posX etc.
			
			posX= (int) doublePosX; // purely for drawing
			posY= (int) doublePosY;
		
			launchedTime++;
		} else
			launchedTime = 0;
	
	}
	
	private boolean hitEntity(){
		boolean isHit = false;
		for ( int i = 0; i<width; i++)
			for (int j = 0; j<length; j++)
				if (entity.getPosX() < checkingPosX+i && ( entity.getWidth()+entity.getPosX() ) > checkingPosX+i  &&  entity.getPosY() < checkingPosY+j && ( entity.getHeight()+entity.getPosY() ) > checkingPosY+j)
					return true;
				else
					isHit = false;
		return isHit;
	}
	
	public void draw (Graphics g){
		
		if (launchedTime != 100 && launchedTime != 0){
			g.setColor(Color.RED);
			g.fillRect(posX,posY,width,length); // later to be changed to img
		}
	}
	
	
	public int getID(){
		return ID;
	}
	public String getDescription(){
		return description;
	}
	public double getCastTime (){
		return castTime;
	}
	
}