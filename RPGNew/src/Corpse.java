import java.awt.Color;

import jwbgl.Posn;
import jwbgl.Surface;

public class Corpse extends NonBlockingObject {
  public Corpse(RPG game, Posn posn, String imgPath) {
    super(game, posn);
    surface = new Surface(imgPath).scale2x();
    surface.setColorkey(Color.WHITE);
    setyOffset(-32); // trial and error...
  }
  public boolean isCorpse() {
    return true;
  }

}
