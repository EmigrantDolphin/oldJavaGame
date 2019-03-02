import java.lang.Math;

public class Teleport extends SkillMovement{
	private int ID = 2;
	private double castTime = 0.1;
	private double skillCooldown = 10.0;
	private int range = 300;
	
	public void cast(Map1 mp, Player player, int mouseX, int mouseY, Entity et){
			
			if (player.getPosX()+range>mouseX && player.getPosX()-range<mouseX && player.getPosY()+range>mouseY && player.getPosY()-range<mouseY){
				player.setPosX(mouseX);
				player.setPosY(mouseY);
			}else{
				
				mouseX -= player.getPosX();
				mouseY -= player.getPosY();
				
				player.setPosX((int)((mouseX*range)/Math.sqrt(mouseX*mouseX+mouseY*mouseY)) + player.getPosX());
				player.setPosY((int)((mouseY*range)/Math.sqrt(mouseX*mouseX+mouseY*mouseY)) + player.getPosY());
			}
	}
	
	public int getID(){
		return ID;
	}
	
	public double getCastTime(){
		return castTime;
	}
	
	public int getRange(){
		return range;
	}
	
	public double getCooldown(){
		return skillCooldown;
	}
	
}