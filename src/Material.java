import java.awt.Graphics;

// すべての物体クラスが継承すべき抽象クラス
abstract class AbstractMaterial {
	protected int width;  // 幅
	protected int height;  // 高さ
	
	public AbstractMaterial(int w, int h) {
		width = w;
		height = h;
	}
	abstract void draw(Graphics g, int x, int y);
}
