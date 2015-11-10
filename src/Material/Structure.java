package Material;

import java.awt.Graphics;
import java.awt.Color;

public class Structure extends AbstractMaterial {
	Color color;
	public Structure(int w, int h, int x, int y, Color c) {
		super(w, h, x, y);
		color = c;
	}
	
  // �`��
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color); // �����̐F
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
  
  public void draw(Graphics g, int tx) {
		// TODO Auto-generated method stub
		g.setColor(color); // �����̐F
		g.fillRect(tx, getY(), getWidth(), getHeight());
	}

  public void setX(int t) throws MaterialsException { 
    throw new StructuresChangedException();
  }
  
  public void setY(int t) throws MaterialsException { 
    throw new StructuresChangedException();
  }
  
  public void setWidth(int t) throws MaterialsException { 
    throw new StructuresChangedException();
  }
  
  public void setHeight(int t) throws MaterialsException { 
    throw new StructuresChangedException();
  }
}
