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
public class CPlayer {
    protected static int numPlayers = 0;
    protected String name; //Name of player
    protected int id; // Players ID number
    List<CDominoes> hand; //The player hand (pieces of dominoes)
    protected boolean skipped;
    
    public CPlayer(String name){
        numPlayers++;
        this.name = name;
        this.id = numPlayers;
        this.hand = new ArrayList<CDominoes>();
        this.skipped = false;
    }
    
    public boolean hasPlay(int value){
        for(CDominoes current : hand){
            if((value == current.left) || (value == current.right)){
                return true;
            }
        }
        
        return false;
    }
    
    public void showPieces(){
        for(CDominoes current : hand){
            System.out.print("[" + current.left + "|" + current.right + "] ");
            //System.out.println("available = " + current.available);
        }
    }
    
    public void showTable(){
        System.out.print("+---");
        for(int i = 0; i < 28; i++){
            System.out.print("-----");
            }
            System.out.println();
            System.out.print("| ");
        for(CDominoes current : hand){
            System.out.print("[" + current.left + "|" + current.right + "]");
            
        }
        System.out.println();
        System.out.print("+---");
        for(int i = 0; i < 28; i++){
            System.out.print("-----");
            }
    }
}
