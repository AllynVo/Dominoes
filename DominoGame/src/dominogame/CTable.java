/*
 * Allyn Vo Carlos Valdez
 * CS 380
 * Assignment: dominos game project 1
 */
package dominogame;
import java.util.*;
/**
 *
 * @author allynvo
 */
public class CTable {
    protected List<CDominoes> dominoes;
    protected int end1, end2;
    
    public CTable(){
        this.dominoes = new LinkedList<CDominoes>();
    }
    
    public List<CDominoes> getDominoes(){
        return dominoes;
    }
    
    public void addToEnd1(CDominoes newDomino){
        //add to the start/left side
        dominoes.add(0, newDomino);
        
        
    }
    
    public void addToEnd2(CDominoes newDomino){
        //add to the end/right side
        dominoes.add(newDomino);
        
        
    }
    
    public void printTable(){
        for(CDominoes dom : dominoes){
            dom.printT();
        }
        System.out.println();
    }
    
}
