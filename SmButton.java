import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class SmButton{
	
	Font font = new Font("ummFont", Font.ITALIC, 48);
	
	private int cX;
	private int cY;
	private int width;
	private int height;
	private String name;
	private boolean selected = false;
	
	public SmButton(String Name){
		name = Name;
	}
	
	public void setX(int x){
		cX = x;
	}
	public int getX(){
		return cX;
	}
	
	
	public void setY(int y){
		cY = y;
	}
	public int getY(){
		return cY;
	}
	
	public boolean isSelected(int mX, int mY){
		if (mX>=cX && mX<=(cX + width) && mY>=(cY - height) && mY<=cY)
			return true;
		else
			return false;
	}
	
	public void setSelected(boolean bool){
		selected = bool;
	}
	public boolean getSelected(){
		return selected;
	}
	
	public void setWidthHeight(Graphics g){
		width = g.getFontMetrics(font).stringWidth(name);
		height = g.getFontMetrics(font).getHeight();
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public String getName(){
		return name;
	}
	
	public void draw (Graphics g){
		g.setFont(font);
		if (selected)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.BLUE);
		
		g.drawString(name, cX, cY);
	}
}