import info.gridworld.grid.Grid;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;

public class Jumper extends Actor{
    //the default times
    private int once = 1;
    private int twice = 2;
    public Jumper(){
        //default color of the bug
        setColor(Color.green);
    }

    public Jumper(Color color){
        //set the color as the client like
        setColor(color);
    }

    public void act(){
        //get the grid world
        Grid<Actor> grid = getGrid();
        if(grid == null){
            return;
        }
        //get the location of the next grid
        Location nextGrid = getLocation().getAdjacentLocation(getDirection());
        //get the location of the next grid of the next grid
        Location nextNextGrid = nextGrid.getAdjacentLocation(getDirection());
        //judge if the grid is valid
        if(!grid.isValid(nextGrid) || !grid.isValid(nextNextGrid)){
            turning(twice);
            return;
        }
        //judge if there exists another actor in the grid
        Actor neighbor = grid.get(nextNextGrid);
        if(neighbor != null){
            turning(once);
            return;
        }
        //move to the grid
        moveTo(nextNextGrid);
    }

    //turing while there is no other actors
    private void turning(int count){
        //turn several times
        for(int i = 0; i < count; ++i){
            setDirection(getDirection() + Location.HALF_RIGHT);
        }
    }
}
