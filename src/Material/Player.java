package Material;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.*;
import Env.*;

public class Player extends AbstractCharacter {
  private KeyController keyController = KeyController.getInstance();
    
  // コンストラクタ
  public Player(int w, int h, int x, int y) {
    super(w, h, x, y);
  }
 
  // 描画
  public void draw(Graphics g) {
    g.drawRect(getX(), getY(), getWidth(), getHeight());
  }
 
  public void draw(Graphics g, int tx) {
    g.drawRect(tx, getY(), getWidth(), getHeight());
  }
 
  public void move() {
    // プレイヤの移動(キー入力)
    Vector v = getMoveDir();
      
    // ジャンプキーが押されたとき
    if ( keyController.getUp() == KeyController.Key.Press ) {
      v.vertical -= 10;
      keyController.setUpKeep();
    } 

    // 左右キーが押されたとき
    if ( keyController.getLeft() == KeyController.Key.Press ) {
      keyController.setLeftKeep();
    } else if ( keyController.getRight() == KeyController.Key.Press ) {
      keyController.setRightKeep();
    }     
    
    // 左右への移動方向の設定
    if ( keyController.getLeft() == KeyController.Key.Keep ) {
      v.horizontal = -5;
    } else if ( keyController.getRight() == KeyController.Key.Keep ) {
      v.horizontal = 5;
    } else {
      v.horizontal = 0;
    }
    setMoveDir(v);
  }
  
  // 着地 (ジャンプキーの有効化)
  public void landing() {
    keyController.setUpRelease();
  }
  
  // キーが押された
  public void keyPressed(KeyEvent e) {
    keyController.pressed(e);
  }
  
  // キーが離された
  public void keyReleased(KeyEvent e) {
    keyController.released(e);
  }
  
  // Enemyクラスとの衝突
  public boolean collidWithEnemy(Enemy enemy) {
    Rectangle playerRec = new Rectangle(getX(), getY(), getWidth(), getHeight());
    Rectangle enemyRec = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        
    if ( playerRec.intersects(enemyRec) ) {
      return true;
    }
    return false;
  }
  
  public boolean enemyStamp(Enemy enemy) {
    int relativeY = Math.abs(enemy.getY()-getY()-enemy.getHeight());
    int relativeRightX = Math.abs(getRight()-enemy.getRight());
    int relativeLeftX = Math.abs(getLeft()-enemy.getLeft());
       
    if ( relativeY < 3 ) {
      if ( relativeRightX < getWidth() || relativeLeftX < getWidth() ) {
        return true;
      }
    }
    return false;
  }

  //**追加** Itemクラスとの衝突
  public boolean colidWithItem(Item i) {
    Rectangle playerRec = new Rectangle(getX(), getY(), getWidth(), getHeight());
    Rectangle itemRec = new Rectangle(i.getX(), i.getY(), i.getWidth(), i.getHeight());
      
    if ( playerRec.intersects(itemRec) ) {
      return true;
    }
    
    return false;
  }
}
