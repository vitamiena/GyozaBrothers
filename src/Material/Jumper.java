package Material;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import Env.*;

public class Jumper extends Enemy {
  public Jumper ( int w, int h, int x, int y, Color c ) {
    super( w, h, x, y, c );
    Vector v = getMoveDir();
    v.horizontal = -2;
    setMoveDir(v);
    setjumpHeight(20);
    jump();
  }

  public void motion () {
    Vector v = getMoveDir();
    v.horizontal = -2;
    setMoveDir(v);

    if ( !isJumping() ) {
      jump();
    }
  }
}
