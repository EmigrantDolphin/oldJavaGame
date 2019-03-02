import java.awt.Graphics;

abstract public class SkillMovement {
	private int ID = 0;
	private double castTime = 0;
	private double skillCooldown = 0;
	private int range = 5000;
	
	public void movement(){
		
	}
	
	public void draw(Graphics g){
		
	}
	
	public double getCooldown(){
		return skillCooldown;
	}
	
	public double getCastTime(){
		return castTime;
	}
	public int getID(){
		return ID;
	}
	
	public int getRange(){
		return range;
	}
	
	public void cast (Map1 mp, Player pl, int mouseX, int mouseY, Entity et){
		System.out.println("something wrong");
	}
	
	
	
}