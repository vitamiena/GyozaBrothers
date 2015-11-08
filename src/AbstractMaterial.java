import java.awt.Graphics;

// すべての物体クラスが継承すべき抽象クラス
abstract class AbstractMaterial {
	protected int width;  // 幅
	protected int height;  // 高さ
	protected int x;
	protected int y;
	public AbstractMaterial(int w, int h, int tx, int ty) {
		width = w;
		height = h;
		x = tx;
		y = ty;
	}
	abstract void draw(Graphics g);
}
