// �����N���X
abstract class AbstractCharacter extends AbstractMaterial {
	protected boolean isAlive; // �����t���O
	protected Vector moveDir; 
	
	// �C���X�^���X�̐��� = �����̒a��
	public AbstractCharacter(int w, int h) {		
		super(w, h);
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
		if ( dir == GameMaster.UP ) {
			moveDir.vertical -= 10;
		} else if ( dir == GameMaster.LEFT ) {
			moveDir.horizontal = -5;
		} else if ( dir == GameMaster.RIGHT ) {
			moveDir.horizontal = 5; 			
		} else {
		}
	}
}