package Material;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import Env.*;

public class Enemy extends AbstractCharacter {
  private Color color;

  public Enemy ( int w, int h, int x, int y, Color c ) {
    super( w, h, x, y );
    Vector v = getMoveDir();
    v.horizontal = -2;
    color = c;
    setMoveDir(v);
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect(getX(), getY(), getWidth(), getHeight());
  }

  public void draw(Graphics g, int tx) {
    // TODO Auto-generated method stub
    g.setColor(color);
    g.fillRect(tx, getY(), getWidth(), getHeight());
  }
}
