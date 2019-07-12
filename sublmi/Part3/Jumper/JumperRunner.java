import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public final class JumperRunner{
    private JumperRunner(){}
    public static void main(String[] args){
        ActorWorld world = new ActorWorld();
        world.add(new Bug());
        world.add(new Flower());
        world.add(new Rock());
        world.add(new Jumper());
        world.show();
    }
}
