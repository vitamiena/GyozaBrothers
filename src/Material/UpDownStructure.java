package Material;

import java.awt.Graphics;
import java.awt.Color;
import Env.*;

public class UpDownStructure extends Structure {
  private int moveHeight; //ˆÚ“®‚·‚é‹——£
  private int speed = 1;
  private int heightDiff = 0; // ˆÚ“®‚µ‚½‹——£
  private boolean up = false;

  public UpDownStructure(int w, int h, int x, int y, Color c, int mh) {
    super(w, h, x, y, c);
    moveHeight = mh;
  }
  
  public UpDownStructure(int w, int h, int x, int y, Color c, int mh, int s) {
    super(w, h, x, y, c);
    moveHeight = mh;
    speed = s;
  }
  
  public void motion() {
    Vector v = getMoveDir();
    if ( up ) {
      if ( heightDiff > speed ) {
        v.vertical = -speed;
        heightDiff -= speed;
      } else {
        v.vertical = -heightDiff;
        heightDiff = 0;
        up = false;
      }
    } else {
      if ( ( moveHeight - heightDiff ) > speed ) {
        v.vertical = speed;
        heightDiff += speed;
      } else {
        v.vertical = ( moveHeight - heightDiff );
        heightDiff = moveHeight;
        up = true;
      }
    }
  }
}