package Game;

import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.util.ArrayList;
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
      
  private ArrayList<Structure> structures;
  private ArrayList<Item> items;
  private ArrayList<Enemy> enemies;
  
    
  private Map() {
  }
  
  public int getPlayerX() { return player_x; }
    
  public int getPlayerY() { return player_y; }
    
  public void initMap(int w, int h) {
    width = w;
    height = h;
    
    // プレイヤの初期位置
    player_x = 100;
    player_y = 0;
    
  }  
  
  public static Map getInstance(int w, int h, ArrayList<Structure> s, ArrayList<Item> i, ArrayList<Enemy> e) {
    singleton.initMap(w, h);
    singleton.structures = new ArrayList<Structure>(s); 
    singleton.items = new ArrayList<Item>(i); 
    singleton.enemies = new ArrayList<Enemy>(e);
    return singleton;
  }
  
  // 各要素の描画
  public void draw(Graphics g, Player p) {
    //g.fillRect(0, buttom+p.height+1, 600, 100);
    //drawStructure(g, floor1, p);
    //drawStructure(g, floor2, p);
      
    for ( Structure s : structures ) {
      drawStructure(g, s, p);
    }

      /*
    if ( item[0].isVisible() ) {
      drawItem(g, item[0], p);  //追加
    }
    if (enemy.isAlive()) {
      drawEnemy(g, enemy, p);
    }*/
    
    for ( Item i : items ) {
      if ( i.isVisible() ) { 
        drawItem(g, i, p);
      }
    }
    
    for ( Enemy e : enemies ) {
      if ( e.isAlive() ) {
        drawEnemy(g, e, p);
      }
    }
    //floor1.draw(g, getRelativePosition(floor1.getX(), p));
    //floor2.draw(g, getRelativePosition(floor2.getX(), p));
    p.draw(g, player_x);
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
    for ( Enemy enemy : enemies ) {
      if ( p.collidWithMaterial(enemy) && enemy.isAlive() ) {
        if ( p.onCharacter(enemy) ) {
          enemy.dead();
        } else {
          if ( p.isImmortal() ) {
            p.toDeadable();
          } else {
            p.dead();
          }
        } 
      }
    }

    // アイテム衝突判定
    for ( Item item : items ) {
      if ( item.isVisible() && p.collidWithMaterial(item) ) {
        p.getItem(item);
      }
    }
  }
  
  public void enemyMove(Player p) {
    for ( Enemy enemy : enemies ) {
      if ( isInScreen(enemy, p) ) {
        Vector v = enemy.getMoveDir();
        v.horizontal = -2;
        enemy.setMoveDir(v);
        characterMove(enemy);
      }
    }
  }
  
  // キャラクタの移動 (プレイヤ、エネミー共通)
  public void characterMove(AbstractCharacter c) {
    // プレイヤの移動方向の取得
    Vector v = c.getMoveDir();
      
    // 重力による落下
    if ( v.vertical < 5 ) {
      v.vertical += 1; 
    }
      
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
    for ( Structure structure : structures ) {
      if ( c.collidWithStructure(structure) ) {
        c.setY(structure.getTop() - c.getHeight());
        v.vertical = 0;
        c.landing();
      }
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
  
  // resetと同じ動作
  public void reset(Player p, ArrayList<Structure> s, ArrayList<Item> i, ArrayList<Enemy> e) {
    initMap(width, height);
    structures = new ArrayList<Structure>(s); 
    items = new ArrayList<Item>(i); 
    enemies = new ArrayList<Enemy>(e);
    p.setX(player_x);
    p.setY(player_y);
    p.reborn();
  }
}
