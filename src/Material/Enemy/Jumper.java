package Material.Enemy;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import Env.*;
import Material.*;

public class Jumper extends Enemy {
  private int speed = -2;
  private int jumpHight = 20;

  public Jumper ( int w, int h, int x, int y, Color c ) {
    super( w, h, x, y, c );
    Vector v = getMoveDir();
    v.horizontal = speed;
    setMoveDir(v);
    setjumpHeight(jumpHight);
    jump();
  }

  public void motion () {
    Vector v = getMoveDir();
    v.horizontal = speed;
    setMoveDir(v);

    if ( !isJumping() ) {
      jump();
    }
  }
}
