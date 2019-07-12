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
   ​
2. we can use the method isValid to check if location(10, 10) is in the grid. The usage is

   ```java
   object.isValid(new Location(10, 10));
   ```

   If and only if the given location is valid, the function will return true.

3. The class Grid contains method declarations, but no code is supplied in the methods because the class Grid is an interface. The subclass of the interface must implement these methods in the interface. We can find the implementations of these methods in the class BoundedGrid or the class UnboundedGrid or the class AbstrctGrid. These class inherits the interface and implement the methods required by the interface Grid.