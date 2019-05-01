# Dominoes Game
Simple dominoes game. 
Developed with my partner Carlos V.

### Implementation
* Class **CRandom** as sorting approach for the sequence that the domino pieces will be shown/picked. 
* Class **CTable** to show/display the sorted pieces. 
* Class **CDominoes** to contain the data structure with pieces. 
* Class **CPlayer** to select a randomly picked and sequentially show the selected pieces.

### Details
* 2 players
* From the 28 pieces, each player randomly gets 10 pieces
  * The remaining 8 pieces stay available to be taken if a player doesn't have any piece that will match the head or tail of the game
* The first person to play is selected at random.
* The winner plays all of their pieces, so having emptied their hand before the other player
