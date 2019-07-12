import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

import java.util.ArrayList;
//declare of the rock hound
public class RockHound extends Critter
{
    //the processActors
    public void processActors(ArrayList<Actor> actors){
        for (Actor actor : actors) {
            if(actor instanceof Rock){
                //remove the rocks
                actor.removeSelfFromGrid();
            }
        }
    }
}
