import javax.swing.JComponent;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;


public class KeyBinds {
	GameCode gameWindow;
	Player player;
	Cast cast;
	ActionBar actionBar;
	StartMenu startMenu;
	
	public KeyBinds (GameCode gw, StartMenu sm, Player pl, Cast cst, ActionBar ab) {
		gameWindow = gw;
		player = pl;
		actionBar = ab;
		cast = cst;
		startMenu = sm;
		
		setKeyBindings();
		setMouseBindings();
	}
	
	////////////
	//KEYBOARD//
	////////////

	
	private void setKeyBindings(){
	////////////	
	//MOVEMENT//
	////////////
		
		Action goLeft = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				player.moveLeft = true;
			}
		};
		Action goRight = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				player.moveRight = true;
			}
		};
		Action goUp = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				player.moveUp = true;
			}
		};
		Action goDown = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				player.moveDown = true;
			}
		};
		///////////
		Action goLeftCancel = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				player.moveLeft = false;
			}
		};
		Action goRightCancel = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				player.moveRight = false;
			}
		};
		Action goUpCancel = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				player.moveUp = false;
			}
		};
		Action goDownCancel = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				player.moveDown = false;
			}
		};
		
		
		
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0),"motionRight");
		gameWindow.getActionMap().put("motionRight",goRight);
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0),"motionLeft");
		gameWindow.getActionMap().put("motionLeft",goLeft);
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0),"motionUp");
		gameWindow.getActionMap().put("motionUp",goUp);
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0),"motionDown");
		gameWindow.getActionMap().put("motionDown",goDown);
		///////////
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0,true),"motionnRight");  //true means on release
		gameWindow.getActionMap().put("motionnRight",goRightCancel);
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0,true),"motionnLeft");
		gameWindow.getActionMap().put("motionnLeft",goLeftCancel);
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0,true),"motionnUp");
		gameWindow.getActionMap().put("motionnUp",goUpCancel);
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0,true),"motionnDown");
		gameWindow.getActionMap().put("motionnDown",goDownCancel);
		
		//////////
		//SKILLS//
		//////////
		
		Action spellSlot1 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(1, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_1,0),"useSpellSlot1");
		gameWindow.getActionMap().put("useSpellSlot1", spellSlot1);
		
		Action spellSlot2 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(2, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_2,0),"useSpellSlot2");
		gameWindow.getActionMap().put("useSpellSlot2", spellSlot2);
		
		Action spellSlot3 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(3, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_3,0),"useSpellSlot3");
		gameWindow.getActionMap().put("useSpellSlot3", spellSlot3);
		
		Action spellSlot4 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(4, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_4,0),"useSpellSlot4");
		gameWindow.getActionMap().put("useSpellSlot4", spellSlot4);
		
		Action spellSlot5 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(5, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_5,0),"useSpellSlot5");
		gameWindow.getActionMap().put("useSpellSlot5", spellSlot5);
		
		Action spellSlot6 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(6, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_6,0),"useSpellSlot6");
		gameWindow.getActionMap().put("useSpellSlot6", spellSlot6);
		
		Action spellSlot7 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(7, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_7,0),"useSpellSlot7");
		gameWindow.getActionMap().put("useSpellSlot7", spellSlot7);
		
		Action spellSlot8 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(8, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_8,0),"useSpellSlot8");
		gameWindow.getActionMap().put("useSpellSlot8", spellSlot8);
		
		Action spellSlot9 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(9, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_9,0),"useSpellSlot9");
		gameWindow.getActionMap().put("useSpellSlot9", spellSlot9);
		
		Action spellSlot0 = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				actionBar.useSpellSlot(0, getMouseX(), getMouseY());
			}
		};
		
		gameWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_0,0),"useSpellSlot0");
		gameWindow.getActionMap().put("useSpellSlot0", spellSlot0);
		
	}
	
	
		///////////
		// MOUSE //
		///////////
	private void setMouseBindings(){
		gameWindow.addMouseListener(new MListener());
	}
	
	public class MListener implements MouseListener{
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mousePressed(MouseEvent e){
			//uses a function depending at what position mouse was pressed
			switch (gameWindow.getState()){
				case 1: startMenu.pressAt(getMouseX(), getMouseY());
						break;
						
				case 2: cast.startCasting(new FireBall(), getMouseX(), getMouseY());
						actionBar.pressAt(getMouseX(), getMouseY());
						break;
			}
		}
		public void mouseReleased(MouseEvent e){}
		public void mouseClicked(MouseEvent e){}
		
		
	}
	
		
	private int getMouseX(){
			return (int) MouseInfo.getPointerInfo().getLocation().getX() - (int) gameWindow.getLocationOnScreen().getX();
		}
	private int getMouseY(){
			return (int) MouseInfo.getPointerInfo().getLocation().getY() - (int) gameWindow.getLocationOnScreen().getY();
		}
	
	
	
	
}