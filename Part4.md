# Part4

16340028 陈思航

###	set7

1. The methods are act, getActors, processActors, getMoveLocations, selectMoveLocation, makeMove.

   ```java
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 38
   public void act()

   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 56
   public ArrayList<Actor> getActors()

   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 71
   public void processActors(ArrayList<Actor> actors)
       
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 88
   public ArrayList<Location> getMoveLocations()
       
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 104
   public Location selectMoveLocation(ArrayList<Location> locs)
       
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 125
   public void makeMove(Location loc)
   ```

2. Five basic actions common to all critters when they acts are:

   getActores, processActors, getMoveLocations, selectMoveLocation, makeMove

   ```java
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 56
   public ArrayList<Actor> getActors()

   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 71
   public void processActors(ArrayList<Actor> actors)
       
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 88
   public ArrayList<Location> getMoveLocations()
       
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 104
   public Location selectMoveLocation(ArrayList<Location> locs)
       
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 125
   public void makeMove(Location loc)
   ```

3. The subclass of Critter should override the getActors method because the subclass of Critter gets the different actors from the class Critter itself in different locations.

   ```java
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 56~59
   public ArrayList<Actor> getActors()
   {
       return getGrid().getNeighbors(getLocation());
   }
   ```

4. According to the methods in the class Critter, we can change the color of all the actors. Also we can urge them all to move and get their own locations.

   ```java
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 71~78
   public void processActors(ArrayList<Actor> actors)
       {
           for (Actor a : actors)
           {
               if (!(a instanceof Rock) && !(a instanceof Critter))
                   a.removeSelfFromGrid();
           }
       }
   ```

5. The three methods are getMoveLocations, selectMoveLocation, makeMove.

   Firstly, the method getMoveLocations is called to return a list of all the empty adjacent locations in the surrounding. Then the method selectMoveLocation selects one location around the critter randomly if there exists an empty location. If not, the method will return the current location. Furthermore the method makeMove urge the critter move to the selected location.

   ```java
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 88~91
   public ArrayList<Location> getMoveLocations()
       {
           return getGrid().getEmptyAdjacentLocations(getLocation());
       }

   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 104~111
   public Location selectMoveLocation(ArrayList<Location> locs)
       {
           int n = locs.size();
           if (n == 0)
               return getLocation();
           int r = (int) (Math.random() * n);
           return locs.get(r);
       }

   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 125~131
   public void makeMove(Location loc)
       {
           if (loc == null)
               removeSelfFromGrid();
           else
               moveTo(loc);
       }
   ```

6. The class Critter inherits the class Actor with a default constructor. There is no need to have a constructor for Critter because Java will provides a default constructor for class Critter, which is called after the constructor of the class Actor.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 39~45
   public Actor()
       {
           color = Color.BLUE;
           direction = Location.NORTH;
           grid = null;
           location = null;
       }
   ```
​
### set8

1. The method act in the class ChameleonCritter doesn't override but the other methods such as processActors and makeMove has override . so the result of the method act in the class ChameleonCritter isn't the same as that in the class Actor.
   ```java
   // @file: projects/critters/ChameleonCritter.java
   // @line: 36~45
    public void processActors(ArrayList<Actor> actors)
       {
           int n = actors.size();
           if (n == 0)
               return;
           int r = (int) (Math.random() * n);

           Actor other = actors.get(r);
           setColor(other.getColor());
       }

   // @file: projects/critters/ChameleonCritter.java
   // @line: 50~54
   public void makeMove(Location loc)
       {
           setDirection(getLocation().getDirectionToward(loc));
           super.makeMove(loc);
       }
   ```

2. The makeMove method of ChameleonCritter call super.makeMove to urges the Critter class to move to the given location. The method makeMove of the ChameleonCritter changes the direction of the critter and the method makeMove urges it to move.

   ```java
   // @file: projects/critters/ChameleonCritter.java
   // @line: 53
   super.makeMove(loc);
   ```

3. We can modify the method called makeMove to drop the flowers in the old location as:

   ```java
   // @file: projects/critters/ChameleonCritter.java
   // @line: 50
   public void makeMove(Location loc)
       {
       	Location current = getLocation();
           setDirection(current.getDirectionToward(loc));
           super.makeMove(loc);
       	if(loc.qeuals(current)) return;
       	Flower flower = new Flower(getColor());
       	flower.putSelfInGrid(getGrid(), current);
       }
   ```

4.  The method getActors in the class ChameleonCritter has the same function as that in the class Critter. There is no need to override this method in the class ChameleonCritter.

   ```java
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 56~59
   public ArrayList<Actor> getActors()
       {
           return getGrid().getNeighbors(getLocation());
       }
   ```

5.  The class Actor contains the method getLocation. the classed such as Critter and ChameleonCritter inherits this class.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 102~105
   public Location getLocation()
       {
           return location;
       }
   ```
