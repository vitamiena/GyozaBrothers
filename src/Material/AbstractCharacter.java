package Material;

import java.awt.*;
import Env.*;

// �����N���X
abstract class AbstractCharacter extends AbstractMaterial {
	private boolean isAlive; // �����t���O
	private Vector moveDir; 
	
	// �C���X�^���X�̐��� = �����̒a��
	public AbstractCharacter(int w, int h, int x, int y) {		
		super(w, h, x, y);
		isAlive = true;
		moveDir = new Vector(0,0);
	} 
	
	// ���S
	public void Dead() {
		isAlive = false;
	}
	
  public boolean isAlive() {
    return isAlive;
  }
  
	// �Q�b�^�[�E�Z�b�^�[
	public void setMoveDir(Vector v) {
		moveDir = v;
	}
	public Vector getMoveDir() {
		return moveDir;
	}
	
	// �ړ�
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
		Rectangle playerRec = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle strRec = new Rectangle(s.getX(), s.getY(), s.getWidth(), s.getHeight());
		
		if ( playerRec.intersects(strRec) ) {
			return true;
		}
		
		return false;
	}
}