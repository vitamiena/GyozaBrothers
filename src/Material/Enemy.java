package Material;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import Env.*;

public class Enemy extends AbstractCharacter {

  public Enemy ( int w, int h, int x, int y ) {
    super( w, h, x, y );
    Vector v = getMoveDir();
    v.horizontal = -1;
    setMoveDir(v);
  }

  public void draw(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(getX(), getY(), getWidth(), getHeight());
    g.setColor(Color.orange);
  }

}
