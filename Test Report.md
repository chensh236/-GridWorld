# Test Report

16340028 陈思航

####	The test will process according to the given conditions in design report.

__Initial:__ 

​	Before having the unit test, we should initial the members such as the jumper and the Actor world. We should also clear it.

​	

```java
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
```



1. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

   __Answer:__ If there exists a rock, the jumper will turn half right(45 degrees) to avoid dashing against the rock. If there exists a flower, the jumper will still jump.

   __Test:__ We set rocks, flowers surrounding the jumper which are two grids far away from it. If the jumper stay in its location, the test is successful.

   ```java
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
   ```

   ​

2. What will a jumper do if the location two cells in front of the jumper is out of the grid?

   __Answer:__ The jumper will turn 90 degrees to the right, avoiding jumping out of the grid.

   __Test:__ We set the jumper which is facing the boundary of the world which is two cells far away from it. If it turn and change its direction and turns 90 degrees, the test is successful.

   ```java
   @Test
       //test if the jumper can move if it's surrounded by the rocks
       public void testFacing2() {
           world.add(new Location(ONE , ZERO), jumper);
           jumper.act();
           assertEquals(new Location(ONE, ZERO), jumper.getLocation());
           assertEquals(90, jumper.getDirection());
       }
   ```

   ​

3. What will a jumper do if it is facing an edge of the grid?

   __Answer:__ The jumper will turn 90 degrees to the right, avoiding jumping out of the grid.

   __Test:__  We set the jumper which is facing the boundary of the world which is one cell far away from it. If it turn and change its direction and turns 90 degrees, the test is successful.

   ```java
   @Test
       //test if the jumper can move if it's surrounded by the rocks
       public void testFacing() {
           world.add(new Location(ZERO , ZERO), jumper);
           jumper.act();
           assertEquals(new Location(ZERO, ZERO), jumper.getLocation());
           assertEquals(90, jumper.getDirection());
       }
   ```

   ​

4. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

   __Answer:__ If another actor is in the cell that is two cell in front of the jumper, it will turn half_right(45 degrees) to avoid dashing against another actor.

   __Test:__ We set a bug surrounding the jumper which are two grids far away from it. If the jumper stay in its location, the test is successful.

   ```java
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
   ```

   ​

5. What will a jumper do if it encounters another jumper in its path?

   __Answer:__ If it encounters another jumper in its path, it will jump over it.

   __Test:__ We set jumpers surrounding the jumper which are only one grid far away from it. If the jumper stay in its location, the test is successful.

   ```java
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
   ```

   ​

6. Are there any other tests the jumper needs to make?

   __Answer:__ Yes. If the jumper is surrounded by the other actors two cells far way from itself, it will always turn. If there exists two or more actor as a barrier, it will turn much more times.

   __Test:__ These cases are included in the test cases above. We don't have need to test again.

   ​

#### Conclusion: 
Through the tests above, we know that the jumper has possessed the functions above. It's supposed to conform to the given commands above.