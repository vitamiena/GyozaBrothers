package Material;

import java.awt.*;
import Env.*;

// 生物クラス
abstract class AbstractCharacter extends AbstractMaterial {
	private boolean isAlive; // 生存フラグ
	private Vector moveDir;  // 移動方向
	
	// インスタンスの生成 = 生命の誕生
	public AbstractCharacter(int w, int h, int x, int y) {		
		super(w, h, x, y);
		isAlive = true;
		moveDir = new Vector(0,0);
	} 
	
	// 死亡
	public void Dead() {
		isAlive = false;
	}
	
  // 生きているかどうか
  public boolean isAlive() {
    return isAlive;
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
	
  // Structureクラスとの衝突
	public boolean colidWithStructure(Structure s) {
		Rectangle playerRec = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle strRec = new Rectangle(s.getX(), s.getY(), s.getWidth(), s.getHeight());
		
		if ( playerRec.intersects(strRec) ) {
			return true;
		}
		
		return false;
	}
}