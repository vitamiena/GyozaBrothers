package Material;

import java.awt.Graphics;

// ���ׂĂ̕��̃N���X���p�����ׂ����ۃN���X
abstract class AbstractMaterial {
	private int width;  // ��
	private int height;  // ����
	private int x;
	private int y;
	public AbstractMaterial(int w, int h, int tx, int ty) {
		width = w;
		height = h;
		x = tx;
		y = ty;
	}
	abstract public void draw(Graphics g);
  
  public int getX() { return x; }
  public int getY() { return y; }
  public int getWidth() { return width; }
  public int getHeight() { return height; }
  public void setX(int t) throws MaterialsException { x = t; }
  public void setY(int t) throws MaterialsException { y = t; }
  public void setWidth(int t) throws MaterialsException { width = t; }
  public void setHeight(int t) throws MaterialsException { height = t; }
}