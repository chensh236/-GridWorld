public class SparseGridNode{
	//the content
    private Object occ;
    private int col;
    //the next node
    private SparseGridNode next;
    //constrcutor
    public SparseGridNode(Object occupant, int column, SparseGridNode getNext){
        this.occ = occupant;
        this.col = column;
        this.next = getNext;
    }
    //get the content from this node
    public Object getOccupant(){
        return this.occ;
    }

    public int getColumn(){
        return this.col;
    }
    //get the next node
    public SparseGridNode getNext(){
        return next;
    }
    //set the current node
    public void setOccupant(Object occupant){
        this.occ = occupant;
    }

    //set the next node
    public void setNext(SparseGridNode newNext){
        next = newNext;
    }
}