import info.gridworld.actor.Bug;

public class DancingBug extends Bug{
    //the array
    private int []array;
    private int current;
    //the constrcutor function
    public DancingBug(int[] arr){
        //judge if there is no times of dancing
        if(arr == null){
            this.array = new int[0];
        } else{
            //if there is some time to dance
            this.array = new int[arr.length];
            for (int i = 0; i < arr.length; ++i) {
                this.array[i] = arr[i];
            }
        }
        //initial the current step
        current = 0;
    }
    //some tiems to turn
    public void turnAround(int length){
        for (int i = 0; i < length; ++i) {
            turn();
        }
    }
    //the act function
    public void act(){
        current = current < array.length? current : 0;
        this.turnAround(array[current]);
        ++current;
        super.act();
    }
}