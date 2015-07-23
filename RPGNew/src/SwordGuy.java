import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import jwbgl.*;
/* Warrior-type player unit class.  Sword/shield.*/
public class SwordGuy extends HumanUnit {
  private int minDamage, maxDamage, bashDamage;
  private static String[] activities = {
    "walking", "standing", "attacking", "blocking_1", "blocking_2", "blocking_3",
    "bashing", "slashing_1", "slashing_2", "slashing_3", "falling"};

  public SwordGuy(RPG game, String name, Posn posn, Player player, HashMap<Color, Color> paletteSwaps) {
    /*public HumanUnit(RPG game, String name, String animationName,
        String[] activities, HashMap<Color, Color> paletteSwaps, Posn posn, Player player) {*/
    super(game, name, activities, paletteSwaps, posn, player);
    currentHP = maxHP = 200;
    currentEP = maxEP = 100;
    minDamage = 6;
    maxDamage = 12;
    bashDamage = 20;
    addAccessory(new Sword(game, this, "sword"));
    addAccessory(new Shield(game, this, "Shield of Suck"));
  }
  public SwordGuy(RPG game, String name, Posn posn, Player player) {
    this(game, name, posn, player, new HashMap<Color, Color>());
  }

  public void doAttackHit(Unit u) {
    int dmg = game.getRNG().nextInt(maxDamage - minDamage) + minDamage + 1;
    // soundFX
    u.takeHit(this, dmg);
    playHitSound();
  }
  public void doBashHit(Unit u) {
    int dx = u.getX() - getX();
    int dy = u.getY() - getY();
    int x = u.getX()+dx;
    int y = u.getY()+dy;
    Tile t = game.getFloor().getTile(new Posn(x,y));
    if (!game.isObstacle(new Posn(x,y))) {
      u.move(dx, dy);
    }
    u.takeBashHit(this, bashDamage);
    playHitSound();
    //System.out.println(this + " hit unit " + u);
  }
  
  @Override
  public void draw(Graphics g) {
    super.draw(g);
  }

}
