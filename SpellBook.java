import java.awt.Graphics;
import java.awt.Color;

public class SpellBook {
	
	private final int sbCoordX = 345;
	private final int sbCoordY = 213;
	private final int sbWidth = 693;
	private final int sbHeight = 426;
	
	private final int skillCount = 20;
	private int skillCoordX[] = new int[skillCount+1]; // so ID 1 is slot 1
	private int skillCoordY[] = new int[skillCount+1];
	
	private int skillSlot[] = new int[skillCount+1];
	
	private int selectedSpellID = 0;
	
	private final int gapX = 3;
	private final int gapY = 2;
	private final int skillWidth = sbWidth / 5 - gapX; 
	private final int skillHeight = sbHeight / 4 - gapY;
	
	private boolean isOpen = false;
	
	private Cast casting;
	private FireBall fireBall = new FireBall();
	private Teleport teleport = new Teleport();
	
	public SpellBook(Cast cst){
		casting = cst;
		
		setSkillCoords();
		setSkills();
		
		
	}
	
	private void setSkillCoords(){
		int skillNR = 1;
		for (int i=1; i<=4; i++){
			for (int j=1; j<=5; j++){
				skillCoordX[skillNR] = sbCoordX + gapX*(j) + skillWidth*(j-1) ;  // -3 on width, but +3 on coordX
				skillCoordY[skillNR] = sbCoordY + gapY*(i) + skillHeight*(i-1) ;
				skillNR++;
			}
		}
	}
	
	private void setSkills(){
		skillSlot[1] = fireBall.getID();
		skillSlot[2] = teleport.getID();
	}
	
	public void pressAt(int mouseX, int mouseY){
		if (isOpen){
			for (int i = 1; i<=skillCount; i++)
			if (mouseX > skillCoordX[i] && mouseX < skillCoordX[i]+skillWidth && mouseY > skillCoordY[i] && mouseY < skillCoordY[i]+skillHeight){
				selectedSpellID = skillSlot[i];
			}
			
		}
	}
	
	public void open (){
		
		casting.disableCasting();
		
		isOpen = true;
		
	}
	
	public void close () {
		isOpen = false;
		casting.enableCasting();
	}
	
	public void changeStance () {
		
		if (isOpen)
			close();
		else
			open();
		
	}
	
	private void drawSpells(Graphics g){
		
		for(int i=1; i<=skillCount; i++){
			if(skillSlot[i] == 0)
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.GREEN); // or mb skillIDs.getSpellByID(skillSlot[i]).getColor(); ?? LATERS 
			
			g.fillRect(skillCoordX[i], skillCoordY[i], skillWidth, skillHeight);
		}
	}

	
	public void draw (Graphics g){
		if (isOpen){
			g.setColor(Color.RED);
			g.fillRect(sbCoordX, sbCoordY, sbWidth, sbHeight);
			
			drawSpells(g);
		}
		
	}
	
	public int getSelectedSpellID(){
		return selectedSpellID;
	}
	public void delSelectedSpellID(){
		selectedSpellID = 0;
	}
	
}