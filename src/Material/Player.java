package Material;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.applet.AudioClip;
import static java.awt.event.KeyEvent.*;
import java.awt.*;
import Game.*;
import Env.*;

public class Player extends AbstractCharacter {
  private KeyController keyController = KeyController.getInstance();
  private int dashSpeed;
  private boolean isImmortal;
  private GameMaster gm;

  // コンストラクタ
  public Player(int w, int h, int x, int y, Color c) {
    super(w, h, x, y, c);
    

    setjumpHeight(10);
    dashSpeed = 5;
    isImmortal = false;
  }
  
   public Player(int w, int h, int x, int y, Color c, GameMaster g) {
    this(w, h, x, y, c);
    gm = g;
  }

  @Override
  public void reborn() {
    super.reborn();
    setColor(Color.GREEN);
    setjumpHeight(10);
    dashSpeed = 5;
    isImmortal = false;
  }

  // 描画
  public void draw(Graphics g) {
    g.drawRect(getX(), getY(), getWidth(), getHeight());
  }
 
  public void move() {
    // プレイヤの移動(キー入力)
    Vector v = getMoveDir();
    
    // ジャンプキーが押されたとき
    if ( keyController.getUp() == KeyController.Key.Press ) {
      keyController.setUpKeep();
    }

    // 左右キーが押されたとき
    if ( keyController.getLeft() == KeyController.Key.Press ) {
      keyController.setLeftKeep();
    } else if ( keyController.getRight() == KeyController.Key.Press ) {
      keyController.setRightKeep();
    }     
    
    // 左右への移動方向の設定
    if ( keyController.getLeft() == KeyController.Key.Keep ) {
      v.horizontal = -dashSpeed;
    } else if ( keyController.getRight() == KeyController.Key.Keep ) {
      v.horizontal = dashSpeed;
    } else {
      v.horizontal = 0;
    }
    
    if ( ( ! isJumping() ) && keyController.getUp() == KeyController.Key.Keep ) {
      gm.se = gm.getAudioClip(gm.getDocumentBase(), "Sound\\jump.wav");
      jump( gm.se );
    }
    
    setMoveDir(v);
  }
  
  // キーが押された
  public void keyPressed(KeyEvent e) {
    keyController.pressed(e);
  }
  
  // キーが離された
  public void keyReleased(KeyEvent e) {
    keyController.released(e);
  }
 
  public void getItem(Item i) {
    // TODO : item種類に応じた挙動
    if ( getjumpHeight() == 10 ) { 
      setColor(Color.GRAY);
      setjumpHeight(20);
      isImmortal = true;
    } else {
      setColor(Color.BLACK);
      dashSpeed = 10;
    }
    i.toInvisible();
  }

  public boolean isImmortal() {
    return isImmortal;
  }

  public void toDeadable() {
    if ( dashSpeed == 10 ) {
      setColor(Color.GRAY);
      dashSpeed = 5;
    } else {
      setColor(Color.GREEN);
      setjumpHeight(10);
      isImmortal = false;
    }
  }
}
