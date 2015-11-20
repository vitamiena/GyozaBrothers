package Material;

import java.awt.*;
import Env.*;

// �����N���X
abstract public class AbstractCharacter extends AbstractMaterial {
  private boolean isAlive; // �����t���O
  private boolean isJumping; // �W�����v���Ă��邩�ǂ���
  private int jumpHeight;

  // �C���X�^���X�̐��� = �����̒a��
  public AbstractCharacter(int w, int h, int x, int y, Color c) {
    super(w, h, x, y, c);
    isAlive = true;
    isJumping = false;
    jumpHeight = 10;
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

  // �W�����v�̍���
  public void setjumpHeight( int y ) { jumpHeight = y; }
  public int getjumpHeight() { return jumpHeight; }

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
    moveDir.vertical -= jumpHeight;
    isJumping = true;
  }
  
  public void landing() {
    isJumping = false;
  };

}
