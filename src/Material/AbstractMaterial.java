package Material;

import java.awt.Graphics;
import java.awt.*;
import Env.*;

// すべての物体クラスが継承すべき抽象クラス
abstract public class AbstractMaterial {
  private int width;  // 幅
  private int height;  // 高さ
  private int x;
  private int y;
  public Vector moveDir;  // 移動方向
  private Color color;
  
  public AbstractMaterial(int w, int h, int tx, int ty, Color c) {
    width = w;
    height = h;
    x = tx;
    y = ty;
    moveDir = new Vector(0,0);
    color = c;
  }
  // 描画
  public void draw(Graphics g, int tx) {
    g.setColor(color);
    g.fillRect(tx, getY(), getWidth(), getHeight());
  }
 
    
  public int getX() { return x; }
  public int getY() { return y; }
  public int getWidth() { return width; }
  public int getHeight() { return height; }
  public int getLeft() { return x; }
  public int getRight() { return x+width; }
  public int getTop() { return y; }
  public int getBottom() { return y + height; }
  
  public void setX(int t) { x = t; }
  public void setY(int t) { y = t; }
  public void setWidth(int t) { width = t; }
  public void setHeight(int t) { height = t; }
 
  public Rectangle getRectangle() {
    return new Rectangle(getX(), getY(), getWidth(), getHeight());
  }

  // 直前の座標の取得
  public int previousPointX() {
    return getX()-moveDir.horizontal;
  }
  public int previousPointY() {
    return getY()-moveDir.vertical;
  }

  // 他のAbstractMaterialクラスとの衝突
  public boolean collidWithMaterial(AbstractMaterial material) {
    Rectangle playerRec = getRectangle();
    Rectangle materialRec = material.getRectangle();
    return playerRec.intersects(materialRec);
  }
}
