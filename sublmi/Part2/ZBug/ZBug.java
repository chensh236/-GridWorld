import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class ZBug extends Bug{
    //the enum of the segment
    private enum segment{
        Z_FIRST, Z_SECOND, Z_THIRD
    }
    //the segement current
    private segment current;
    private int steps;
    private int length;
    //the constructor function
    public ZBug(int length){
        this.length = length;
        steps = 0;
        current = segment.Z_FIRST;
        setDirection(Location.EAST);
    }
    //the act function
    public void act(){
        //judge if the step if valid
        if(steps < length && canMove()){
            move();
            ++steps;
        }else{
            //judge the segment of the z
            switch(current){
                //the first
                case Z_FIRST:
                    setDirection(Location.SOUTHWEST);
                    steps = 0;
                    current = segment.Z_SECOND;
                    break;
                //the second
                case Z_SECOND:
                    setDirection(Location.EAST);
                    steps = 0;
                    current = segment.Z_THIRD;
                    break;
                //the third
                default:
                    break;
            }
        }
    }
}