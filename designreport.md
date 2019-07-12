# Design Report

16340028 陈思航

###	Inception: clarify the details of the problem

1. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

   __Answer:__ If there exists a rock, the jumper will turn half right(45 degrees) to avoid dashing against the rock. If there exists a flower, the jumper will still jump.

2. What will a jumper do if the location two cells in front of the jumper is out of the grid?

   __Answer:__ The jumper will turn 90 degrees to the right, avoiding jumping out of the grid.

3. What will a jumper do if it is facing an edge of the grid?

   __Answer:__ The jumper will turn 90 degrees to the right, avoiding jumping out of the grid.

4. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

   __Answer:__ If another actor is in the cell that is two cell in front of the jumper, it will turn half_right(45 degrees) to avoid dashing against another actor.

5. What will a jumper do if it encounters another jumper in its path?

   __Answer:__ If it encounters another jumper in its path, it will jump over it.

6. Are there any other tests the jumper needs to make?

   __Answer:__ Yes. If the jumper is surrounded by the other actors two cells far way from itself, it will always turn. If there exists two or more actor as a barrier, it will turn much more times.