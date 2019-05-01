/*
 * Allyn Vo Carlos Valdez
 * CS 380
 * Assignment: dominos game project 1
 */

package dominogame;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class DominoGame {

    protected static int numPlayers;
    protected static List<CPlayer> playerList;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        start();
    }

    public static void start() {
        numPlayers = 2;

        playerList = new ArrayList<CPlayer>();
        playerList.add(new CPlayer("AI1"));
        playerList.add(new CPlayer("AI2"));

        CTable newTable = new CTable();

        CRandom newPieces = new CRandom();

        //Deal and show pieces 
        for (CPlayer player : playerList) {
            newPieces.deal(player, 10);
            System.out.println("\nPlayerID = " + player.id + " stores " + player.hand.size() + " pieces.");
            player.showPieces();
        }

        //Show unclaimed pieces
        System.out.println("\nPieces that are unclaimed:");
        for (CDominoes piece : newPieces.pieces) {
            System.out.println("[" + piece.left + "|" + piece.right + "]");
        }

        //Randomly select player to start
        int randomIndex = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        System.out.println("\nRandom index: " + randomIndex);

        //Play pieces
        int tableIndex = numPlayers;
        playerList.add(new CPlayer("Table"));
        
        playerList.get(tableIndex).hand.add(playerList.get(randomIndex).hand.remove(0));
        
        System.out.println("First piece chosen from player ID = " + randomIndex + ":");
        

        int index = randomIndex;

        int trainLeft;
        int trainRight;

        int skips = 0;
        
        CPlayer player;

        while (true) {
            if (skips >= 3) {
                for (int h = 0; h < numPlayers; h++) {
                    player = playerList.get(h);
                    System.out.println("\nPlayerID = " + player.id + " stores " + player.hand.size() + " pieces.");
                    player.showPieces();
                }
                // decision for which player wins
                if(playerList.get(0).hand.size() > playerList.get(1).hand.size()){
                    System.out.println("Player ID " + playerList.get(0).name + " WINS");
                }
                else if(playerList.get(0).hand.size() < playerList.get(1).hand.size()){
                    System.out.println("Player ID " + playerList.get(1).name + " WINS");
                }
                else if(playerList.get(0).hand.size() == playerList.get(1).hand.size()){
                    System.out.println("IT's a DRAW");
                }
                
                break;
            }
            if (playerList.get(0).hand.isEmpty()) {
                System.out.println("Player ID " + playerList.get(0).name + " WINS");
                for (int h = 0; h < numPlayers; h++) {
                    player = playerList.get(h);
                    System.out.println("\nPlayerID = " + player.id + " stores " + player.hand.size() + " pieces.");
                    player.showPieces();
                }
                break;
            }
            if (playerList.get(1).hand.isEmpty()) {
                System.out.println("Player ID " + playerList.get(1).name + " WINS");
                for (int h = 0; h < numPlayers; h++) {
                    player = playerList.get(h);
                    System.out.println("\nPlayerID = " + player.id + " stores " + player.hand.size() + " pieces.");
                    player.showPieces();
                }
                break;
            }
            index = (index + 1) % numPlayers;

            //find a match to the train
            trainLeft = playerList.get(tableIndex).hand.get(0).left;
            trainRight = playerList.get(tableIndex).hand.get(playerList.get(tableIndex).hand.size() - 1).right;
            int leftTile = -1;
            int rightTile = -1;
            CDominoes domino = null;

            //checks each player's hand for a match to train
            for (int i = 0; i < playerList.get(index).hand.size(); i++) {
                System.out.println("checking piece " + i + " of " + playerList.get(index).hand.size());
                CDominoes dom = playerList.get(index).hand.get(i);

                leftTile = compare(dom.left, trainLeft, trainRight);
                rightTile = compare(dom.right, trainLeft, trainRight);

                //checks domino side to compare to train
                if (leftTile == 0) { // [2|5]-flip-[5|2] -> [2|3][3|1] 
                    playerList.get(index).hand.get(i).flip();
                    playerList.get(tableIndex).hand.add(0, playerList.get(index).hand.remove(i));
                    if (skips > 0) {
                        skips--;
                    }
                    break;
                }
                if (rightTile == 0) { // [5|2] -> [2|3][3|1] 
                    playerList.get(tableIndex).hand.add(0, playerList.get(index).hand.remove(i));
                    if (skips > 0) {
                        skips--;
                    }
                    break;
                }
                if (leftTile == 1) { // [2|3][3|1] <- [1|2]
                    playerList.get(tableIndex).hand.add(playerList.get(index).hand.remove(i));
                    if (skips > 0) {
                        skips--;
                    }
                    break;
                }
                if (rightTile == 1) { // [2|3][3|1] <- [1|5]-flip-[5|1]<-
                    playerList.get(index).hand.get(i).flip();
                    playerList.get(tableIndex).hand.add(playerList.get(index).hand.remove(i));
                    if (skips > 0) {
                        skips--;
                    }
                    break;
                }

                if (rightTile == -1 && leftTile == -1 && i == playerList.get(index).hand.size()-1) {
                    //Can't play so add unclaimed pieces till found one to play
                    //If still can't play, player with less pieces wins.
                    if (newPieces.pieces.isEmpty()) {
                        skips++;
                        break;
                    }
                    System.out.println("Added new piece to player "+ playerList.get(index).name);
                    playerList.get(index).hand.add(newPieces.pieces.remove(0));
                }
            }
            System.out.println("Next piece by player " + playerList.get(index).name);
            playerList.get(tableIndex).showTable();
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        }
    }

    public static int compare(int value, int L, int R) {
        if (value == L) {
            return 0; //Matches left side of train
        } else if (value == R) {
            return 1; //Matches right side of train
        } else {
            return -1;
        }
    }

}
