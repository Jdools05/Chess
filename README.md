# Chess
Chess engine build off of pure Java

NOTE: This project is still under development. I am the only one working on this, it will take some time. With that being said, if you have suggestions and/or fixes. Please don't hesitate to reach out. Open an issue and I will respond. Thanks!

Functionality:
  Inside Main.java there is a "game" loop that takes in a move as a String in the format: "Tile_start-Tile_end". EX: "e2-e4" is the Kings pawn two tiles forward.
  To promote a pawn, move to the opposite side of the board and append the symbol of the piece to promote to. Acceptable promotions are "Q", "N", "B", "R" EX: "c7-c8Q" promotes a pawn and the C file to a Queen.

  The engine keeps track of whos turn starting with White. There is no need to specify whos making the move, if it is blacks turn, the engine will not permit a white piece to move.
  
Structure:
  This engine has a Game class that holds a 2D array of Tiles, which are a class that stores position and pieces. The Game class parses the moves into a start tile and and end tile. The game then checks for a valid piece on the start tile. If there is, then the piece checks to see if there movement is valid between the tiles. If these conditions are met, these moves are made a copy of the board, then is checked for king in check. This is to prevent chained pieces from moving. If the king is not in check, the board is updated with the move and the turn is changed.
  This design is specifically for the use of Machine Learning. The plan is to utilize this engine to train some AIs to play chess as a proof-of-consept project.
  
 Any ideas or suggestions for the design of either the engine or the AIs, please reach out and I shall respond.
