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

import info.gridworld.grid.UnboundedGrid;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public final class CircleBugRunner{
    private CircleBugRunner(){}
    public static void main(String[] args){
        UnboundedGrid<Actor> unbd = new UnboundedGrid<Actor>();
        ActorWorld world = new ActorWorld(unbd);
        CircleBug cb = new CircleBug(4);
        world.add(new Location(2, 3), cb);
        world.show();
    }
}