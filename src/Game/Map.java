package Game;

import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import Env.*;
import Material.*;

public class Map {
	private static Map singleton = new Map();
	
	private int player_x;
	private int player_y;
	protected int buttom;
	Structure floor1; 
	Structure floor2;

	private Map() {
	}

  public int getPlayerX() { return player_x; }
  
  public int getPlayerY() { return player_y; }
	
	public static Map getInstance(int width, int height) {
		singleton.buttom = height - 50;
    // プレイヤの初期位置
		singleton.player_x = 10;
		singleton.player_y = singleton.buttom;
    
    // 床の生成
		singleton.floor1 = new Structure(300, height - singleton.buttom, 0, singleton.buttom+10, Color.ORANGE);
		singleton.floor2 = new Structure(100, height - singleton.buttom, 350, singleton.buttom+10, Color.ORANGE);
		return singleton;
	}
	
  // 各要素の描画
	public void draw(Graphics g, Player p) {
		p.draw(g);
		//g.fillRect(0, buttom+p.height+1, 600, 100);
		floor1.draw(g);
		floor2.draw(g);
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
}
