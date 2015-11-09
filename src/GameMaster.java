import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JApplet;
import javax.swing.JLabel;

import static java.awt.event.KeyEvent.*;

public class GameMaster extends JApplet implements Runnable, KeyListener {
	private Thread thread = null;
	JLabel lb = new JLabel();
	int x = 10, y = 100;
	private Player player;
	private Map map;
	private Image img;
	private Graphics offg;
	private int width, height;
	//private KeyController keyController = KeyController.getInstance();
	
	@Override
	public void init() {	
		setFocusable(true);
		addKeyListener(this);		
		Dimension size = getSize();
		width = size.width;
		height = size.height;
		img = createImage(width, height);
		offg = img.getGraphics();
		
		map = Map.getInstance(width, height);
		player = new Player(10, 10, map.player_x, map.player_y);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);		
		offg.clearRect(0, 0, width, height);
		player.move();
		map.playerMove(player);
		map.draw(offg, player);
		g.drawImage(img, 0, 0, this);
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
			while ( player.isAlive ) {						
				repaint();	
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {			
				}
			}
			stop();
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
