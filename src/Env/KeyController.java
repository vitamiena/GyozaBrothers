package Env;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class KeyController {
  public enum Key { Press, Keep, Release } // キーの状態 (押された、押され続けている、離されている)
  private static KeyController singleton = new KeyController();
  private Key right = Key.Release; // 右キー
  private Key left = Key.Release;  // 左キー
  private Key up = Key.Release;    // ジャンプキー

  private KeyController() {
  }

  public static KeyController getInstance() {
    return singleton;
  }	

  // キーが押される
  public void pressed(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case VK_SPACE: // ジャンプ 
        if ( up == Key.Release ) { // キーが離されている状態のとき (二段ジャンプの禁止)
          up = Key.Press; 
        }
        break;
      case VK_LEFT: // 左
        if ( left == Key.Release ) {
          left = Key.Press;
        }
        break;
      case VK_RIGHT: // 右
        if ( right == Key.Release ) {
          right = Key.Press;
        }
        break;
      default: ; break;			
    }
  }

  // キーが離されたとき (ジャンプは、着地した時にキーが離された判定とする)
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
