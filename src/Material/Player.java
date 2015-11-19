package Material;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;
import static java.awt.event.KeyEvent.*;
import java.awt.*;
import Env.*;

public class Player extends AbstractCharacter {
  private KeyController keyController = KeyController.getInstance();
  private int jumpHeight;
  private boolean isImmortal;
  // コンストラクタ
  public Player(int w, int h, int x, int y) {
    super(w, h, x, y);
    jumpHeight = 10;
    isImmortal = false;
  }

  @Override
  public void reborn() {
    super.reborn();
    jumpHeight = 10;
    isImmortal = false;
  }

  // 描画
  public void draw(Graphics g) {
    g.drawRect(getX(), getY(), getWidth(), getHeight());
  }
 
  public void draw(Graphics g, int tx) {
    g.setColor(Color.GREEN);
    g.fillRect(tx, getY(), getWidth(), getHeight());
  }
 
  public void move() {
    // プレイヤの移動(キー入力)
    Vector v = getMoveDir();
    
    // ジャンプキーが押されたとき
    if ( keyController.getUp() == KeyController.Key.Press ) {
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
    
    if ( ( ! isJumping() ) && keyController.getUp() == KeyController.Key.Keep ) {
      v.vertical -= jumpHeight;
      jump();
    }
    
    setMoveDir(v);
  }
  
  // キーが押された
  public void keyPressed(KeyEvent e) {
    keyController.pressed(e);
  }
  
  // キーが離された
  public void keyReleased(KeyEvent e) {
    keyController.released(e);
  }
  
  // AbstractMaterialクラスとの衝突
  public boolean collidWithMaterial(AbstractMaterial material) {
    Rectangle playerRec = getRectangle();
    Rectangle materialRec = material.getRectangle();
        
    return playerRec.intersects(materialRec);
  }

  // 直前の方向の判断
  public boolean onCharacter(AbstractCharacter character) {
    if ( previousPointY() - character.previousPointY() < 0 ) { 
      return true;
    }
    return false;
  }
  
  public boolean enemyStamp(Enemy enemy) {
    int relativeY = enemy.getY()-getY();
    int relativeRightX = Math.abs(getRight()-enemy.getRight());
    int relativeLeftX = Math.abs(getLeft()-enemy.getLeft());
       
    if ( relativeY < getHeight() ) {
      if ( relativeRightX < getWidth() || relativeLeftX < getWidth() ) {
        return true;
      }
    }
    return false;
  }

  public void getItem(Item i) {
    // TODO : item種類に応じた挙動
    jumpHeight = 20;
    i.toInvisible();
  }

  public boolean isImmortal() {
    return isImmortal;
  }

  public void toDeadable() {
    isImmortal = false;
  }
}
