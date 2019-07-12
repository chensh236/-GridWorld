import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import java.awt.Color;
import java.util.ArrayList;

public class BlusterCritter extends Critter
{
    //default constructor function
    public BlusterCritter(){
        this.counter = MAX;
    }
    //darken factor
    private static final double DARKENING_FACTOR = 0.05;
    private static final int MAX = 255;
    private static final int INITIAL =0;
    private static final int FACTOR = 10;
    private static final int TWO = 2;
    private int counter;
    public BlusterCritter(int c){
        super();
        counter = c;
    }

    public ArrayList<Actor> getActors(){
        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        //get the current location
        Location current = getLocation();
        //scan all the actors that exists within 2 grids
        for (int i = current.getRow() - TWO; i <= current.getRow() + TWO; ++ i) {
            for(int j = current.getCol() - TWO; j <= current.getCol() + TWO; ++ j){
                Location tmpLoc = new Location(i, j);
                //judge if the actor is valid and normal
                if(getGrid().isValid(tmpLoc) && !tmpLoc.equals(current)){
                   Actor tmpActor = getGrid().get(tmpLoc);
                   if(tmpActor != null){
                    neighbors.add(tmpActor);
                   } 
                }
            }
        }
        return neighbors;
    }
    //override
    public void processActors(ArrayList<Actor> neighbors){
        int index = 0;
        //count the Critter Actor
        for(Actor tmp : neighbors){
            if(tmp instanceof Critter){
                ++index;
            }
        }
        if(index < counter){
            brighter();
        }else{
            darken();
        }
    }

    //brighter and darken
    private void darken(){
        Color c = getColor();
        //obteain the numbers of rgb
        
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        //avoid underflowing
        if(red < INITIAL){
            red = INITIAL;
        }
        if(green < INITIAL){
            green = INITIAL;
        }
        if (blue < INITIAL) {
            blue = INITIAL;
        }
        setColor(new Color(red, green, blue));
    }

    private void brighter(){
        Color c = getColor();
        int red = c.getRed() + FACTOR;
        int green = c.getGreen() + FACTOR;
        int blue = c.getBlue() + FACTOR;
        //avoid overflowing
        if(red > MAX){
            red = MAX;
        }
        if(green > MAX){
            green = MAX;
        }
        if (blue > MAX) {
            blue = MAX;
        }
        setColor(new Color(red, green, blue));
    }
}
