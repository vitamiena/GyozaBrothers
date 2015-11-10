﻿package Material;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import Env.*;

public class Player extends AbstractCharacter {
	private KeyController keyController = KeyController.getInstance();
	
  // コンストラクタ
  public Player(int w, int h, int x, int y) {
		super(w, h, x, y);
	}
	
  // 描画
	public void draw(Graphics g) {
		g.drawRect(getX(), getY(), getWidth(), getHeight());
	}
	
  // プレイヤの移動 (キー入力)
	public void move() {
    Vector v = getMoveDir();
    
    // ジャンプキーが押されたとき
		if ( keyController.getUp() == KeyController.Key.Press ) {
			v.vertical -= 10;
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
			v.horizontal = -5;
		} else if ( keyController.getRight() == KeyController.Key.Keep ) {
			v.horizontal = 5;
		} else {
			v.horizontal = 0;
		}
    setMoveDir(v);
	}
	
  // 着地 (ジャンプキーの有効化)
	public void landing() {
		keyController.setUpRelease();
	}
  
  // キーが押された
  public void keyPressed(KeyEvent e) {
    keyController.pressed(e);
  }
  
  // キーが離された
  public void keyReleased(KeyEvent e) {
    keyController.released(e);
  }
}
