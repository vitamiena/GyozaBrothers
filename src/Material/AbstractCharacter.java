package Material;

import java.awt.*;
import Env.*;

// 生物クラス
abstract public class AbstractCharacter extends AbstractMaterial {
  private boolean isAlive; // 生存フラグ
  private boolean isJumping; // ジャンプしているかどうか
  private Vector moveDir;  // 移動方向

  // インスタンスの生成 = 生命の誕生
  public AbstractCharacter(int w, int h, int x, int y) {
    super(w, h, x, y);
    isAlive = true;
    moveDir = new Vector(0,0);
    isJumping = false;
  }

  // 死亡
  public void dead() {
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
    isJumping = true;    
  }
  
  public void landing() {
    isJumping = false;  
  };

  // Structureクラスとの衝突
  public boolean collidWithStructure(Structure s) {
    Rectangle playerRec = getRectangle();
    Rectangle strRec = s.getRectangle();

    return playerRec.intersects(strRec);
  }
}
