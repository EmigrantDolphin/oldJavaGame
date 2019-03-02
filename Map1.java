import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;


public class Map1{
	private Scanner data;
	private BufferedImage mapImage;
	private int mapHeight;
	private int mapWidth;
	private String collisionDir = "maps\\map1\\map1collision.txt";
	private String imgDir = "maps\\map1\\map1.png";
	
	
	public int collisionArray[][];
	
	public Map1(int WindowHeight,int WindowWidth){
		
		mapHeight = WindowHeight -100; //-100 to leave space for actionBar
		mapWidth = WindowWidth;
		collisionArray = new int [mapWidth][mapHeight];
		
		loading();
		
	}
	
	private void loading(){		
	
		try{
			data = new Scanner(new File(collisionDir));
			for (int x=0; x<mapWidth;x++)
				for(int y=0; y<mapHeight;y++)
					collisionArray[x][y] = Integer.parseInt(data.nextLine());
		}catch(Exception e){}
	
		try{
			mapImage = ImageIO.read(new File(imgDir));
		}catch(Exception e){};
	}
	
	public void draw(Graphics g){
		g.drawImage(mapImage,0,0,mapWidth,mapHeight,null);
	}
	
	public boolean isColliding( int x, int y, int width, int length){
		for (int i = x; i<=x+width; i++)
			for (int j = y; j<=y+length; j++)
				if (x<0 || x+width>mapWidth || y<0 || y+length>mapHeight)
					return true;
				else if (collisionArray[x][y] == 5)
					return true;
					
				
		return false;
	}
	
	
	//getters
	public int getHeight(){
		return mapHeight;
	}
	public int getWidth(){
		return mapWidth;
	}
	
}