import info.gridworld.actor.Bug;
//the declare of the class
public class SpiralBug extends Bug{
    //the current steps
    private int steps;
    //the side length of the world
    private int sideLength;
    //constructor function
    public SpiralBug(int sideLength){
        this.sideLength = sideLength;
        steps = 0;
    }
    //act function
    public void act(){
        //judge if it can move
        if(canMove() && steps < sideLength){
            move();
            ++steps;
            //it can not move
        }else{
            //turn twice
            turn();
            turn();
            //initial the steps
            steps = 0;
            ++sideLength;
        }
    }
}