import java.awt.Graphics;

public class Map {
	private static Map singleton = new Map();
	
	protected int player_x = 10;
	protected int player_y = 200;
	protected int buttom = 200;
	
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
		
		if ( player_y <= buttom ) { // �d��
			v.vertical += 1; 
		} else { // ���C��
			if ( v.horizontal > 0 ) {
				v.horizontal -=1;
			} else if ( v.horizontal < 0 ) {
				v.horizontal += 1;
			}
		}
		p.setMoveDir(v);
		player_x += v.horizontal;
		player_y += v.vertical;
		// ���n
		if ( player_y > buttom ) {
			player_y = buttom;
			v.vertical = 0;
			p.landing();
		}
	}
}
