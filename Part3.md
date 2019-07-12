# Part3

16340028 陈思航

###	set3

1. We can use

   ```java
   loc1.getRow();
   ```

   to access the row value of loc1.

   ```java
   // @file: framework/info/gridworld/grid/Location.java
   // @line: 110~113
   public int getRow()
   {
       return row;
   }
   ```

2. The value of b is false after the follwing statement is executed. The coordination of  loc1 is different from that of loc2.

   ```java
   // @file: framework/info/gridworld/grid/Location.java
   // @line: 205~212
   public boolean equals(Object other)
   {
       if (!(other instanceof Location))
           return false;
       Location otherLoc = (Location) other;
       return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
   }
   ```

3. The value of loc3 after the following statement is executed is (4,4) because the coordination of the grid which is in the south of the location loc2 is (4,4). The method getAdjacentLocation is to find the adjacent location in the  surrounding of the current location.

   ```java
   // @file: framework/info/gridworld/grid/Location.java
   // @line: 130~139
   public Location getAdjacentLocation(int direction)
       {
           // reduce mod 360 and round to closest multiple of 45
           int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
           if (adjustedDirection < 0)
               adjustedDirection += FULL_CIRCLE;

           adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
           int dc = 0;
           int dr = 0;
   		...
               
   // @file: framework/info/gridworld/grid/Location.java
   // @line: 147~148
       	...
           else if (adjustedDirection == SOUTH)
               dr = 1;
           ...
              
   // @file: framework/info/gridworld/grid/Location.java
   // @line: 168~169
           ...
           return new Location(getRow() + dr, getCol() + dc);
       }
   ```

4. The value of dir is 135 after the following statement is executed, which shows that the location of the coordination (6,5) is in the southeast of the location loc1. The method getDirectionToward is to find the direction between the given location and the current location.

   ```java
   // @file: framework/info/gridworld/grid/Location.java
   // @line: 178~195
   public int getDirectionToward(Location target)
       {
           int dx = target.getCol() - getCol();
           int dy = target.getRow() - getRow();
           // y axis points opposite to mathematical orientation
           int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

           // mathematical angle is counterclockwise from x-axis,
           // compass angle is clockwise from y-axis
           int compassAngle = RIGHT - angle;
           // prepare for truncating division by 45 degrees
           compassAngle += HALF_RIGHT / 2;
           // wrap negative angles
           if (compassAngle < 0)
               compassAngle += FULL_CIRCLE;
           // round to nearest multiple of 45
           return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
       }
   ```

5. The method getAdjacentLocation knows which adjacent location to return through the given parameter that indicates the direction of the adjacent location from the current location. This method finds the closest location in the given direction from the current location.

   ```java
   // @file: framework/info/gridworld/grid/Location.java
   // @line: 133~135
   int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
           if (adjustedDirection < 0)
               adjustedDirection += FULL_CIRCLE;

   // @file: framework/info/gridworld/grid/Location.java
   // @line: 168
   return new Location(getRow() + dr, getCol() + dc);
   ```


------

### set4

1. We can use the method getOccupiedLocation() to get the array list of the occupied location. Then we use the method size() the get the size of the array list. We can obtain acount of the objects in a grid.

   To obtain a count of the empty locations in a bounded grid, we can use the method getNumRows() and the method getNumCols() to get the count of the rows and columns. Then we make a product  of these counts to get the count of all grids. Then we use the method getOccupiedLocations() to get the array list of the occupied locations, using the method size() to get the size of the array list. The count of all grids minus the size of the occupied location list is the count of the empty locations in a bounded grid.
   $$
   Count_{emptyLocations} = object.getNumRows() * object.getNumCols() - object.getOccupiedLocations().size()
   $$
   ```java
   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 35
   int getNumRows();

   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 41
   int getNumCols();

   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 85
   ArrayList<Location> getOccupiedLocations();
   ```

2. we can use the method isValid to check if location(10, 10) is in the grid. The usage is

   ```java
   object.isValid(new Location(10, 10));
   ```

   If and only if the given location is valid, the function will return true.

   ```java
   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 50
   boolean isValid(Location loc);
   ```

3. The class Grid contains method declarations, but no code is supplied in the methods because the class Grid is an interface. The subclass of the interface must implement these methods in the interface. We can find the implementations of these methods in the class BoundedGrid or the class UnboundedGrid or the class AbstrctGrid. These class inherits the interface and implement the methods required by the interface Grid.

   ```java
   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 29
   public interface Grid<E>
   ```

4.  I don't think it would be a better design to return the objects in an array because it's easier to access the data through subscript than calling a function. Furthermore, we don't need to know the size of the array before we fill it. In one word, it's easier for the client to access the data through ArrayList.

   ```java
   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 85
   ArrayList<Location> getOccupiedLocations();

   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 96
   ArrayList<Location> getValidAdjacentLocations(Location loc);

   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 107
   ArrayList<Location> getEmptyAdjacentLocations(Location loc);

   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 118
   ArrayList<Location> getOccupiedAdjacentLocations(Location loc);

   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 129
   ArrayList<E> getNeighbors(Location loc);
   ```



