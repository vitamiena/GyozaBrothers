package Material;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;
import static java.awt.event.KeyEvent.*;
import java.awt.*;
import Env.*;

public class Player extends AbstractCharacter {
  private KeyController keyController = KeyController.getInstance();
  private int jumpHeight;
  private boolean isImmortal;
  // �R���X�g���N�^
  public Player(int w, int h, int x, int y) {
    super(w, h, x, y);
    jumpHeight = 10;
    isImmortal = false;
  }

  @Override
  public void reborn() {
    super.reborn();
    jumpHeight = 10;
    isImmortal = false;
  }

  // �`��
  public void draw(Graphics g) {
    g.drawRect(getX(), getY(), getWidth(), getHeight());
  }
 
  public void draw(Graphics g, int tx) {
    g.setColor(Color.GREEN);
    g.fillRect(tx, getY(), getWidth(), getHeight());
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
      v.horizontal = -5;
    } else if ( keyController.getRight() == KeyController.Key.Keep ) {
      v.horizontal = 5;
    } else {
      v.horizontal = 0;
    }
    
    if ( ( ! isJumping() ) && keyController.getUp() == KeyController.Key.Keep ) {
      v.vertical -= jumpHeight;
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
  
  // AbstractMaterial�N���X�Ƃ̏Փ�
  public boolean collidWithMaterial(AbstractMaterial material) {
    Rectangle playerRec = getRectangle();
    Rectangle materialRec = material.getRectangle();
        
    return playerRec.intersects(materialRec);
  }

  // ���O�̕����̔��f
  public boolean onCharacter(AbstractCharacter character) {
    if ( previousPointY() - character.previousPointY() < 0 ) { 
      return true;
    }
    return false;
  }
  
  public boolean enemyStamp(Enemy enemy) {
    int relativeY = enemy.getY()-getY();
    int relativeRightX = Math.abs(getRight()-enemy.getRight());
    int relativeLeftX = Math.abs(getLeft()-enemy.getLeft());
       
    if ( relativeY < getHeight() ) {
      if ( relativeRightX < getWidth() || relativeLeftX < getWidth() ) {
        return true;
      }
    }
    return false;
  }

  public void getItem(Item i) {
    // TODO : item��ނɉ���������
    jumpHeight = 20;
    i.toInvisible();
  }

  public boolean isImmortal() {
    return isImmortal;
  }

  public void toDeadable() {
    isImmortal = false;
  }
}
