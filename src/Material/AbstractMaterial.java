package Material;

import java.awt.Graphics;

// ‚·‚×‚Ä‚Ì•¨‘ÌƒNƒ‰ƒX‚ªŒp³‚·‚×‚«’ŠÛƒNƒ‰ƒX
abstract public class AbstractMaterial {
  private int width;  // •
  private int height;  // ‚‚³
  private int x;
  private int y;
  
  public AbstractMaterial(int w, int h, int tx, int ty) {
    width = w;
    height = h;
    x = tx;
    y = ty;
  }
  // •`‰æ
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
}