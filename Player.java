import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.Thread;
import java.awt.Color;

public class Player extends Thread{
	private int mapWidth;
	private int mapHeight;
	private BufferedImage img;
	
	public boolean moveRight = false;
	public boolean moveLeft = false;
	public boolean moveUp = false;
	public boolean moveDown = false;
	
	private int movementSpeed = 3;
	private int currentPositionX= 70;
	private int currentPositionY= 130;
	private int playerWidth = 24;
	private int playerHeight = 24;
	private int maxHealth = 100;
	private int health = 100;
	
	private Map1 map1;
	
	
	
	public Player(Map1 mapp1){
		map1 = mapp1;
		mapWidth = map1.getWidth();
		mapHeight = map1.getHeight();	
	}
	
	
	// Collision and movement
	
	private boolean willCollide(int nextX, int nextY){
		boolean result = false;
		for (int x = nextX; x<playerWidth+nextX; x++){
			for (int y = nextY; y<playerHeight+nextY;y++)
				if (map1.collisionArray[x][y] == 5){  //if the next position has collision in the array, then collide
					result=true;
					break;
				}
			if(result)
				break;
		}
		
		return result;
	}
	

	
	public void movement (){
		if (moveRight) //triggers when button is pressed. check setKeyBindings
			if (currentPositionX+movementSpeed>=mapWidth - playerWidth) // if next position(speed + current position) exceeds the window border then
				currentPositionX=mapWidth - playerWidth; //then place me on the edge of the border. x coordinate
			else if (willCollide(currentPositionX+movementSpeed,currentPositionY))
				System.out.println("Collision!");
			else
				currentPositionX+=movementSpeed; //else place me onto next positon (current position + speed) right
		
		if (moveLeft)
			if (currentPositionX-movementSpeed<=0) //if next position exceeds window border to the left
				currentPositionX=0; //place me next to the border coord x
			else if (willCollide(currentPositionX-movementSpeed,currentPositionY))
				System.out.println("Collision!");
			else
				currentPositionX-=movementSpeed;  //else move me to the next position (current position - speed) (left)
		
		if (moveDown)
			if (currentPositionY+movementSpeed>=mapHeight - playerHeight) ////same for y coordinate
				currentPositionY=mapHeight - playerHeight;
			else if (willCollide(currentPositionX,currentPositionY+movementSpeed))
				System.out.println("Collision!");
			else
				currentPositionY+=movementSpeed;
		
		if (moveUp)
			if(currentPositionY-movementSpeed<=0)
				currentPositionY=0;
			else if (willCollide(currentPositionX,currentPositionY-movementSpeed))
				System.out.println("Collision!");
			else
				currentPositionY-=movementSpeed;
		//System.out.println(mapWidth);
	}

	private int getHealthBar(){
		return (int) (health*playerWidth)/maxHealth;
	}
	
	public void draw(Graphics g){
		g.setColor (Color.GREEN);
		g.fillRect(currentPositionX,currentPositionY,playerWidth,playerHeight);
		
		
		g.setColor(Color.BLACK);
		g.fillRect(currentPositionX,currentPositionY-10,playerWidth,7);
		g.setColor(Color.GREEN);
		g.fillRect(currentPositionX,currentPositionY-10,getHealthBar(),7);
	
	}
	
	//GETTERS
	public int getPosX(){  //for other classes
		return currentPositionX;
	}
	public int getPosY(){
		return currentPositionY;
	}
	public int getWidth(){
		return playerWidth;
	}
	public int getHeight(){
		return playerHeight;
	}
	//SETTERS
	public void setPosX(int x){
		currentPositionX = x;
	}
	public void setPosY(int y){
		currentPositionY = y;
	}
}