/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class RockHoundRunner
{
    //useful numbers
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private RockHoundRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(FOUR, FIVE), new Rock(Color.BLUE));
        world.add(new Location(FIVE, FIVE), new Rock(Color.PINK));
        world.add(new Location(FOUR, FOUR), new RockHound());
        //show
        world.show();
    }
}