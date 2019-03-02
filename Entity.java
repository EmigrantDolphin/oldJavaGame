import java.awt.Graphics;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Color;
import java.lang.Thread;

public class Entity{
	
	private int posX = 660;
	private int posY = 500;
	private int entityWidth = 30;
	private int entityHeight = 30;
	private int targetX;
	private int targetY;
	private int speed = 2;
	private int attackDistance = 60;
	private int aggroDistance = 340;
	private int maxHealth = 25;
	private int health = 25;
	
	private int mapWidth = 0; //set in constructor
	private int mapHeight = 0;
	
	Player player;
	Map1 map1;
	
	public Entity(Player p,Map1 m1){
		player = p;
		map1 = m1;
		mapWidth = map1.getWidth();
		mapHeight = map1.getHeight();
	}
	
	private int getHealthBar(){
		return (int) (health*entityWidth)/maxHealth;
	}
	
	public void dealDamage (int damage){
		health -= damage;
	}
	public int getPosX (){
		return posX;
	}
	public int getPosY(){
		return posY;
	}
	public int getWidth(){
		return entityWidth;
	}
	public int getHeight(){
		return entityHeight;
	}
	
	private int getProximity(){
		double proximity = 0.0;
		int x = targetX - posX;
		int y = targetY - posY;
		proximity = Math.sqrt(x*x + y*y);
		
		return (int) proximity;
	}
	
	private void updateTargetCord(){
		targetX = player.getPosX();
		targetY = player.getPosY();
	}
	//////////////////////////////
	/// ALL BELOW FOR MOVEMENT ///
	//////////////////////////////
	private boolean isColliding(int x, int y){
		boolean result = false;
		
		for (int i = x ; i<entityWidth + x ; i++){
			for (int j = y; j<entityHeight + y; j++)
				if (map1.collisionArray[i][j] == 5){
					result = true;
					break;
				}
			if (result)
				break;
		}
				
		return result;
	}
	
	private void movement(){
		if (targetX>posX && getProximity()>attackDistance && getProximity()<aggroDistance && !isColliding(posX+speed,posY)){
			posX+=speed;
		}
		if (targetX<posX && getProximity()>attackDistance && getProximity()<aggroDistance && !isColliding(posX-speed,posY)){
			posX-=speed;
		}
		if (targetY>posY && getProximity()>attackDistance && getProximity()<aggroDistance && !isColliding(posX,posY+speed)){
			posY+=speed;
		}
		if (targetY<posY && getProximity()>attackDistance && getProximity()<aggroDistance && !isColliding(posX,posY-speed)){
			posY-=speed;
		}
	}
	
	public void action(){
		updateTargetCord();
		movement();
	}
	
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(posX,posY,entityWidth,entityHeight);
		g.setColor(Color.BLACK);
		g.fillRect(posX,posY-10,entityWidth,7);
		g.setColor(Color.GREEN);
		g.fillRect(posX,posY-10,getHealthBar(),7);
	}
	
}