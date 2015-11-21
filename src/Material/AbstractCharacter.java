package Material;

import java.awt.*;
import Env.*;

// 生物クラス
abstract public class AbstractCharacter extends AbstractMaterial {
  private boolean isAlive; // 生存フラグ
  private boolean isJumping; // ジャンプしているかどうか
  private int jumpHeight;

  // インスタンスの生成 = 生命の誕生
  public AbstractCharacter(int w, int h, int x, int y, Color c) {
    super(w, h, x, y, c);
    isAlive = true;
    isJumping = false;
    jumpHeight = 10;
  }

  // 死亡
  public void dead() {
    // ジャンプの高さをデフォルトに戻す
    setjumpHeight(10);
    isAlive = false;
  }
  
  // 復活
  public void reborn() {
    isAlive = true;
  }

  // 生きているかどうか
  public boolean isAlive() {
    return isAlive;
  }
  
  public boolean isJumping() {
    return isJumping;
  }

  // ゲッター・セッター
  public void setMoveDir(Vector v) {
    moveDir = v;
  }

  // 移動方向の取得
  public Vector getMoveDir() {
    return moveDir;
  }

  // ジャンプの高さ
  public void setjumpHeight( int y ) { jumpHeight = y; }
  public int getjumpHeight() { return jumpHeight; }

  // 移動
  public void move(String dir) {
    /*
       if ( dir == GameMaster.UP ) {
       moveDir.vertical -= 10;
       } else if ( dir == GameMaster.LEFT ) {
       moveDir.horizontal = -5;
       } else if ( dir == GameMaster.RIGHT ) {
       moveDir.horizontal = 5; 			
       } else {
       }
       */
  }

  public void jump() {
    moveDir.vertical -= jumpHeight;
    isJumping = true;
  }
  
  public void landing() {
    isJumping = false;
  };

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
}
