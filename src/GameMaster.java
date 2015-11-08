import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JApplet;
import javax.swing.JLabel;

import static java.awt.event.KeyEvent.*;

public class GameMaster extends JApplet implements Runnable, KeyListener {
	public static String UP = "up";
	public static String LEFT = "left";
	public static String RIGHT = "right";
	public static String NONE = "none";
	private Thread thread = null;
	JLabel lb = new JLabel();
	int x = 10, y = 100;
	private Player player;
	private Map map;
	//private KeyController keyController = KeyController.getInstance();
	private String dir = NONE;

	@Override
	public void init() {	
		setFocusable(true);
		addKeyListener(this);
		player = new Player(10, 10);
		map = Map.getInstance();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		player.move();
		dir = NONE;
		map.playerMove(player);
		map.draw(g, player);
	}
	public void keyPressed(KeyEvent e) {
		player.keyController.pressed(e);
		repaint();
	}
	
	public void keyReleased(KeyEvent e) {
		player.keyController.released(e);
		repaint();
	}
	public void keyTyped(KeyEvent e) {}	

	public void run() {
		Thread thisThread = Thread.currentThread();
		while ( thread == thisThread ) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {			
			}
			repaint();
		}
		
	}
	
	@Override
	public void start() {
		if ( thread == null ) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	@Override
	public void stop() {
		thread = null;
	}
}
