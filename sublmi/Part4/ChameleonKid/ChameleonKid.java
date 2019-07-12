//import
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter
{
    public ArrayList<Actor> getActors(){
        //the array list of the neighbors
        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        //the location and the direction of the current critter
        Location currLoc = getLocation();
        //the location of that ahead and behind
        int[] directions = {Location.AHEAD, Location.HALF_CIRCLE};
        for(int i = 0; i < directions.length; ++i){
            //judge the location forward
            Location forward = currLoc.getAdjacentLocation(getDirection() + directions[i]);
            if(getGrid().isValid(forward)){
                Actor a = getGrid().get(forward);
                //add the unempty actor
                if(a != null){
                    neighbors.add(a);
                }
            }
        }
        return neighbors;
    }
}
