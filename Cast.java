import java.awt.Graphics;
import java.lang.System;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

// cast -> attack -> skillMovement (FireBall etc)

public class Cast {
	
	Attacks attack;
	Player player;
	SkillMovement skill;
	Map1 map1;
	
	private boolean isCastingActive = true;
	
	private int castedAmountGraphics = 0;
	private double castedAmountCalc = 0;
	private double castIterator = 0;
	private int finishedCastAmount = 0; // in constructor
	
	private boolean startCasting = false;
	
	public Cast (Player pl, Attacks at, Map1 mp){
		attack = at;
		player = pl;
		map1 = mp;
		
		finishedCastAmount = player.getWidth();
		
	}
	
	public void disableCasting(){
		isCastingActive = false;
	}
	public void enableCasting(){
		isCastingActive = true;
	}
	
	
	public void startCasting (SkillMovement sk, int mouseX, int mouseY){
		if (mouseX>0 && mouseX<map1.getWidth() && mouseY>0 && mouseY<map1.getHeight() && isCastingActive){
				skill = sk;
				
				castIterator = finishedCastAmount / ((60) * skill.getCastTime());  //finishedCastAmount (graphic)  / how many frames is the casting aka time
				
				
				
				startCasting = true;
				
				save1 = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

			
		}
	}
	
	long save1 = 0;
	
	public void movement(){
		if (startCasting){
			castedAmountCalc += castIterator;
			castedAmountGraphics = (int) castedAmountCalc;
		}
		
		if (castedAmountCalc >= finishedCastAmount){
			startCasting = false;
			castedAmountCalc = 0;
			castedAmountGraphics = 0;
			System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - save1);
			
			///////////////// when finished casting
			attack.fire(skill);
		}
		
	}
	
	public void draw(Graphics g){
		
		if (startCasting){
			g.setColor(Color.BLACK);
			g.fillRect(player.getPosX(), player.getPosY()-20, finishedCastAmount, 7);
			
			g.setColor(Color.YELLOW);
			g.fillRect(player.getPosX(), player.getPosY()-20, castedAmountGraphics, 7);
		}
		
	}
	
	public boolean isCasting(){
		return startCasting;
	}
	
}