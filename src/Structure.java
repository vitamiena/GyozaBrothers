import java.awt.Graphics;
import java.awt.Color;

public class Structure extends AbstractMaterial {
	Color color;
	public Structure(int w, int h, int x, int y, Color c) {
		super(w, h, x, y);
		color = c;
	}
	
	@Override
	void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

}
