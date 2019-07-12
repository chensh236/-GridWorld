import info.gridworld.grid.*;
import java.util.ArrayList;

public class UnboundedGrid<E> extends AbstractGrid <E>{
    private static final int INITIAL = 16;
    private static final int ERRNUM = -1;
    //the members in the class
    private int dimension;
    private Object[][] occupantArray;
    //constructor
    public UnboundedGrid(){
        dimension = INITIAL;
        occupantArray = new Object[INITIAL][INITIAL];
    }

    //get the number of rows and columns
    public int getNumRows(){
    	return ERRNUM;
    }

    public int getNumCols(){
    	return ERRNUM;
    }
    //judge if the location is valid
    public boolean isValid(Location loc){
    	if(loc.getRow() >= 0 && loc.getCol() >= 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    public ArrayList<Location> getOccupiedLocations(){
    	//construct a new array
    	ArrayList<Location> locations = new ArrayList<Location>();
    	//traverse
    	for(int i = 0; i < dimension; ++i){
    		for (int j = 0; j < dimension; ++j) {
    			Location tmp = new Location(i, j);
    			//find if there exists an actor in this location
    			if(isValid(tmp) && this.get(tmp) != null){
    				locations.add(tmp);
    			}
    		}
    	}
    	//return the array
    	return locations;
    }

    public E get(Location loc){
    	this.testLoc(loc);
        int row = loc.getRow();
        int col = loc.getCol();
        E r = (row >= dimension || col >= dimension)? null : (E)occupantArray[row][col];
        return r;
    }

    public E put(Location loc, Object obj){
    	this.testLoc(loc);
        //judge if the object is null
        if(obj == null){
        	throw new IllegalArgumentException("Object null");
        }

        int row = loc.getRow();
        int col = loc.getCol();
        //overflow
        if(row >= dimension || col >= dimension){
        	this.expend(loc);
        }

        E old = get(loc);
        //renew
        occupantArray[row][col] = obj;
        return old;
    }

    //remove the object
    public E remove(Location loc){
    	this.testLoc(loc);
    	//overflow
    	if(loc.getRow() >= dimension || loc.getCol() >= dimension){
        	return null;
        }
        //get the corresponding object
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
		return r;
    }
    //expend when overflow
    private void expend(Location loc){
    	int factor = dimension;
    	//expending
    	while(!(loc.getRow() < factor && loc.getCol() < factor)){
    		factor = factor * 2;
    	}
		//new array
    	Object[][] newArray = new Object[factor][factor];
    	//copy content to the new array
    	for(int i = 0; i < dimension; ++i){
    		for (int j = 0; j < dimension; ++j) {
    			newArray[i][j] = occupantArray[i][j];
    		}
    	}
    	//renew the factors
    	dimension = factor;
    	occupantArray = newArray;
    }

    //location test
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