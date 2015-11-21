package Material;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;
import static java.awt.event.KeyEvent.*;
import java.awt.*;
import Env.*;

public class Player extends AbstractCharacter {
  private KeyController keyController = KeyController.getInstance();
  private int dashSpeed;
  private boolean isImmortal;
  
  // �R���X�g���N�^
  public Player(int w, int h, int x, int y, Color c) {
    super(w, h, x, y, c);

    setjumpHeight(10);
    dashSpeed = 5;
    isImmortal = false;
  }

  @Override
  public void reborn() {
    super.reborn();

    setjumpHeight(10);
    dashSpeed = 5;
    isImmortal = false;
  }

  // �`��
  public void draw(Graphics g) {
    g.drawRect(getX(), getY(), getWidth(), getHeight());
  }
 
  public void move() {
    // �v���C���̈ړ�(�L�[����)
    Vector v = getMoveDir();
    
    // �W�����v�L�[�������ꂽ�Ƃ�
    if ( keyController.getUp() == KeyController.Key.Press ) {
      keyController.setUpKeep();
    }

    // ���E�L�[�������ꂽ�Ƃ�
    if ( keyController.getLeft() == KeyController.Key.Press ) {
      keyController.setLeftKeep();
    } else if ( keyController.getRight() == KeyController.Key.Press ) {
      keyController.setRightKeep();
    }     
    
    // ���E�ւ̈ړ������̐ݒ�
    if ( keyController.getLeft() == KeyController.Key.Keep ) {
      v.horizontal = -dashSpeed;
    } else if ( keyController.getRight() == KeyController.Key.Keep ) {
      v.horizontal = dashSpeed;
    } else {
      v.horizontal = 0;
    }
    
    if ( ( ! isJumping() ) && keyController.getUp() == KeyController.Key.Keep ) {
      jump();
    }
    
    setMoveDir(v);
  }
  
  // �L�[�������ꂽ
  public void keyPressed(KeyEvent e) {
    keyController.pressed(e);
  }
  
  // �L�[�������ꂽ
  public void keyReleased(KeyEvent e) {
    keyController.released(e);
  }
 
  public void getItem(Item i) {
    // TODO : item��ނɉ���������
    if ( getjumpHeight() == 10 ) { 
      setjumpHeight(20);
      isImmortal = true;
    } else {
      dashSpeed = 10;
    }
    i.toInvisible();
  }

  public boolean isImmortal() {
    return isImmortal;
  }

  public void toDeadable() {
    if ( dashSpeed == 10 ) {
      dashSpeed = 5;
    } else {
      setjumpHeight(10);
      isImmortal = false;
    }
  }
}
