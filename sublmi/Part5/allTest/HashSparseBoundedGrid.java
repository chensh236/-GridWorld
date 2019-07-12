import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class HashSparseBoundedGrid<E> extends AbstractGrid <E> {
    private int rowNum;
    private int colNum;
    private Map<Location, E> occupantMap;
    //constructor
    public HashSparseBoundedGrid(int rows, int cols){
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        colNum = cols;
        rowNum = rows;
        //construct the new array
        occupantMap = new HashMap<Location, E>();
    }

    public int getNumRows(){
        return this.rowNum;
    }

    public int getNumCols(){
        return this.colNum;
    }

    public boolean isValid(Location loc){
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> locations = new ArrayList<Location>();
        //traverse
        for(Location loc : occupantMap.keySet()){
            locations.add(loc);
        }
        return locations;
    }

    //get the actor from current location 
    public E get(Location loc){
    	//judge if the location is null
        if(loc == null){
     	   throw new IllegalArgumentException("Location null");
        }
        //judge if the location is vali
        if(!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        return occupantMap.get(loc);
    }
    //put the location
    public E put(Location loc, E obj){
    	this.testLoc(loc);
        if(obj == null){
            throw new IllegalArgumentException("obj == null");
        }
        return occupantMap.put(loc, obj);
    }
    //remove the location
    public E remove(Location loc){
    	this.testLoc(loc);
        //remove and return
        return occupantMap.remove(loc);
    }

    private void testLoc(Location loc){
        //judge if the location is null
        if(loc == null){
           throw new IllegalArgumentException("Location null");
        }
        //judge if the location is valid
        if(!isValid(loc)){
            throw new IllegalArgumentException("Location not valid");
        }
    }
}