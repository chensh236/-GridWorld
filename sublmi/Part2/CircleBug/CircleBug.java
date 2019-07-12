//the import
import info.gridworld.actor.Bug;
//the declare of the class
public class CircleBug extends Bug{
    //the current steps in the direction
    private int steps;
    private int sideLength;
    //constructor function
    public CircleBug(int sideLength){
        steps = 0;
        this.sideLength = sideLength;
    }
    //the act function
    public void act(){
        //judge if it can move
        if(canMove() && steps < sideLength){
            move();
            ++steps;
            //it can not move
        }else{
            //initial the numbers
            turn();
            steps = 0;
        }
    }
}
