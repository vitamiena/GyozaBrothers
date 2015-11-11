package Material;

import java.awt.Graphics;

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

  public void setX(int t) throws MaterialsException { x = t; }
  public void setY(int t) throws MaterialsException { y = t; }
  public void setWidth(int t) throws MaterialsException { width = t; }
  public void setHeight(int t) throws MaterialsException { height = t; }
}