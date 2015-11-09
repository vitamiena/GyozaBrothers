import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;

public class Map {
	private static Map singleton = new Map();
	
	protected int player_x = 10;
	protected int player_y = 200;
	protected int buttom = 200;
	Structure floor1; 
	Structure floor2;

	private Map() {
	}
	
	public static Map getInstance(int width, int height) {
		singleton.buttom = height - 50;
		singleton.player_x = 10;
		singleton.player_y = singleton.buttom;
		singleton.floor1 = new Structure(300, height - singleton.buttom, 0, singleton.buttom+10, Color.ORANGE);
		singleton.floor2 = new Structure(100, height - singleton.buttom, 350, singleton.buttom+10, Color.ORANGE);
		return singleton;
	}
	
	public void draw(Graphics g, Player p) {
		p.draw(g);
		//g.fillRect(0, buttom+p.height+1, 600, 100);
		floor1.draw(g);
		floor2.draw(g);
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
		if ( p.colidWithStructure(floor1) || p.colidWithStructure(floor2) ) {
			p.y = buttom;
			v.vertical = 0;
			p.landing();
		}
	}
}
