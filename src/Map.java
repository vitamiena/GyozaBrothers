import java.awt.Graphics;

public class Map {
	private static Map singleton = new Map();
	
	protected int player_x = 10;
	protected int player_y = 100;
	protected int buttom = 100;
	
	private Map() {
	}
	
	public static Map getInstance() {		
		return singleton;
	}
	
	public void draw(Graphics g, Player p) {
		p.draw(g, player_x, player_y);
	}
	
	public void playerMove(Player p) {
		Vector v = p.getMoveDir();
		
		if ( player_y < 100 ) { // d—Í
			v.vertical += 1;
		} else { // –€ŽC—Í
			if ( v.horizontal > 0 ) {
				v.horizontal -=1;
			} else if ( v.horizontal < 0 ) {
				v.horizontal += 1;
			}
		}
		p.setMoveDir(v);
		player_x += v.horizontal;
		player_y += v.vertical;
		if ( player_y > buttom ) {
			player_y = buttom;
		}
	}
}
