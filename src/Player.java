import java.awt.Graphics;

public class Player extends AbstractCharacter {
	public KeyController keyController = KeyController.getInstance();
	public Player(int w, int h, int x, int y) {
		super(w, h, x, y);
	}
	
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
	
	public void move() {
		if ( keyController.getUp() == KeyController.Key.Press ) {
			moveDir.vertical -= 10;
			keyController.setUpKeep();
		} 
		if ( keyController.getLeft() == KeyController.Key.Press ) {
			keyController.setLeftKeep();
		} else if ( keyController.getRight() == KeyController.Key.Press ) {
			keyController.setRightKeep();
		} 	
		if ( keyController.getLeft() == KeyController.Key.Keep ) {
			moveDir.horizontal = -5;
		} else if ( keyController.getRight() == KeyController.Key.Keep ) {
			moveDir.horizontal = 5;
		} else {
			moveDir.horizontal = 0;
		}
	}
	
	public void landing() {
		keyController.setUpRelease();
	}
}
