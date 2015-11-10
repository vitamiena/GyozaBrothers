package Game;

import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import Env.*;
import Material.*;

public class Map {
	private static Map singleton = new Map();
	
  private int width;
  private int height;
	private int player_x;
	private int player_y;
	protected int buttom;
  
	Structure floor1; 
	Structure floor2;

	private Map() {
	}

  public int getPlayerX() { return player_x; }
  
  public int getPlayerY() { return player_y; }
	
	public static Map getInstance(int w, int h) {
    singleton.width = w;
    singleton.height = h;
		singleton.buttom = singleton.height - 50;
    // プレイヤの初期位置
		singleton.player_x = singleton.width/2;
		singleton.player_y = singleton.buttom;
    
    // 床の生成
		singleton.floor1 = new Structure(500, singleton.height - singleton.buttom, 0, singleton.buttom+10, Color.ORANGE);
		singleton.floor2 = new Structure(500, singleton.height - singleton.buttom, 550, singleton.buttom+10, Color.ORANGE);
		return singleton;
	}
	
  // 各要素の描画
	public void draw(Graphics g, Player p) {
		p.draw(g, player_x);
		//g.fillRect(0, buttom+p.height+1, 600, 100);
		//floor1.draw(g, getRelativePosition(floor1.getX(), p));
		//floor2.draw(g, getRelativePosition(floor2.getX(), p));
    drawStructure(g, floor1, p);
    drawStructure(g, floor2, p);
	}
	
  // プレイヤの移動
	public void playerMove(Player p) {
    try {
      // プレイヤの移動方向の取得
      Vector v = p.getMoveDir();
      
      // 重力による落下
      v.vertical += 1; 
      
      // 摩擦力による原則
      if ( v.horizontal > 0 ) {
        v.horizontal -=1;
      } else if ( v.horizontal < 0 ) {
        v.horizontal += 1;
      }
      
      // 自然の要素(重力、摩擦力)による移動方向の修正
      p.setMoveDir(v);
      p.setX(p.getX() + v.horizontal);
      p.setY(p.getY() + v.vertical);
      // 着地判定
      if ( p.colidWithStructure(floor1) || p.colidWithStructure(floor2) ) {
        p.setY(buttom);
        v.vertical = 0;
        p.landing();
      }
    } catch( MaterialsException e ) {
    }
	}
  
  public int getRelativePosition(int x, Player p) { //プレイヤとの相対位置
    return x - p.getX() + player_x;
  }
  
  public void drawStructure(Graphics g, Structure s, Player p) {

    if ( isInScreen(s, p) ) {
      s.draw(g, getRelativePosition(s.getLeft(), p));
    }
  }
  
  public boolean isInScreen(AbstractMaterial m, Player p) {
    int left, right;
    
    left = getRelativePosition(m.getLeft(), p);
    right = getRelativePosition(m.getRight(), p);
    
    if ( left > width ) { return false; }
    if ( right < 0 ) { return false; }
    
    return true;
  }
}
