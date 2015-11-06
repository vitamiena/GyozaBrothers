import java.awt.Graphics;

public class Player extends AbstractCharacter {
	public Player(int w, int h) {
		super(w, h);
	}
	
	public void draw(Graphics g, int x, int y) {
		g.drawRect(x, y, width, height);
	}
}
