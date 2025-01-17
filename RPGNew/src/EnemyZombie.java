import java.util.Random;

import jwbgl.*;
/* A basic enemy unit for testing! */

public class EnemyZombie extends ZombieUnit {
  private int minDamage, maxDamage;
  private double slowMoveSpeed, fastMoveSpeed, attackChance;
  private int visionRadius, smellRadius;
  private static String[] activities = {"walking", "standing", "attacking", "stunned_short", "falling"};
  public EnemyZombie(RPG game, String name, Posn posn, Player player) {
    super(game, name, activities, posn, player);
    currentHP = maxHP = 45;
    currentEP = maxEP = 30;
    minDamage = 2;
    maxDamage = 4;
    //minDamage = maxDamage = 0;
    slowMoveSpeed = 0.3;
    fastMoveSpeed = 0.7;
    attackChance = 0.75;
    visionRadius = 6;
    smellRadius = 12;
  }

  public void nextActivity() {
    super.nextActivity();
    Random RNG = game.getRNG();
    Unit tu = getNextTargetUnit();
    if (currentActivity.equals("standing")) {
      for (Unit u: game.getUnits()) {
        if (isHostile(u)) {
          if (game.distance2(this,u) <= smellRadius) {
            /* This should be where the "smell" sound is played, but we're losing the target
             * due to endAttack() [maybe]. Work on this later */
            if (tu == null) {
              setNextTargetUnit(u);
              tu = u;
              //System.out.println("smell");
            } else if (game.distance2(this,u) < game.distance2(this,tu)) {
              setNextTargetUnit(u);
              tu = u;
            }
          }
        }
      }
      if (getNextTargetUnit() != null) {
        setNextActivity("attacking");
      }
      if (targetUnit != null) {
        super.nextActivity();
      }
    }
    if (currentActivity.equals("walking")) {
      double cancelChance = 1-slowMoveSpeed;
      tu = getNextTargetUnit();
      if (tu != null) {
        if (game.distance2(this, tu) <= visionRadius) {
          cancelChance = 1-fastMoveSpeed;
        } else {
          cancelChance = 1-slowMoveSpeed;
        }
      }
      if (RNG.nextDouble() < cancelChance) {
        setCurrentActivity("standing");
        // HOW MUCH OF THIS IS NECESSARY?
        setTargetPosn(null);
        //setTargetUnit(null);
        //setNextTargetUnit(null);
        //setNextActivity(null);
      }
    } else if (currentActivity.equals("attacking")) {
      if (RNG.nextDouble() > attackChance) {
        setCurrentActivity("standing");
        currentEP += attackCost;
        // HOW MUCH OF THIS IS NECESSARY?
        setTargetPosn(null);
      }
    }
  }
  
  public void doAttackHit(Unit u) {
    int dmg = game.getRNG().nextInt(maxDamage - minDamage + 1) + minDamage;
    // soundFX
    u.takeHit(this, dmg);
    playHitSound();
  }

  @Override
  public void playBashSound() {
    // TODO Auto-generated method stub
  }
}
