import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;

public class Map {
	private static Map singleton = new Map();
	
	protected int player_x = 10;
	protected int player_y = 200;
	protected int buttom = 200;
	Structure floor = new Structure(300, 100, 0, buttom+10, Color.ORANGE);

	private Map() {
	}
	
	public static Map getInstance() {		
		return singleton;
	}
	
	public void draw(Graphics g, Player p) {
		p.draw(g);
		//g.fillRect(0, buttom+p.height+1, 600, 100);
		floor.draw(g);
	}
	
	public void playerMove(Player p) {
		Vector v = p.getMoveDir();
		
		v.vertical += 1; 
		
		if ( v.horizontal > 0 ) {
			v.horizontal -=1;
		} else if ( v.horizontal < 0 ) {
			v.horizontal += 1;
		}
		
		p.setMoveDir(v);
		p.x += v.horizontal;
		p.y += v.vertical;
		// ’…’n
		if ( p.colidWithStructure(floor) ) {
			p.y = buttom;
			v.vertical = 0;
			p.landing();
		}
	}
}
