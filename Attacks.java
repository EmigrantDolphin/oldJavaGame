import java.awt.Graphics;
import java.awt.MouseInfo;
import javax.swing.JComponent;
import java.util.ArrayList;

public class Attacks {
	GameCode gameWindow;
	ArrayList <SkillMovement> calcMovement = new ArrayList <SkillMovement>();
	ArrayList <SkillMovement> drawMovement = new ArrayList <SkillMovement>();
	
	KeyBinds keyBinds;
	Player player;
	Entity entity;
	Map1 map1;
	
	
	public Attacks (GameCode gw, Map1 mp, Player pl, Entity et) {
		player = pl;
		gameWindow = gw;
		entity = et;
		map1 = mp;
	}
	
	
	public void fire (SkillMovement skill){
		skill.cast(map1, player, getMouseX(), getMouseY(), entity);
		
		calcMovement.add(skill);
		drawMovement.add(skill);
	}
	
	
	public void draw (Graphics g){
		for (int i = 0; i < drawMovement.size(); i++)
			drawMovement.get(i).draw(g);
	}
	
	public void movement(){
		for (int i = 0; i < calcMovement.size(); i++)
			calcMovement.get(i).movement();
		
	}
	
	
	private int getMouseX(){
			return (int) MouseInfo.getPointerInfo().getLocation().getX() - (int) gameWindow.getLocationOnScreen().getX();
		}
	private int getMouseY(){
			return (int) MouseInfo.getPointerInfo().getLocation().getY() - (int) gameWindow.getLocationOnScreen().getY();
		}
	
	
}