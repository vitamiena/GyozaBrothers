import java.awt.Graphics;

// ���ׂĂ̕��̃N���X���p�����ׂ����ۃN���X
abstract class AbstractMaterial {
	protected int width;  // ��
	protected int height;  // ����
	
	public AbstractMaterial(int w, int h) {
		width = w;
		height = h;
	}
	abstract void draw(Graphics g, int x, int y);
}
