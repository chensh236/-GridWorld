# Part5

###	set10

1.

- The isValid method is specified in the Grid interface. 

  ```java
  // @file: info/gridworld/grid/Grid.java
  // @line: 50
  boolean isValid(Location loc);
  ```

  ​

- The class BoundedGrid and the class UnboundedGrid implement this method.

  ```java
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 60~64
  public boolean isValid(Location loc)
      {
          return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                  && 0 <= loc.getCol() && loc.getCol() < getNumCols();
      }

  // @file: info/gridworld/grid/UnboundedGrid.java
  // @line: 53~56
  public boolean isValid(Location loc)
      {
          return true;
      }
  ```

  ​

2.

- The method getValidAdjacentLocations call this method isValid.

  ```java
  // @file: info/gridworld/grid/AbstractGrid.java
  // @line: 44~45
  if (isValid(neighborLoc))
      locs.add(neighborLoc);
  ```

- The other methods such as getEmptyAdjacentLocations or getOccupiedAdjacentLocations calls the method getValidAdjacentLocations to call the method isValid. 

- The method get, put and remove in the class BoundedGrid also call the method isValid.

  ```java
  // @file: info/gridworld/grid/AbstractGrid.java
  // @line: 54
  for (Location neighborLoc : getValidAdjacentLocations(loc))
      
  // @file: info/gridworld/grid/AbstractGrid.java
  // @line: 65
  for (Location neighborLoc : getValidAdjacentLocations(loc))

  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 85~87
  public E get(Location loc)
      {
          if (!isValid(loc))
              
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 93~95
  public E put(Location loc, E obj)
      {
          if (!isValid(loc))
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 107~109
  public E remove(Location loc)
      {
          if (!isValid(loc))
  ```

  ​

3.

- The methods get and getOccupiedAdjacentLocations and are called by the getNeighbors.

  ```java
  // @file: info/gridworld/grid/AbstractGrid.java
  // @line: 28~34
  public ArrayList<E> getNeighbors(Location loc)
      {
          ArrayList<E> neighbors = new ArrayList<E>();
          for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
              neighbors.add(get(neighborLoc));
          return neighbors;
      }
  ```

  ​

- The AbstractGrid class implements the getOccupiedAdjacentLocations method.

  ```java
  // @file: info/gridworld/grid/AbstractGrid.java
  // @line: 62~71
  public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
      {
          ArrayList<Location> locs = new ArrayList<Location>();
          for (Location neighborLoc : getValidAdjacentLocations(loc))
          {
              if (get(neighborLoc) != null)
                  locs.add(neighborLoc);
          }
          return locs;
      }
  ```

  ​

- The BoundedGrid and UnboundedGrid implement the method get.

  ```java
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 85~91
  public E get(Location loc)
      {
          if (!isValid(loc))
              throw new IllegalArgumentException("Location " + loc
                      + " is not valid");
          return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
      }

  // @file: info/gridworld/grid/UnboundedGrid.java
  // @line: 66~71
  public E get(Location loc)
      {
          if (loc == null)
              throw new NullPointerException("loc == null");
          return occupantMap.get(loc);
      }
  ```


4.

- The method get returns an object of type E to test whether the returned object is null. The getEmptyAdjacentLocations call the method get to test whether the object is null to judge whether the given location is empty. Otherwise the given location is occupied.

  ```java
  // @file: info/gridworld/grid/UnboundedGrid.java
  // @line: 66~71
  public E get(Location loc)
      {
          if (loc == null)
              throw new NullPointerException("loc == null");
          return occupantMap.get(loc);
      }

  // @file: info/gridworld/grid/AbstractGrid.java
  // @line: 56~57
  if (get(neighborLoc) == null)
      locs.add(neighborLoc);
  ```

  5.

- The number of the valid adjacent locations which can be chosen will be changed from 8 to 4, which are called North, East, South and West.

  ```java
  // @file: info/gridworld/grid/AbstractGrid.java
  // @line: 46
  d = d + Location.HALF_RIGHT;
  ```

  ​

  1.

- The public constructor in the class BoundedGrid ensures that a grid has at least one valid location because it will throw an exception if the given location is invalid.

  ```java
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 39~46
  public BoundedGrid(int rows, int cols)
      {
          if (rows <= 0)
              throw new IllegalArgumentException("rows <= 0");
          if (cols <= 0)
              throw new IllegalArgumentException("cols <= 0");
          occupantArray = new Object[rows][cols];
      }
  ```

  ​

  2.

- The method getNumCols return the column of the grid, which is also the columns of the row 0.

  ```java
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 53~58
  public int getNumCols()
      {
          // Note: according to the constructor precondition, numRows() > 0, so
          // theGrid[0] is non-null.
          return occupantArray[0].length;
  ```

- The constructor guarantees that the Grid object has at least 1 row and column.

  3.

- The value of the row is equal to 0 or bigger than 0. It also smaller than the row of the bounded grid object.

- The value of the row is equal to 0 or bigger than 0. It also smaller than the row of the bounded grid object. 

  ```java
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 60~64
  public boolean isValid(Location loc)
      {
          return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                  && 0 <= loc.getCol() && loc.getCol() < getNumCols();
      }
  ```

  ​

  4.

- The type ArrayList< Location > is returned by the getOccupiedLocations method.

  ```java
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 66
  public ArrayList<Location> getOccupiedLocations()
  ```

