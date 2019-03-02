import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.MouseInfo;

public class StartMenu {
	private int winWidth = 0;
	private int winHeight = 0;
	
	private boolean buttonCoordsSet = false;
	private boolean allowActions = false;
	private String selectedButtonName = "";
	
	// for Single Player Button
	
	private final String[] buttonName = {"Single Player", "Join Game", "Host Game"}; // add more for more options, but dont forget to change switch
	private SmButton[] button = new SmButton[buttonName.length];
	private final int gap = 20; //pixels between buttons
	
	private GameCode gameCode;
	private Player player;
	private Host host;
	private Client client;
	
	public StartMenu(GameCode gw, Player pl, Host ht, Client cl, int WinWidth, int WinHeight){
		gameCode = gw;
		host = ht;
		client = cl;
		player = pl;
		
		winWidth = WinWidth;
		winHeight = WinHeight;
		
	}
	
	
	public void pressAt(int mX, int mY){
		for (SmButton Button : button){
			if (Button.isSelected(getMouseX(), getMouseY())){
				selectedButtonName = Button.getName();
				break;
			}
		}
		
		switch (selectedButtonName){ // if button name is the same as the name for an action.
			case "Single Player": gameCode.setState(2); // sets state to game
								break;
			case "Join Game":   System.out.println(buttonName[1]+" was selected.");
								client = new Client();
								client.getNeededVars("127.0.0.1",gameCode,player);
								client.start();
								break;
			case "Host Game":   System.out.println(buttonName[2]+" was selected.");
								host = new Host();
								host.getNeededVars(gameCode, player);
								host.start();
								break;
		}
		selectedButtonName = "";
		
	}
	
	public void actions(){
		if (allowActions){
			for (SmButton Button : button){	
				if (Button.isSelected(getMouseX(), getMouseY()))
					Button.setSelected(true);
				else
					Button.setSelected(false);
			}
			
		}
	}
	
	private void oneTime(Graphics g){
		if (!buttonCoordsSet){
			//setting button cordds || set width height cuz its automatic depending on font and graphics
			for (int i = 0; i<button.length; i++){
				button[i] = new SmButton(buttonName[i]);
				button[i].setWidthHeight(g);
			}
			
			int boxPixelAmount = (button[0].getHeight()+gap)*button.length; //deciding start point for y depending on amount of buttons
			int yStart = (int) winHeight/2 - boxPixelAmount/2;
			
			for (int i = 0; i<button.length; i++){
				button[i].setX((int) winWidth/2 - button[i].getWidth()/2);
				button[i].setY(yStart + (button[i].getHeight()+gap)*i);
			}
			
			//
			
			buttonCoordsSet = true;
			allowActions = true;
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0,0,winWidth, winHeight);
		
		oneTime(g);
		drawButtons(g);
	}
	
	private void drawButtons(Graphics g){
		for (SmButton Button : button){
			Button.draw(g);
		}
	}
	
	private int getMouseX(){
			return (int) MouseInfo.getPointerInfo().getLocation().getX() - (int) gameCode.getLocationOnScreen().getX();
		}
	private int getMouseY(){
			return (int) MouseInfo.getPointerInfo().getLocation().getY() - (int) gameCode.getLocationOnScreen().getY();
		}
	
}