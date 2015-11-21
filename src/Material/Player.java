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
  private int dashSpeed;
  private boolean isImmortal;
  
  // コンストラクタ
  public Player(int w, int h, int x, int y, Color c) {
    super(w, h, x, y, c);
    jumpHeight = 10;
    dashSpeed = 5;
    isImmortal = false;
  }

  @Override
  public void reborn() {
    super.reborn();
    jumpHeight = 10;
    dashSpeed = 5;
    isImmortal = false;
  }

  // 描画
  public void draw(Graphics g) {
    g.drawRect(getX(), getY(), getWidth(), getHeight());
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
      v.horizontal = -dashSpeed;
    } else if ( keyController.getRight() == KeyController.Key.Keep ) {
      v.horizontal = dashSpeed;
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
  
  // 直前の方向の判断(AbstractMaterial)
  public boolean onMaterial(AbstractMaterial material) {
    return ( material.getY() - previousPointY() > 0 );
  }

  public boolean underMaterial(AbstractMaterial material) {
    return ( material.getY() - previousPointY() < 0 );
  }

  public boolean leftMaterial(AbstractMaterial material) {
    return ( material.getX() - previousPointX() > 0 );
  }
  
  public boolean rightMaterial(AbstractMaterial material) {
    return ( material.getX() - previousPointX() < 0 );
  }
 
  public void getItem(Item i) {
    // TODO : item種類に応じた挙動
    if ( jumpHeight == 10 ) { 
      jumpHeight = 20;
      isImmortal = true;
    } else {
      dashSpeed = 10;
    }
    i.toInvisible();
  }

  public boolean isImmortal() {
    return isImmortal;
  }

  public void toDeadable() {
    if ( dashSpeed == 10 ) {
      dashSpeed = 5;
    } else {
      jumpHeight = 10;
      isImmortal = false;
    }
  }
}
