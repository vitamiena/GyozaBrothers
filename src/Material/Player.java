package Material;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.*;
import Env.*;

public class Player extends AbstractCharacter {
  private KeyController keyController = KeyController.getInstance();
    
  // �R���X�g���N�^
  public Player(int w, int h, int x, int y) {
    super(w, h, x, y);
  }
 
  // �`��
  public void draw(Graphics g) {
    g.drawRect(getX(), getY(), getWidth(), getHeight());
  }
 
  public void draw(Graphics g, int tx) {
    g.drawRect(tx, getY(), getWidth(), getHeight());
  }
 
  public void move() {
    // �v���C���̈ړ�(�L�[����)
    Vector v = getMoveDir();
      
    // �W�����v�L�[�������ꂽ�Ƃ�
    if ( keyController.getUp() == KeyController.Key.Press ) {
      v.vertical -= 10;
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
    setMoveDir(v);
  }
  
  // ���n (�W�����v�L�[�̗L����)
  public void landing() {
    keyController.setUpRelease();
  }
  
  // �L�[�������ꂽ
  public void keyPressed(KeyEvent e) {
    keyController.pressed(e);
  }
  
  // �L�[�������ꂽ
  public void keyReleased(KeyEvent e) {
    keyController.released(e);
  }
  
  // Enemy�N���X�Ƃ̏Փ�
  public boolean collidWithEnemy(Enemy enemy) {
    Rectangle playerRec = new Rectangle(getX(), getY(), getWidth(), getHeight());
    Rectangle enemyRec = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        
    if ( playerRec.intersects(enemyRec) ) {
      return true;
    }
    return false;
  }
  
  public boolean enemyStamp(Enemy enemy) {
    int relativeY = Math.abs(enemy.getY()-getY()-enemy.getHeight());
    int relativeRightX = Math.abs(getRight()-enemy.getRight());
    int relativeLeftX = Math.abs(getLeft()-enemy.getLeft());
       
    if ( relativeY < 3 ) {
      if ( relativeRightX < getWidth() || relativeLeftX < getWidth() ) {
        return true;
      }
    }
    return false;
  }

  //**�ǉ�** Item�N���X�Ƃ̏Փ�
  public boolean colidWithItem(Item i) {
    Rectangle playerRec = new Rectangle(getX(), getY(), getWidth(), getHeight());
    Rectangle itemRec = new Rectangle(i.getX(), i.getY(), i.getWidth(), i.getHeight());
      
    if ( playerRec.intersects(itemRec) ) {
      return true;
    }
    
    return false;
  }
}
