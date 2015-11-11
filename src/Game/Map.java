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
    private Item item[] = new Item[2];
    private Enemy enemy;
    
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
        
        // アイテムの生成	
        singleton.item[0] = new Item(10, 10, 100, singleton.buttom, Color.BLUE);

        // エネミーの生成	
        singleton.enemy = new Enemy(30, 10, 200, singleton.buttom, Color.RED);
      return singleton;
    }
  
    // 各要素の描画
    public void draw(Graphics g, Player p) {
      p.draw(g, player_x);
        //g.fillRect(0, buttom+p.height+1, 600, 100);
        drawStructure(g, floor1, p);
        drawStructure(g, floor2, p);
        
        if ( item[0].isVisible() ) {
          drawItem(g, item[0], p);  //追加
        }
      if (enemy.isAlive()) {
        drawEnemy(g, enemy, p);
      }
      //floor1.draw(g, getRelativePosition(floor1.getX(), p));
      //floor2.draw(g, getRelativePosition(floor2.getX(), p));
    }
  
    // プレイヤの移動
    public void playerMove(Player p) {
      try {
        // プレイヤの移動方向の取得
        Vector v = p.getMoveDir();
          
          // 重力による落下
          v.vertical += 1; 
          
          // 摩擦力による原則
          v.vertical += 1; 
          
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
          if ( p.collidWithStructure(floor1) || p.collidWithStructure(floor2) ) {
            p.setY(buttom);
              v.vertical = 0;
              p.landing();
          }

        // エネミー衝突判定
        if ( p.collidWithEnemy(enemy) && enemy.isAlive() ) {
          if ( p.enemyStamp(enemy) ) {
            enemy.Dead();
          } else {
            p.Dead();
          }
        }

        // アイテム衝突判定
        if ( p.colidWithItem(item[0]) ) {
          item[0].toInvisible();
        }
      } catch( MaterialsException e ) {
      }
    }
  public void enemyMove() {
    try {
      Vector v = enemy.getMoveDir();

      enemy.setX(enemy.getX()+v.horizontal);
      enemy.setY(enemy.getY()+v.vertical);
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
  
    public void drawItem(Graphics g, Item i, Player p) {
      if ( isInScreen(i,p) ) {
        i.draw(g, getRelativePosition(i.getLeft(), p));
      }
    }

  public void drawEnemy(Graphics g, Enemy enemy, Player p) {
    if ( isInScreen(enemy,p) ) {
      enemy.draw(g, getRelativePosition(enemy.getLeft(), p));
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
