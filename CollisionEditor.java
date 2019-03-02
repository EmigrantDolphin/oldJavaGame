import javax.swing.JFrame;

class CollisionEditor {
	public static void main (String[] args){
		JFrame window = new JFrame("collision editor");
		
		int windowWidth = 1400;
		int windowHeight = 800;
		
		Map map = new Map(windowWidth, windowHeight);
		window.add(map);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(true);
		window.setSize(windowWidth,windowHeight);
		
	}
}