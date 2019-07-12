# Part2

16340028 陈思航

###	set2

1. the instance variable sideLength defines the max number of the step that the bug can move as the boundary of the box.

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 45
   if (steps < sideLength && canMove())
   ```

   ​

2. The instance  variable steps records the number of steps in the current direction that the bug has moved in current direction.

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 45~55
   if (steps < sideLength && canMove())
   {
   	move();
   	steps++;
   }
   else
   {
   	turn();
       turn();
       steps = 0;
   }
   ```

   ​

3. When the turn method is called, the bug will turn 45 degrees to the right. However, when the bug has moved sideLength steps, it should turn 90 degrees to the right. We call the turn method twice to enable the bug to turn 90 degress, avoiding moving out of the box.

   ```java
   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 64
   setDirection(getDirection() + Location.HALF_RIGHT);

   // @file: framework/info/gridworld/grid/Location.java
   // @line: 48
   public static final int HALF_RIGHT = 45;

   // @file: projects/boxBug/BoxBug.java
   // @line: 51~55
   {
   	turn();
   	turn();
   	steps = 0;
   }
   ```

4. The class BoxBug extends the class Bug. The class Bug has a public move method. As the subclass of the class Bug, BoxBug  inherits the move method from Bug and it can call this method.

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 25
   public class BoxBug extends Bug

   // @file: framework/info/gridworld/actor/Bug.java
   // @line: 71
   public void move()
   ```

   ​

5. Yes. The member sideLength in class BoxBug is private and there is no method to edit this member so once the size of its square pattern is set, it can't be changed by the client code.

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 28
   private int sideLength;
   ```

   ​

6. Yes, if there exists another actor in front of the track of the bug, the bug will turn to a new direction. As a result the path is changed.

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 45
   if (steps < sideLength && canMove())

   // @file: projects/boxBug/BoxBug.java
   // @line: 50~55
       else
           {
               turn();
               turn();
               steps = 0;
           }
   ```

   ​

7. When the steps is equal to the sideLength or once the class BoxBug is constructed, the value of steps will be zero.

   ```java
   // @file: projects/boxBug/BoxBug.java
   // @line: 34~38
   public BoxBug(int length)
   {
   	steps = 0;
       sideLength = length;
   }

   // @file: projects/boxBug/BoxBug.java
   // @line: 45
   if (steps < sideLength && canMove())

   // @file: projects/boxBug/BoxBug.java
   // @line: 50~55
   else
   {
   	turn();
       turn();
       steps = 0;
   }
   ```

   ​

