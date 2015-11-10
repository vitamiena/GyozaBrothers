package Material;

import java.awt.Graphics;
import java.awt.Color;

public class Item extends AbstractMaterial {
  Color color;
  private boolean isVisible;

  public Item(int w, int h, int x, int y, Color c) {
    super(w, h, x, y);
    color = c;
    isVisible = true;
  }

  // •`‰æ
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect(getX(), getY(), getWidth(), getHeight()); 
  }
	
	public void draw(Graphics g, int tx) {
		g.setColor(color);
		g.fillRect(tx, getY(), getWidth(), getHeight());
	}

  public boolean isVisible() {
    return isVisible;
  }

  public void toInvisible () {
    isVisible = false;
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

