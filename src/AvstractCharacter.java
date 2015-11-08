import java.awt.*;

// 生物クラス
abstract class AbstractCharacter extends AbstractMaterial {
	protected boolean isAlive; // 生存フラグ
	protected Vector moveDir; 
	
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
	
	// ゲッター・セッター
	public void setMoveDir(Vector v) {
		moveDir = v;
	}
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
	
	public boolean colidWithStructure(Structure s) {
		Rectangle playerRec = new Rectangle(x, y, width, height);
		Rectangle strRec = new Rectangle(s.x, s.y, s.width, s.height);
		
		if ( playerRec.intersects(strRec) ) {
			return true;
		}
		
		return false;
	}
}