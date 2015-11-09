import java.awt.*;

// �����N���X
abstract class AbstractCharacter extends AbstractMaterial {
	protected boolean isAlive; // �����t���O
	protected Vector moveDir; 
	
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
		Rectangle playerRec = new Rectangle(x, y, width, height);
		Rectangle strRec = new Rectangle(s.x, s.y, s.width, s.height);
		
		if ( playerRec.intersects(strRec) ) {
			return true;
		}
		
		return false;
	}
}