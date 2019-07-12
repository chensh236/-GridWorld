import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.*;

public class JumperTest {
    //useful numbers
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO =2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int FORTYFIVE = 45;
    
    private Jumper jumper = new Jumper();
    private ActorWorld world = new ActorWorld();

    @Before
    public void setUp() throws Exception {
        //get the row and the column of the world
        int row = world.getGrid().getNumRows();
        int col = world.getGrid().getNumCols();
        //initial the world
        for (int i = 0; i < row; ++i) {
            for(int j = 0; j < col; ++j){
                world.remove(new Location(i, j));
            }
        }
    }
    //test if the jumper can act
    @Test
    public void testAct() {
        world.add(new Location(FOUR , FOUR), jumper);
        jumper.act();
        jumper.act();
        assertEquals(new Location(ZERO, FOUR), jumper.getLocation());
    }
    //test if the jumper can turn
    @Test
    //test if the jumper can move if it's surrounded by the jumpers
    public void testMove() {
        world.add(new Location(FOUR , FOUR), jumper);
        world.add(new Location(THREE, FOUR), new jumper());
        world.add(new Location(FIVE, FOUR), new jumper());
        world.add(new Location(FOUR, THREE), new jumper());
        world.add(new Location(FOUR, FIVE), new jumper());
        jumper.act();
        assertEquals(new Location(TWO, FOUR), jumper.getLocation());
    }

    //test if the jumper faces the barrier
    @Test
    //test if the jumper can move if it's surrounded by the rocks
    public void testBarrierRock() {
        world.add(new Location(FOUR , FOUR), jumper);
        world.add(new Location(TWO, FOUR), new Rock());
        world.add(new Location(SIX, FOUR), new Rock());
        world.add(new Location(FOUR, TWO), new Rock());
        world.add(new Location(FOUR, SIX), new Rock());
        jumper.act();
        assertEquals(new Location(FOUR, FOUR), jumper.getLocation());
        assertEquals(FORTYFIVE, jumper.getDirection());
    }

    @Test
    //test if the jumper can move if it's surrounded by the flowers
    public void testBarrierFlower() {
        world.add(new Location(FOUR , FOUR), jumper);
        world.add(new Location(TWO, FOUR), new Flower());
        world.add(new Location(SIX, FOUR), new Flower());
        world.add(new Location(FOUR, TWO), new Flower());
        world.add(new Location(FOUR, SIX), new Flower());
        jumper.act();
        assertEquals(new Location(FOUR, FOUR), jumper.getLocation());
        assertEquals(FORTYFIVE, jumper.getDirection());
    }

    @Test
    //test if the jumper can move if it's surrounded by the bugs
    public void testBarrierBug() {
        world.add(new Location(FOUR , FOUR), jumper);
        world.add(new Location(TWO, FOUR), new Bug());
        world.add(new Location(SIX, FOUR), new Bug());
        world.add(new Location(FOUR, TWO), new Bug());
        world.add(new Location(FOUR, SIX), new Bug());
        jumper.act();
        assertEquals(new Location(FOUR, FOUR), jumper.getLocation());
        assertEquals(FORTYFIVE, jumper.getDirection());
    }

     @Test
    //test if the jumper can move if it's surrounded by the rocks
    public void testBarrierJumper() {
        world.add(new Location(FOUR , FOUR), jumper);
        world.add(new Location(TWO, FOUR), new Jumper());
        world.add(new Location(SIX, FOUR), new Jumper());
        world.add(new Location(FOUR, TWO), new Jumper());
        world.add(new Location(FOUR, SIX), new Jumper());
        jumper.act();
        assertEquals(new Location(FOUR, FOUR), jumper.getLocation());
        assertEquals(FORTYFIVE, jumper.getDirection());
    }


    @Test
    //test if the jumper can move if it's surrounded by the rocks
    public void testFacing() {
        world.add(new Location(ZERO , ZERO), jumper);
        jumper.act();
        assertEquals(new Location(ZERO, ZERO), jumper.getLocation());
        assertEquals(90, jumper.getDirection());
    }

     @Test
    //test if the jumper can move if it's surrounded by the rocks
    public void testFacing2() {
        world.add(new Location(ONE , ZERO), jumper);
        jumper.act();
        assertEquals(new Location(ONE, ZERO), jumper.getLocation());
        assertEquals(90, jumper.getDirection());
    }
}
