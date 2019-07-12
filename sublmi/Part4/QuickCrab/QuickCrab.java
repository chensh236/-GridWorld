import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;

import java.util.ArrayList;

public class QuickCrab extends CrabCritter
{   
    public ArrayList<Location> getMoveLocations(){
        ArrayList<Location> locs = new ArrayList<Location>();
        //current states
        Location currLoc = getLocation();
        //get locs
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for(int i : dirs){
            Location forward = currLoc.getAdjacentLocation(i);
            Location forForward = forward.getAdjacentLocation(i);
            //judge if the next grid of the next grid is valid
            if(getGrid().isValid(forward) && getGrid().isValid(forForward) 
                && getGrid().get(forward) == null && getGrid().get(forForward) == null){
                //add this location
                locs.add(forForward);
            }
        }
        //judge if the size of the array is zero
        if(locs.size() != 0){
            return locs;
        }
        //return the move locations of the parent
        return super.getMoveLocations();
    }
}
