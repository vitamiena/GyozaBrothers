package Material;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.util.Random;
import Env.*;

public class Randommer extends Enemy {
  private int time;
  private int threadTime;
  public Randommer ( int w, int h, int x, int y, Color c, int sleepTime ) {
    super( w, h, x, y, c );
    threadTime = sleepTime;
    time = 0;
  }

  public void motion () {
    if ( time > 500 ) {
      Vector v = getMoveDir();
      Random rnd = new Random();
      v.horizontal = rnd.nextInt(40)-20;
      v.vertical = rnd.nextInt(40)-20;
      setMoveDir(v);
      time = 0;
    }
    time += threadTime;
  }
}
