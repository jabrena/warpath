
public class TestLevel extends Level {
  public TestLevel(RPG game) {
    super(game, 15, 15);
    // Add some player units.
    //HumanUnit u = new HumanUnit(me, "u", new Posn(3,4), me.getHumanPlayer());
    //SwordGirl u = new SwordGirl(me, "u", new Posn(3,4), me.getHumanPlayer());
    
    // Make a hostile AI player

    // Make an "enemy" guy
    
    //HumanUnit x = new HumanUnit(me, "x", new Posn(9,4), me.getPlayer(2));
    //WanderingUnit x = new WanderingUnit(me, "x", new Posn(9,4), me.getPlayer(2));
    EnemySwordGuy x = new EnemySwordGuy(game, "x", new Posn(9,4), game.getPlayer(2));
    units.add(x);
    //EnemySwordGuy y = new EnemySwordGuy(me, "y", new Posn(9,5), me.getPlayer(2));
    //me.addUnit(y);
    
    objects.add(new Wall(game, new Posn(9,7), "wall_48x78_1.png"));
    objects.add(new Wall(game, new Posn(9,8), "wall_48x78_1.png"));
    objects.add(new Wall(game, new Posn(9,9), "wall_48x78_1.png"));
    objects.add(new Wall(game, new Posn(9,10), "wall_48x78_1.png"));
  }
}
