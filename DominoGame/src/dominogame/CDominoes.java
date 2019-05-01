/*
 * Allyn Vo Carlos Valdez
 * CS 380
 * Assignment: dominos game project 1
 */
package dominogame;

/**
 *
 * @author allynvo
 */
public class CDominoes implements Comparable<CDominoes> {
    
    public int left, right, available;
    
    public CDominoes(){
        this.left = 0;
        this.right = 0;
        this.available = 1; // 1 is available
    }
    
    public CDominoes(int L, int R){
        this.left = L;
        this.right = R;
        this.available = 1;
    }
    
    public void flip(){
        int oldLeft = left;
        int oldRight = right;
        right = oldLeft;
        left = oldRight;
    }
    
    public void printT(){
        System.out.println("---");
	System.out.println(" " + left);
	System.out.println(" -");
	System.out.println(" " + right);
	System.out.println("---");
    }

    @Override
    public int compareTo(CDominoes o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        return "[" + left + "|" + right + "]";
    }
    
}
