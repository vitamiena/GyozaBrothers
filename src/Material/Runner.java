package Material;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import Env.*;

public class Runner extends Enemy {
  public Runner ( int w, int h, int x, int y, Color c ) {
    super( w, h, x, y, c );
    Vector v = getMoveDir();
    v.horizontal = -2;
    setMoveDir(v);
  }

  public void motion () {
    Vector v = getMoveDir();
    v.horizontal = -2;
    setMoveDir(v);
  }
}
