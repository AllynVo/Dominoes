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
public class CRandom {
    public List<CDominoes> pieces;
    public int ogSize;
    
    public CRandom(){
        pieces = new ArrayList<CDominoes>();
        
        for(int i = 0; i <= 6; i++){
            for(int k = i; k <= 6; k++){
                CDominoes newDom = new CDominoes(i, k);
                System.out.print("[" + newDom.left + "|" + newDom.right + "] ");
                pieces.add(newDom);
            }
            System.out.println();
        }
        ogSize = pieces.size();
        System.out.println("Stored " + ogSize + " pieces.");
        
        //Randomized/shuffle pieces
        Collections.shuffle(pieces);
    }
    
    public void deal(CPlayer player, int amount){
        for(int i = 0; i < amount; i++){
            player.hand.add(pieces.remove(0));
        }
    }
    
}
