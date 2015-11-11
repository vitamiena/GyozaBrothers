package Env;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class KeyController {
  public enum Key { Press, Keep, Release } // �L�[�̏�� (�����ꂽ�A�����ꑱ���Ă���A������Ă���)
  private static KeyController singleton = new KeyController();
  private Key right = Key.Release; // �E�L�[
  private Key left = Key.Release;  // ���L�[
  private Key up = Key.Release;    // �W�����v�L�[

  private KeyController() {
  }

  public static KeyController getInstance() {
    return singleton;
  }	

  // �L�[���������
  public void pressed(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case VK_SPACE: // �W�����v 
        if ( up == Key.Release ) { // �L�[��������Ă����Ԃ̂Ƃ� (��i�W�����v�̋֎~)
          up = Key.Press; 
        }
        break;
      case VK_LEFT: // ��
        if ( left == Key.Release ) {
          left = Key.Press;
        }
        break;
      case VK_RIGHT: // �E
        if ( right == Key.Release ) {
          right = Key.Press;
        }
        break;
      default: ; break;			
    }
  }

  // �L�[�������ꂽ�Ƃ� (�W�����v�́A���n�������ɃL�[�������ꂽ����Ƃ���)
  public void released(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case VK_LEFT: left = Key.Release; break;
      case VK_RIGHT: right = Key.Release; break;
      default: ; break;			
    }		
  }

  public Key getRight() {
    return right;
  }
  public Key getLeft() {
    return left;
  }
  public Key getUp() {
    return up;
  }
  public void setUpKeep() {
    up = Key.Keep;
  } 	
  public void setUpRelease() {
    up = Key.Release;
  }
  public void setRightKeep() {
    right = Key.Keep;
  }
  public void setLeftKeep() {
    left = Key.Keep;
  }
}
