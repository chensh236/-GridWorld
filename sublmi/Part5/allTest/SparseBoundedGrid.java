import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class SparseBoundedGrid<E> extends AbstractGrid <E> {
    //the member of this class
    private SparseGridNode[] occupantLinkList;
    private int rowNum;
    private int colNum;
    
    //constructor
    public SparseBoundedGrid(int rows, int cols){

        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        colNum = cols;
        rowNum = rows;
        //construct the new array
        occupantLinkList = new SparseGridNode[rowNum];
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
        for(int i = 0; i < getNumRows(); ++i){
            //get the occupied lcations
            SparseGridNode ptr = occupantLinkList[i];
            while(ptr != null){
                // If there's an object at this location, put it in the array.
                int column = ptr.getColumn();
                locations.add(new Location(i, column));
                ptr = ptr.getNext();
            }
        }
        return locations;
    }

    //get the actor from current location 
    public E get(Location loc){
        this.testLoc(loc);
        int row = loc.getRow();
        int col = loc.getCol();
        SparseGridNode ptr = occupantLinkList[row];
        while(ptr != null){
            if(ptr.getColumn() == col){
                return (E)ptr.getOccupant();
            }else{
                ptr = ptr.getNext();
            }
        }
        //there is no actor corresponding to the location
        return null;
    }
    //put the location
    public E put(Location loc, E obj){
        this.testLoc(loc);
        //set the new location
        int row = loc.getRow();
        int col = loc.getCol();
        E r = remove(loc);
        //get the head node of the link list
        SparseGridNode head = occupantLinkList[row];
        SparseGridNode newHead = new SparseGridNode(obj, col, head);
        occupantLinkList[row] = newHead;
        return r;
    }
    //remove the location
    public E remove(Location loc){
        //judge if the location is valid
        if(!isValid(loc)){
            throw new IllegalArgumentException("Location not valid");
        }
        E r = get(loc);
        //if there is no actor corresponding to the locations
        if(r == null){
            return null;
        }
        int row = loc.getRow();
        int col = loc.getCol();
        //set the ptr from the head
        SparseGridNode ptr = occupantLinkList[row];
        //the link list is null
        if(ptr != null){
            //if it exists in the head of the link list
            if(ptr.getColumn() == col){
                occupantLinkList[row] = ptr.getNext();
            }else{
                SparseGridNode fast = ptr.getNext();
                while(fast != null){
                    if(fast.getColumn() == col){
                        ptr.setNext(fast.getNext());
                        return r;
                    }else{
                        ptr = ptr.getNext();
                        fast = fast.getNext();
                    }
                }
            }
        }
        return r;
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