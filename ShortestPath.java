

public class ShortestPath {
	
	Map1 map1;

	int nextPosX = 1;
	int nextPosY = 1;

	int curPosX = 0;
	int curPosY = 0;
	
	public ShortestPath (int eX, int eY, int tX, int tY, Map1 m1){
		curPosX = eX;
		curPosY = eY;
		tarPosX = tX;
		tarPosY = tY;
		map1 = m1;
	}
	
	
	
	
	
	public int getNextX (){
		return nextPosX;
	}
	public int getNextY(){
		return nextPosY;
	}
}