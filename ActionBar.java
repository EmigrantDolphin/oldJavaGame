import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import javax.swing.JComponent;

public class ActionBar{
	
	private int windowWidth = 0;
	private int windowHeight = 0;
	
	private int distOnX = 10;
	private int distOnY = 15;
	private int skillButtonCube;
		
	private int spellBookCoordX = 2;
	private int spellBookCoordY = 2;
	private int spellBookWidth = 96;
	private int spellBookHeight = 96;
	
	
	private int spellSlotCount = 10;
	private int spellSlotSkillID[] = new int[spellSlotCount+1]; //so referred to actual button
	private int spellSlotCoordX[] = new int[spellSlotCount+1];
	private int spellSlotCoordY[] = new int[spellSlotCount+1];
	private int spellSlotCooldown[] = new int[spellSlotCount+1];
	private int spellSlotWidth;
	private int spellSlotHeight;
	private int spellSlotGap = 5;
	
	private Cast cast;
	private FireBall fireBall = new FireBall();
	private Teleport teleport = new Teleport();
	private SpellBook spellBook;
	private SpellIDs spellIDs = new SpellIDs();
	private GameCode gameWindow;
	
	
	private Font font = new Font ("new Font", Font.PLAIN, 18);
	
	public ActionBar (GameCode gw, int winWidth, int winHeight, Cast cst){
		windowWidth = winWidth;
		windowHeight = winHeight;
		cast = cst;
		gameWindow = gw;
		
		spellBook = new SpellBook(cst);
		setSpellSlotCoords();
		
		skillButtonCube = 70;
		
		spellSlotSkillID[1] = fireBall.getID(); // setting spells on actionBar from sb will have own functions
		spellSlotSkillID[2] = teleport.getID();
		
	}
	
	private void setSpellSlotCoords(){
		
		spellSlotWidth = (windowWidth - spellBookWidth - (spellSlotCount * spellSlotGap))/spellSlotCount;
		spellSlotHeight = spellBookHeight;
		
		for (int i = 1; i<= spellSlotCount; i++){
			spellSlotCoordY[i] = spellBookCoordY; // this makes it in line with spellBook button
			spellSlotCoordX[i] = spellSlotWidth * (i-1) + spellBookWidth+spellBookCoordX + (spellSlotGap*i);
		}
		
	}
	
	
	private void iconActions(int x, int y){
		if (spellBookPressed(x,y)){
			spellBook.changeStance();
		}
		
		
	}
	
	private boolean spellBookPressed (int x, int y){
		if (x>spellBookCoordX && x<spellBookWidth+spellBookCoordX && y>spellBookCoordY && y<spellBookHeight+spellBookCoordY) // if mouse clicked on the spellbook action bar coordinates 
			return true;
		else 
			return false;
		
	}
	
	private void spellBookIconDraw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(putAtX(spellBookCoordX), putAtY(spellBookCoordY), spellBookWidth, spellBookHeight);
		g.setColor(Color.BLACK);
		
		g.setFont(font);
		g.drawString("Spell \n Book", putAtX(3), putAtY(50));
	}
	
	private void spellOnMouseDraw(Graphics g){
		if (spellBook.getSelectedSpellID() != 0){
			g.setColor(Color.GREEN);
			g.fillRect(getMouseX() -10, getMouseY()-10, 20, 20); // change laters , all from skills
		}
	}
	
	private void spellSlotsDraw(Graphics g){
		
		for(int i = 1; i<=spellSlotCount; i++){
			if (spellSlotSkillID[i] == 0)
				g.setColor(Color.BLACK);
			else if ( isSpellSlotOnCooldown(i))
				g.setColor(Color.YELLOW);
			else
				g.setColor(Color.GREEN);
			
			g.fillRect(putAtX(spellSlotCoordX[i]), putAtY(spellSlotCoordY[i]), spellSlotWidth, spellSlotHeight);
		}
	}
	
	public void useSpellSlot(int spellSlot, int mouseX, int mouseY){
		if (!isSpellSlotOnCooldown(spellSlot)){
			if (!cast.isCasting())
				cast.startCasting(spellIDs.getSpellByID(spellSlotSkillID[spellSlot]), mouseX, mouseY);
			if (cast.isCasting())
				setSpellSlotCooldown(spellIDs.getSpellByID(spellSlotSkillID[spellSlot]), spellSlot);
		}
	}
	
	private void setSpellSlotCooldown(SkillMovement spell, int spellSlot){
		spellSlotCooldown[spellSlot] = (int) spell.getCooldown() * 60;
	}
	
	private boolean isSpellSlotOnCooldown(int i){
		
		if (spellSlotCooldown[i] <= 0)
			return false;
		else
			return true;
		
	}
	
	public void pressAt (int mouseX, int mouseY){
		iconActions(mouseX,windowHeight - mouseY); //makes it relative to actionBar
		
		spellBook.pressAt(mouseX, mouseY);
		actionBarPressAt(mouseX, mouseY);
	}
	
	private void actionBarPressAt(int mX, int mY){
		if (spellBook.getSelectedSpellID() != 0){
			
			for (int i = 1; i<=spellSlotCount; i++)
				if (getMouseX()>spellSlotCoordX[i] && getMouseX()<spellSlotCoordX[i]+spellSlotWidth && mToA(getMouseY())>spellSlotCoordY[i] && mToA(getMouseY()) < spellSlotCoordY[i]+spellSlotHeight){
					spellSlotSkillID[i] = spellBook.getSelectedSpellID();
					spellBook.delSelectedSpellID();
				}
			
		}
	}
	
	public void actions(){
		cooldowns();
	}
	
	
	private void cooldowns(){
		for (int i=1; i<=spellSlotCount; i++){
			if (spellSlotCooldown[i] > 0)
				spellSlotCooldown[i]--;
		}
	}
	
	public void draw (Graphics g){
		
		spellBookIconDraw(g);
		spellSlotsDraw(g);
		spellBook.draw(g);
		spellOnMouseDraw(g);
			
			
	
	}
	
	private int getMouseX(){
			return (int) MouseInfo.getPointerInfo().getLocation().getX() - (int) gameWindow.getLocationOnScreen().getX();
		}
	private int getMouseY(){
			return (int) MouseInfo.getPointerInfo().getLocation().getY() - (int) gameWindow.getLocationOnScreen().getY();
		}
	
	
	
	private int putAtX(int x){
		return x;
	}
	private int putAtY(int y){
		return windowHeight - 100 + y;
	}
	private int mToA(int mY){  //mouse to actionBar
		return mY - windowHeight + 100;
	}
	
	
	
	
}