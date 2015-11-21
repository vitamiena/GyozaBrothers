package Material;

import java.awt.Graphics;
import java.awt.Color;

public class Life extends AbstractMaterial {
  private boolean isVisible;

  public Life(int w, int h, int x, int y, Color c) {
    super(w, h, x, y, c);
    isVisible = true;
  }

  public boolean isVisible() {
    return isVisible;
  }

  public void toInvisible () {
    isVisible = false;
  }

  public void reset () {
    isVisible = true;
  }

}

