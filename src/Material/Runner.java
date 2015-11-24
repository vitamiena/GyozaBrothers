package Material;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import Env.*;

public class Runner extends Enemy {
  private int speed;
  public Runner ( int w, int h, int x, int y, Color c ) {
    super( w, h, x, y, c );
    Vector v = getMoveDir();
    v.horizontal = speed;
    setMoveDir(v);
  }

  public void motion () {
    Vector v = getMoveDir();
    v.horizontal = speed;
    setMoveDir(v);
  }
}
