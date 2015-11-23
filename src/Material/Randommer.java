package Material;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.util.Random;
import Env.*;

public class Randommer extends Enemy {
  private int time;
  public Randommer ( int w, int h, int x, int y, Color c ) {
    super( w, h, x, y, c );
    time = 0;
    Vector v = getMoveDir();
    Random rnd = new Random();
    v.horizontal = rnd.nextInt(20)-10;
    v.vertical = rnd.nextInt(20)-10;
    setMoveDir(v);
  }

  public void motion () {
    if ( time > 500 ) {
      Vector v = getMoveDir();
      Random rnd = new Random();
      v.horizontal = rnd.nextInt(40)-20;
      v.vertical = rnd.nextInt(40)-20;
      time = 0;
    }
    time += 10;
  }
}
