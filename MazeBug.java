package info.gridworld.maze;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class MazeBug extends Bug{

    private static final int BEVEL = 90;
    private static final int DIRECTION = 4;
    Location nextLoc;
    //judge if the game is ended
    private boolean theEnd = false;
    //judge if the dialog has been shown
    private Location determine;
    public Stack<Location> stk = new Stack<Location>();
    public ArrayList<Location> selectedLoc = new ArrayList<Location>();
    public int counter = 0;
    int[] turnCount = new int[DIRECTION];

    public MazeBug(){
        setColor(Color.GREEN);
        for (int i = 0; i < DIRECTION; ++i) {
            turnCount[i] = 1;
        }
    }

    public MazeBug(Color bugColor)
    {
        setColor(bugColor);
        for (int i = 0; i < DIRECTION; ++i) {
            turnCount[i] = 1;
        }
        findDetermine();
    }

    public void act()
    {
        if(theEnd){
            return;
        }
        //show the dialog
        if(counter % 300 == 0){
            findDetermine();
            reduce();
        }
        //after judgement
        if(canMove()){
            move();
        } else if(theEnd){
                counter++;
                showDialog();
                return;
        } else {
            Grid<Actor> gr = getGrid();
            if (gr == null){
                return;
            }    
            Location loc = getLocation();
            // the dialog has been shown
            loc = stk.pop();
            nextLoc = stk.peek();
            //backward
            setDirection(loc.getDirectionToward(nextLoc));
            counter++;
            turnCount[loc.getDirectionToward(nextLoc) / BEVEL]--;
            moveTo(nextLoc);
            Flower flower = new Flower(getColor());
            flower.putSelfInGrid(gr, loc);
        }
    }

    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null){
            return;
        }
        if(!canMove()){
            return;
        }
        Location loc = getLocation();
        //start
        if (stk.empty() == true) {
            stk.push(loc);
        }
        //set direction
        setDirection(loc.getDirectionToward(nextLoc));
        ++counter;
        turnCount[loc.getDirectionToward(nextLoc) / BEVEL]++;
        moveTo(nextLoc);
        stk.push(nextLoc);
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null){
            return false;
        }
        Location loc = getLocation();
        pushAllDirections();
        if(theEnd){
            nextLoc = selectedLoc.get(selectedLoc.size() - 1);
            setDirection(loc.getDirectionToward(nextLoc));
            selectedLoc.clear();
            return false;
        }
        //null stack
        if(selectedLoc.isEmpty()){
            selectedLoc.clear();
            return false;
        }

        int[] currentCount = new int[DIRECTION];
        for (int i = 0; i < DIRECTION; ++i) {
            currentCount[i] = -1000000;
        }
        //pop the selected stack
        for (int i = 0; i < selectedLoc.size(); ++i) {
            Location theNext = selectedLoc.get(i);
            int index = loc.getDirectionToward(theNext) / BEVEL;
            currentCount[index] = turnCount[index];
        }
        //selected the max num
        int first = 0;
        int max = currentCount[0];
        for (int i = 1; i < DIRECTION; ++i) {
            if(currentCount[i] > max){
                first = i;
                max = currentCount[i];
            }
        }
        //selected
        int selectDir = first;
        nextLoc = loc.getAdjacentLocation(selectDir * BEVEL);
        selectedLoc.clear();
        return true;
    }

    private void showDialog(){
        String title = "Entrance found";
        String message = "Final: " + counter + " steps.";
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }
    private void reduce(){
        Location loc = getLocation();
        if(determine.getCol() < loc.getCol()){
            turnCount[1] = 1;
            turnCount[3] = 10;
        }else{
            turnCount[1] = 10;
            turnCount[3] = 1;
        }
        if(determine.getRow() < loc.getRow()){
            turnCount[2] = 1;
            turnCount[0] = 10;
        }else{
            turnCount[2] = 10;
            turnCount[0] = 1;
        }
    }
    private void findDetermine(){
        if(determine != null){
            return;
        }
        Grid<Actor> gr = getGrid();
        ArrayList<Location> allOccupies = gr.getOccupiedLocations();
        for (int i = 0; i < allOccupies.size(); ++i){
            if((Actor)gr.get(allOccupies.get(i)) instanceof Rock
                    && ((Actor)gr.get(allOccupies.get(i))).getColor().equals(Color.RED)){
                determine = allOccupies.get(i);
                break;
            }
        }
    }
    private void pushAllDirections(){
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        int dir = getDirection();
        for (int i = 0; i < DIRECTION - 1; ++i) {
            Location theNext = loc.getAdjacentLocation((i - 1) * BEVEL + dir);
            if (!gr.isValid(theNext)){
                continue;
            } else if (gr.get(theNext) == null) {
                selectedLoc.add(theNext);
            } else if ((gr.get(theNext) instanceof Rock) &&
                     (gr.get(theNext).getColor().equals(Color.RED))) {
                        //game over
                        theEnd = true;
                        selectedLoc.add(theNext);
                        break;
            }
        }
    }
}