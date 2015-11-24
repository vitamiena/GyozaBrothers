package Material;

import java.awt.Graphics;
import java.awt.Color;
import Env.*;

public class RocketTrap extends Trap {
  public RocketTrap(int w, int h, int x, int y, Color c) {
    super(w, h, x, y, c);
  }
  
  public void motion(Player p) {
    Vector v = p.getMoveDir();
    v.vertical = -50;
    p.setMoveDir(v);
  }
}