6.  The class Critter accesses its own grid by calling the method getGrid which exists in the class Actor which is regarded as its parent.

   ```java
   // @file: framework/info/gridworld/actor/Actor.java
   // @line: 92~95
   public Grid<Actor> getGrid()
       {
           return grid;
       }
   ```
​
### set9

1. There is no need to override the processActors method because the neighbors in the list returned by the method processActors is the same as that in the class Critter.

   ```java
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 56~59
   public ArrayList<Actor> getActors()
       {
           return getGrid().getNeighbors(getLocation());
       }
   ```

2. The process that a CrabCritter uses to find and eat other actors need two methods. The method getActors return the list of the neighbors surrounding the Crab. Then the method processActors is called to urge the crab to eat any neighbors found around itself. The functions of the methods such as getActors and processActors are the same as that in the class Critter.

   ```java
   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 56~59
   public ArrayList<Actor> getActors()
       {
           return getGrid().getNeighbors(getLocation());
       }

   // @file: framework/info/gridworld/actor/Critter.java
   // @line: 71~78
   public void processActors(ArrayList<Actor> actors)
       {
           for (Actor a : actors)
           {
               if (!(a instanceof Rock) && !(a instanceof Critter))
                   a.removeSelfFromGrid();
           }
       }
   ```

3. This method returns an ArrayList of the locations of the neighbors around the crab in its direction. It gives the client some valid locations where the neighbors around the crab exists.

   ```java
   // @file: projects/critters/CrabCritter.java
   // @line: 107~112
   for (int d : directions)
           {
               Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
               if (gr.isValid(neighborLoc))
                   locs.add(neighborLoc);
           }
   ```

4. The locations are (4, 3), (4, 4), (4, 5).

   ```java
   // @file: projects/critters/CrabCritter.java
   // @line: 107~112
   for (int d : directions)
           {
               Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
               if (gr.isValid(neighborLoc))
                   locs.add(neighborLoc);
           }
   ```

5. The similarities:

   1. The Critter and the CrabCritter both choose any direction to move randomly.
   2. They keep their own directions if there is no any other barrier.

   The differences:

   1. The CrabCritter can set its own color in red in default constructor function. There is no given constructor function in the class Critter.
   2. The CrabCritter move from right to left or from left to right actually. But the Critter can move anywhere around its location.
   3. When there exists a barrier and the CrabCritter can not move, it will randomly turn left or turn right. However, the Critter will still stay there.

   ```java
   // @file: projects/critters/CrabCritter.java
   // @line: 79~90
   if (loc.equals(getLocation()))
   {
       double r = Math.random();
       int angle;
       if (r < 0.5)
           angle = Location.LEFT;
       else
           angle = Location.RIGHT;
       setDirection(getDirection() + angle);
   }
   else
       super.makeMove(loc);

   // @file: info/gridworld/actor/Critter.java
   // @line: 127~130
   if (loc == null)
       removeSelfFromGrid();
   else
       moveTo(loc);

   // @file: projects/critters/CrabCritter.java
   // @line: 34~37
   public CrabCritter()
       {
           setColor(Color.RED);
       }
   ```

6. If the value of the current location is the same as the given location, the CrabCritter will turn instead of moving.

   ```java
   // @file: projects/critters/CrabCritter.java
   // @line: 79~88
   if (loc.equals(getLocation()))
   {
       double r = Math.random();
       int angle;
       if (r < 0.5)
           angle = Location.LEFT;
       else
           angle = Location.RIGHT;
       setDirection(getDirection() + angle);
   }
   ```

7. According to the processActors in class Critter, if there exists another Critter, the other Critter should not be removd. So the CrabCritter can not eat each other because the class CrabCritter inherits the class Critter.

   ```java
   // @file: projects/critters/CrabCritter.java
   // @line: 75~76
   if (!(a instanceof Rock) && !(a instanceof Critter))
       a.removeSelfFromGrid();
   ```