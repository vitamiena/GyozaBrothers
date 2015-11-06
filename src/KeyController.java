import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyController implements KeyListener {
	private static KeyController singleton = new KeyController();
	
	private KeyController() {
	}
	
	public static KeyController getInstance() {
		return singleton;
	}	
	
	public void keyPressed(KeyEvent e) {
		/*
		int key = e.getKeyCode();
		switch (key) {
		case VK_SPACE: dir = UP; break;
		case VK_LEFT: dir = LEFT; break;
		case VK_RIGHT: dir = RIGHT; break;
		default: dir = NONE; break;			
		}
		*/
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	

}
