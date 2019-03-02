import javax.swing.JFrame;
import java.awt.Dimension;


class umm {
	public static void main (String[] args){
		JFrame window = new JFrame("umm");
		
		int windowWidth = 1400;
		int windowHeight = 900;
		
		GameCode gc = new GameCode(windowWidth, windowHeight);
		window.add(gc);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);		
		window.pack();
		window.setVisible(true);
		//gc.focus();
	}
}