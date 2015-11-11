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
  private int check_point = 600;
    
  Structure floor1; 
  Structure floor2;
  private Item item[] = new Item[2];
  private Enemy enemy;
    
  private Map() {
  }
  
  public int getPlayerX() { return player_x; }
    
  public int getPlayerY() { return player_y; }
    
  public void initMap(int w, int h) {
    width = w;
    height = h;
    buttom = height - 50;
    // プレイヤの初期位置
    player_x = width/2;
    player_y = buttom;
    
    // 床の生成
    floor1 = new Structure(500, height - buttom, 0, buttom+10, Color.ORANGE);
    floor2 = new Structure(500, height - buttom, 550, buttom+10, Color.ORANGE);
    
    // アイテムの生成	
    item[0] = new Item(10, 10, 100, buttom, Color.BLUE);

    // エネミーの生成	
    enemy = new Enemy(30, 10, 200, buttom, Color.RED);
  }  
  
  public static Map getInstance(int w, int h) {
    singleton.initMap(w, h);
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
    characterMove(p);
    
    // 死亡判定
    if ( p.getTop() > height ) {
      p.dead();
      return;
    }
    
    // エネミー衝突判定
    if ( p.collidWithEnemy(enemy) && enemy.isAlive() ) {
      if ( p.enemyStamp(enemy) ) {
        enemy.dead();
      } else {
        p.dead();
      }
    }

    // アイテム衝突判定
    if ( p.colidWithItem(item[0]) ) {
      item[0].toInvisible();
    }
  }
  
  public void enemyMove() {
    Vector v = enemy.getMoveDir();
    v.horizontal = -2;
    enemy.setMoveDir(v);
    characterMove(enemy);
  }
  
  // キャラクタの移動 (プレイヤ、エネミー共通)
  public void characterMove(AbstractCharacter c) {
    // プレイヤの移動方向の取得
    Vector v = c.getMoveDir();
      
    // 重力による落下
    v.vertical += 1; 
      
    // 摩擦力による減速
    if ( v.horizontal > 0 ) {
      v.horizontal -=1;
    } else if ( v.horizontal < 0 ) {
      v.horizontal += 1;
    }
    
    // 自然の要素(重力、摩擦力)による移動方向の修正
    c.setMoveDir(v);
    c.setX(c.getX() + v.horizontal);
    c.setY(c.getY() + v.vertical);
    // 着地判定
    if ( c.collidWithStructure(floor1) || c.collidWithStructure(floor2) ) {
      c.setY(buttom);
      v.vertical = 0;
      c.landing();
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
  
  public void retry(Player p) {
    if ( p.getLeft() <= check_point ) {
      p.setX(player_x);
      p.setY(player_y);
    } else {
      p.setX(check_point);
      p.setY(player_y);
    }
    p.reborn();
  }
  
  public void reset(Player p) {
    initMap(width, height);
    p.setX(player_x);
    p.setY(player_y);
    p.reborn();
  }
}
