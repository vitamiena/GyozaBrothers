import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class KeyController {
	public enum Key { Press, Keep, Release }
	private static KeyController singleton = new KeyController();
	private Key right = Key.Release;
	private Key left = Key.Release;
	private Key up = Key.Release;
	
	private KeyController() {
	}
	
	public static KeyController getInstance() {
		return singleton;
	}	
	
	public void pressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case VK_SPACE: 
			if ( up == Key.Release ) {
				up = Key.Press; 
			}
			break;
		case VK_LEFT: 
			if ( left == Key.Release ) {
				left = Key.Press;
			}
			break;
		case VK_RIGHT: 
			if ( right == Key.Release ) {
				right = Key.Press;
			}
			break;
		default: ; break;			
		}
	}
	
	public void released(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case VK_LEFT: left = Key.Release; break;
		case VK_RIGHT: right = Key.Release; break;
		default: ; break;			
		}		
	}
	
	public Key getRight() {
		return right;
	}
	public Key getLeft() {
		return left;
	}
	public Key getUp() {
		return up;
	}
	public void setUpKeep() {
		up = Key.Keep;
	} 	
	public void setUpRelease() {
		up = Key.Release;
	}
	public void setRightKeep() {
		right = Key.Keep;
	}
	public void setLeftKeep() {
		left = Key.Keep;
	}
}
