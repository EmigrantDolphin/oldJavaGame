
public class SpellIDs {
	
	
	public SpellIDs(){
	
	}
	
	public SkillMovement getSpellByID(int ID){
		switch (ID){
			case 1: return new FireBall();	
			case 2: return new Teleport();
		}
		
		return null;
	}
	
}