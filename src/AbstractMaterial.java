import java.awt.Graphics;

// ���ׂĂ̕��̃N���X���p�����ׂ����ۃN���X
abstract class AbstractMaterial {
	protected int width;  // ��
	protected int height;  // ����
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
