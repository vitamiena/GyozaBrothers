package Material;

import java.awt.Graphics;
import java.awt.*;

// すべての物体クラスが継承すべき抽象クラス
abstract public class AbstractMaterial {
  private int width;  // 幅
  private int height;  // 高さ
  private int x;
  private int y;
  
  public AbstractMaterial(int w, int h, int tx, int ty) {
    width = w;
    height = h;
    x = tx;
    y = ty;
  }
  // 描画
  abstract public void draw(Graphics g);
    
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
}
