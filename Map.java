import java.awt.Graphics;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.MouseInfo;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.Dimension;

public class Map extends JPanel implements MouseListener,KeyListener{
	
	private File fl;
	private BufferedImage img;
	private PrintWriter write;
	private Scanner data;
	private String collisionDir = "maps\\map1\\map1collision.txt";
	private boolean startLoading = false;
	
	private int windowWidth;
	private int windowHeight;
	private int collisionArray[][];
	
	private int mPosX = 0;
	private int mPosY = 0;
	private int startingMousePosX = 0;
	private int startingMousePosY = 0;
	private boolean xAxisActivated = false;
	private boolean yAxisActivated = false;
	private boolean mousePressed = false;
	private boolean oneTimeRun = true;
	
	public Map(int winWidth, int winHeight){
		windowWidth= winWidth;
		windowHeight = winHeight;
		
		setFocusable(true);
		requestFocusInWindow();
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		addMouseListener(this);
		addKeyListener(this);

		try{
			fl = new File("maps\\map1\\map1.png");
			img = ImageIO.read(fl);
		}catch(Exception e){};
	
	}	
	
	
	private void readingCollision(Graphics g){
		if (mousePressed){
		
		
			if (xAxisActivated){
				mPosX=(int) MouseInfo.getPointerInfo().getLocation().getX() - (int) getLocationOnScreen().getX();
				
				if (mPosX<windowWidth && mPosX>0 && mPosY<windowHeight && mPosY>0)
					if(mPosX>startingMousePosX)
						for (int i = startingMousePosX; i<mPosX; i++){
							collisionArray[i][mPosY] = 5;
							g.setColor(java.awt.Color.RED);
							g.fillRect(i,mPosY,1,1);
						}
					else
						for (int i = startingMousePosX; i>mPosX; i--){
							collisionArray[i][mPosY] = 5;
							g.setColor(java.awt.Color.RED);
							g.fillRect(i,mPosY,1,1);
						}
			}
		
		
		
			
		
			if (yAxisActivated){
				mPosY=(int) MouseInfo.getPointerInfo().getLocation().getY() - (int) getLocationOnScreen().getY();
			
				if (mPosX<windowWidth && mPosX>0 && mPosY<windowHeight && mPosY>0)
					if(mPosY>startingMousePosY)
						for (int i = startingMousePosY; i<mPosY; i++){
							collisionArray[mPosX][i] = 5;
							g.setColor(java.awt.Color.RED);
							g.fillRect(mPosX,i,1,1);
						}
					else
						for (int i = startingMousePosX; i>mPosX; i--){
							collisionArray[mPosX][i] = 5;
							g.setColor(java.awt.Color.RED);
							g.fillRect(mPosX,i,1,1);
						}
			}	
		}
	}
	
	private void oneRun(Graphics g){
		System.out.println(windowWidth +" "+ windowHeight);
		collisionArray = new int [windowWidth][windowHeight];
		oneTimeRun = false;
		g.drawImage(img,0,0,windowWidth,windowHeight,null);
	}
	
	public void paintComponent(Graphics g){
		if (oneTimeRun){
			oneRun(g);
		}
		loadFile(g); //if L is pressed, but only paintComponent can activate;
		
		readingCollision(g); // if mouse is pressed, but only paintComponent can activate; (reading here, action of saving when pressed S)
		
		repaint();
	}
	
	//for key pressed
	private void saveToFile(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_Z){
			try{
				write = new PrintWriter(collisionDir,"UTF-8");
				for (int x = 0 ; x<windowWidth; x++)
					for (int y = 0; y<windowHeight; y++)
						write.println(collisionArray[x][y]);
				write.close();
			}catch(Exception c){
				System.out.println("in keyPressed");
			}
			System.out.println("saved");
		}
	}
	
	private void loadFile(Graphics g){
		if (startLoading){
			g.drawImage(img,0,0,windowWidth,windowHeight,null);
			try{
				data = new Scanner(new File(collisionDir));
				for (int x=0;x<windowWidth;x++)
					for(int y = 0; y<windowHeight;y++){
						collisionArray[x][y]= Integer.parseInt(data.nextLine());
						if (collisionArray[x][y] == 5){
							g.setColor(java.awt.Color.RED);
							g.fillRect(x,y,1,1);
						}
					}
			}catch(Exception e){}
			startLoading = false;
			System.out.println("loaded");
		}
	}
	
	private void activateXaxis(){
		xAxisActivated = true;
		yAxisActivated = false;
		System.out.println("X axis selected");
	}
	private void activateYaxis(){
		yAxisActivated = true;
		xAxisActivated = false;
		System.out.println("Y axis selected");
	}

	
	public void keyPressed(KeyEvent e){
		saveToFile(e);
		if (e.getKeyCode() == KeyEvent.VK_X){
			startLoading = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_C){
			activateXaxis();
		}
		if (e.getKeyCode() == KeyEvent.VK_V){
			activateYaxis();
		}

	};
	public void keyReleased(KeyEvent e){};
	public void keyTyped(KeyEvent e){};
	
	public void mouseClicked(MouseEvent e){};
	public void mouseEntered(MouseEvent e){};
	public void mouseExited(MouseEvent e){
		mousePressed=false;
	};
	public void mousePressed(MouseEvent e){
		mPosX=(int) MouseInfo.getPointerInfo().getLocation().getX() - (int) getLocationOnScreen().getX();
		mPosY=(int) MouseInfo.getPointerInfo().getLocation().getY() - (int) getLocationOnScreen().getY();
		
		startingMousePosX = (int) MouseInfo.getPointerInfo().getLocation().getX() - (int) getLocationOnScreen().getX();
		startingMousePosY = (int) MouseInfo.getPointerInfo().getLocation().getY() - (int) getLocationOnScreen().getY();
		mousePressed = true;
		
	};
	public void mouseReleased(MouseEvent e){
		mousePressed=false;
	};
	
	
}

