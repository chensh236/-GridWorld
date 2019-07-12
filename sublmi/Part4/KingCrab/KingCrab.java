import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import java.util.*;
import java.util.ArrayList;

public class KingCrab extends CrabCritter
{
    private static final double FACTOR = 0.5;
    private boolean ifCanMove(Location a, Location b){
        int row = a.getRow() - b.getRow();
        int col = a.getCol() - b.getCol();
        //mathematical method to calculate the distance
        int distance = (int)Math.floor(Math.sqrt(row * row + col * col) + FACTOR);
        //judge if distance is bigger than 1
        return (distance > 1);
    }
    //move of discard the actor
    private void objectMove(ArrayList<Location> locs, Actor a){
        //for each location having this method
        for(Location loc : locs){
            if(ifCanMove(loc, this.getLocation())){
                a.moveTo(loc);
                return;
            }
        }
        a.removeSelfFromGrid();
    }

    //override
    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }

    public void processActors(ArrayList<Actor> actors){
        for(Actor a : actors){
            //get the array list of the location
            ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(a.getLocation());
            objectMove(locs, a);
        }
    }
}
