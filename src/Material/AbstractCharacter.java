package Material;

import java.awt.*;
import Env.*;

// �����N���X
abstract public class AbstractCharacter extends AbstractMaterial {
  private boolean isAlive; // �����t���O
  private boolean isJumping; // �W�����v���Ă��邩�ǂ���
  private Vector moveDir;  // �ړ�����

  // �C���X�^���X�̐��� = �����̒a��
  public AbstractCharacter(int w, int h, int x, int y) {
    super(w, h, x, y);
    isAlive = true;
    moveDir = new Vector(0,0);
    isJumping = false;
  }

  // ���S
  public void dead() {
    isAlive = false;
  }
  
  // ����
  public void reborn() {
    isAlive = true;
  }

  // �����Ă��邩�ǂ���
  public boolean isAlive() {
    return isAlive;    
  }
  
  public boolean isJumping() {
    return isJumping;
  }

  // �Q�b�^�[�E�Z�b�^�[
  public void setMoveDir(Vector v) {
    moveDir = v;
  }

  // �ړ������̎擾
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
  
  public void jump() {
    isJumping = true;    
  }
  
  public void landing() {
    isJumping = false;  
  };

  // Structure�N���X�Ƃ̏Փ�
  public boolean collidWithStructure(Structure s) {
    Rectangle playerRec = getRectangle();
    Rectangle strRec = s.getRectangle();

    return playerRec.intersects(strRec);
  }
}
