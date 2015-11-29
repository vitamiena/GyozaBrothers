package Material;

import java.awt.Graphics;
import java.awt.Color;
import Env.*;

public class DeathTrap extends Trap {
  public DeathTrap(int w, int h, int x, int y, Color c) {
    super(w, h, x, y, c);
  }
  
  public void motion(Player p) {
    p.dead();
  }
}