### set5

1. An actor has a grid, a color, a direction and a location.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 31~34
   private Grid<Actor> grid;
   private Location location;
   private int direction;
   private Color color;
   ```

2. It's direction is North and its color is blue.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 41~42
   color = Color.BLUE;
   direction = Location.NORTH;
   ```

3. The class Actor allows the client to declare an instance variable or implement methods, but not the abstract interface. So it should be regarded as a class.  Furthermore it has the member that can be accessed by the client.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 29
   public class Actor
   ```

4. The actor can't put itself into a grid twice without removing itself. If we do this, an exception will be thrown.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 117~119
   if (grid != null)
       throw new IllegalStateException(
       "This actor is already contained in a grid.");
   ```

   We can not also remove the actor twice. If we do this, an exception will be thrown.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 135~137
   if (grid == null)
       throw new IllegalStateException(
       "This actor is not contained in a grid.");
   ```

   The actor can be placed into a grid, remove itself, and then put itself back. The actor goes back.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 121~126
   Actor actor = gr.get(loc);
   if (actor != null)
       actor.removeSelfFromGrid();
   gr.put(loc, this);
   grid = gr;
   location = loc;

   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 134~135
   grid.remove(location);
   grid = null;
   location = null;
   ```

5. We can use the method setDirection to urge the actor to turn 90 degrees to the right. The usage is:

   ```java
   object.setDirection(getDirection() + Locaion.RIGHT);
   ```

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 80~85
   public void setDirection(int newDirection)
   {
       direction = newDirection % Location.FULL_CIRCLE;
       if (direction < 0)
           direction += Location.FULL_CIRCLE;
   }
   ```

   ​

### set6

1. The statement which ensures that a bug does not try to move out of its grid is:

   ```java
   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 78~79
   if (gr.isValid(next))
       moveTo(next);
   ```

2. The statement which determines that a bug will not walk into a rock is :

   ```java
   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 100~101
   Actor neighbor = gr.get(next);
       return (neighbor == null) || (neighbor instanceof Flower);
   ```

3. The method isValid and the method get. The method isValid is used to ensure whether the next location in the bug's direction is valid. The method get is used to get the object in the next location. If the next location is not empty, the bug will turn a direction.

   ```java
   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 50
   boolean isValid(Location loc);

   // @file: framework/info/gridworld/grid/Grid.java
   // @line: 79
   E get(Location loc);

   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 98~100
   if (!gr.isValid(next))
       return false;
   Actor neighbor = gr.get(next);
   ```

4. The method getAdjacentLocation is invoked by the canMove method. This method is called to get the adjacent location in the bug's direction.

   ```java
   // @file: framework/info/gridworld/grid/Location.java
   // @line: 130
   public Location getAdjacentLocation(int direction){

   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 97
   Location next = loc.getAdjacentLocation(getDirection());
   ```

5. These methods are getLocation, getDirection, getGrid.

   ```java
   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 93
   Grid<Actor> gr = getGrid();

   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 96
   Location loc = getLocation();

   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 97
   Location next = loc.getAdjacentLocation(getDirection());
   ```

6. It will call the function removeSelfFromGrid() to remove the bug itself out of the grid.

   ```java
   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 78~81
   if (gr.isValid(next))
       moveTo(next);
   else
       removeSelfFromGrid();
   ```

7. The variable loc needed in the move method. This variable is used to store the current location of the bug. It can be used to insert a flower in the old location of the bug after the bug has moved.

   ```java
   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 76~77
   Location loc = getLocation();
   Location next = loc.getAdjacentLocation(getDirection());

   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 82~83
   Flower flower = new Flower(getColor());
   flower.putSelfInGrid(gr, loc);
   ```

8. It's easier for the client to distinguish the flowers from the specific bug.

   ```java
   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 82
   Flower flower = new Flower(getColor());
   ```

9. There is no flower left in the previous location if the function removeSelfFromGrid() is called. However when this method is called in the method move, the flower will be still left.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 143~145
   grid.remove(location);
   grid = null;
   location = null;

   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 78~83
   if (gr.isValid(next))
       moveTo(next);
   else
       removeSelfFromGrid();
       Flower flower = new Flower(getColor());
       flower.putSelfInGrid(gr, loc);
   ```

10. The statement that places the flower into the grid at the bug's previous location is:

   ```java
   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 82~83
   Flower flower = new Flower(getColor());
   flower.putSelfInGrid(gr, loc);
   ```

11. It should call the method turn 4 times as the bug turns 45 degrees each time.

    ```java
    // @file: framework/info/gridworld/actor/Bug.java
    // @line: 62~65
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    ```

    ​