import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

public final class ChameleonKidRunner
{
	//useful numbers
    private static final int TWO = 2;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
	private ChameleonKidRunner(){}
	//main function
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        //add new actors
        world.add(new Location(SEVEN, TWO), new Rock(Color.YELLOW));
        world.add(new Location(FOUR, FOUR), new ChameleonKid());
        world.add(new Location(FIVE, EIGHT), new ChameleonKid());
        world.show();
    }
}