package Material;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.util.Random;
import Env.*;

public class RocketMan extends Enemy {
  public RocketMan ( int w, int h, int x, int y, Color c ) {
    super( w, h, x, y, c );
    Vector v = getMoveDir();
    v.horizontal = -10;
    v.vertical = -1;
    setMoveDir(v);
  }

  public void motion () {
      Vector v = getMoveDir();
      v.horizontal = -10;
      v.vertical = -1;
      setMoveDir(v);
  }
}
