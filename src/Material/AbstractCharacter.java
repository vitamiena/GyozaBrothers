package Material;

import java.applet.AudioClip;
import java.awt.*;
import Env.*;

// �����N���X
abstract public class AbstractCharacter extends AbstractMaterial {
  private boolean isAlive; // �����t���O
  private boolean isJumping; // �W�����v���Ă��邩�ǂ���
  private int jumpHeight;
  //public AudioClip JumpSE;

  // �C���X�^���X�̐��� = �����̒a��
  public AbstractCharacter(int w, int h, int x, int y, Color c) {
    super(w, h, x, y, c);
    isAlive = true;
    isJumping = false;
    jumpHeight = 10;
  }

  // ���S
  public void dead() {
    // �W�����v�̍������f�t�H���g�ɖ߂�
    setjumpHeight(10);
    isAlive = false;
  }
  
  public void dead( AudioClip se ){
    se.play();
    dead();
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
  	//AudioClip JumpSE = getAudioClip(getDocumentBase(), "jump.wav");
  	//JumpSE.play();
    Vector v = getMoveDir();
    v.vertical -= jumpHeight;
    setMoveDir(v);
    isJumping = true;
  }
  
  public void jump( AudioClip se ){
    
    se.play();
    jump();
    
  }
  
  public void landing() {
    isJumping = false;
  };

  // ���O�̕����̔��f(AbstractMaterial)
  public boolean onMaterial(AbstractMaterial material) {
    return ( material.previousTop() - previousBottom() >= 0 );
  }

  public boolean underMaterial(AbstractMaterial material) {
    return ( material.previousBottom() - previousTop() <= 0 );
  }

  public boolean leftMaterial(AbstractMaterial material) {
    return ( material.previousLeft() - previousRight() >= 0 );
  }
  
  public boolean rightMaterial(AbstractMaterial material) {
    return ( material.previousRight() - previousLeft() <= 0 );
  }
}