- The time complexity (Big-Oh) for this method is O(r * c) because all location in the grid object will be visited to check whether it is occupied. The time complexity of adding the occupied locations is O(1).

  ```java
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 71~78
  for (int r = 0; r < getNumRows(); r++)
          {
              for (int c = 0; c < getNumCols(); c++)
              {
                  // If there's an object at this location, put it in the array.
                  Location loc = new Location(r, c);
                  if (get(loc) != null)
                      theLocations.add(loc);
              }
          }
  ```

  ​

  5.

- The type E is retrned by the get method. The parameter Location is needed by this method.

  ```java
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 85
  public E get(Location loc)
  ```

  ​

- The time complexity for this method is O(1).

6.

- When the location that is added is invalid, an IllegalAargument Exception will be thrown.

  ```java
  // @file: info/gridworld/grid/BoundedGrid.java
  // @line: 95~97
  if (!isValid(loc))
              throw new IllegalArgumentException("Location " + loc
                      + " is not valid");
  ```

  ​

- The time complexity of this method is O(1).

  7. ​

     ​

- The type E is returned by the remove method. If an attempt is made to remove an item from an empty location, null is stored in the location and it will be also returned.

- The time complexity of this method is O(1).

  8.

- The methods such as put, get and remove are efficient because their time complexity is O(1). But the method getOccupiedLocations is not efficient because the time complexity is O(r * c).

  1.

- The methods such as hashCode and equals must be implemented by the class Location so that an instance of HashMap can be used for the map and the method hashCode must return the same value for two locations, which can be checked by the method equals.

  ```java
  // @file: info/gridworld/grid/Location.java
  // @line: 218~221
  public int hashCode()
      {
          return getRow() * 3737 + getCol();
      }
  ```

  ​

- The method compareTo would be required of the Location class if a TreeMap were used instead. The method compareTo should return zero for two locations, which can be checked when the method equals is called.

  ```java
  // @file: info/gridworld/grid/Location.java
  // @line: 234~246
  public int compareTo(Object other)
      {
          Location otherLoc = (Location) other;
          if (getRow() < otherLoc.getRow())
              return -1;
          if (getRow() > otherLoc.getRow())
              return 1;
          if (getCol() < otherLoc.getCol())
              return -1;
          if (getCol() > otherLoc.getCol())
              return 1;
          return 0;
      }
  ```

  ​

  2.

- The UnboundedGrid use hash map to store the actors owing to the grid's endless. And the hash map uses the location to be a key. However, an null location can not be regard as a suitable key for the hash map so it is need to check whether the location is null in the unbounded grid.

  ```java
  // @file: info/gridworld/grid/UnboundedGrid.java
  // @line: 38~41
  public UnboundedGrid()
      {
          occupantMap = new HashMap<Location, E>();
      }

  // @file: info/gridworld/grid/UnboundedGrid.java
  // @line: 66~69
  public E get(Location loc)
      {
          if (loc == null)
              throw new NullPointerException("loc == null");
      
  // @file: info/gridworld/grid/UnboundedGrid.java
  // @line: 73~76
      public E put(Location loc, E obj)
      {
          if (loc == null)
              throw new NullPointerException("loc == null");
          
  // @file: info/gridworld/grid/UnboundedGrid.java
  // @line: 82~85
      public E remove(Location loc)
      {
          if (loc == null)
              throw new NullPointerException("loc == null");
  ```

  ​

- In the bounded grid, when checking the location is valid or not, we use the getRow method in the location. When the location is null, this method will throw an IllegalArgumentException suggesting that the location is null. As there is no need to check whether the location is null in the bounded grid.

  ```java
  // @file: info/gridworld/grid/Location.java
  // @line: 110~113
  public int getRow()
      {
          return row;
      }

  // @file: info/gridworld/grid/Location.java
  // @line: 119~122
  public int getCol()
      {
          return col;
      }
  ```

  ​

  3.

- The time complexity of the methods get, put and remove are O(1).

- The time complexity is O(log n) when the TreeMap takes the place of the HashMap.

  4.

- The objects returned by the getOccupiedLocations method in the class will be in different order owing to the different order of the visited keys.

- The keys in the set Locations will be visited in order from low to high as defined by the compareTo method in the class Location when the TreeMap is used instead of a HashMap.

- In the HashMap, the key is visited in order which is depending on its location in the hash table when the keySet is traversed.

  5.

- A map implementation can be used for a bounded grid. If the HashMap is used in a bounded grid object, the time complexity of the method getOccupiedLocations will be reduced to O(n) from O(r * c) when there is n items in the grid.

- If there exists too many items in the bounded world, the map implementation will need much more space in memory than the two-dimensional array because the map will store not only the items but the corresponding locations. However, the array only stores the items.


|           Methods            | SparseGridNode version | LinkedList< OccupantInCol > version | HashMap version | TreeMap version |
| :--------------------------: | :--------------------: | :---------------------------------: | :-------------: | :-------------: |
|         getNeighbors         |          O(c)          |                O(c)                 |      O(1)       |    O(log n)     |
|  getEmptyAdjacentLocations   |          O(c)          |                O(c)                 |      O(1)       |    O(log n)     |
| getOccupiedAdjacentLocations |          O(c)          |                O(c)                 |      O(1)       |    O(log n)     |
|     getOccupiedLocations     |        O(r + n)        |              O(r + n)               |      O(n)       |      O(n)       |
|             get              |          O(c)          |                O(c)                 |      O(1)       |    O(log n)     |
|             put              |          O(c)          |                O(c)                 |      O(1)       |    O(log n)     |
|            remove            |          O(c)          |                O(c)                 |      O(1)       |    O(log n)     |

- For the method get, the time complexity is O(1).
- For the method put, when the grid world stays in the initialed size, the time complexity is O(1). After resizing, the time complexity increases to O(dimension * dimension).