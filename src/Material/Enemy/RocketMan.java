package Material.Enemy;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.util.Random;
import Env.*;
import Material.*;

public class RocketMan extends Enemy {
  private int speed = -10;
  public RocketMan ( int w, int h, int x, int y, Color c ) {
    super( w, h, x, y, c );
    Vector v = getMoveDir();
    v.horizontal = speed;
    v.vertical = -1;
    setMoveDir(v);
  }

  public void motion () {
      Vector v = getMoveDir();
      v.horizontal = speed;
      v.vertical = -1;
      setMoveDir(v);
  }
}
