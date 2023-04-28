package Chess;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 3 : 9;
    }

    public void move(int xp, int yp) {
        if((Chess.whiteTurn && !isWhite || (!Chess.whiteTurn && isWhite))){
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        }
        // Check if the move is within the board
        if (xp < 0 || xp > 7 || yp < 0 || yp > 7) {
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        }

        // Check if the knight is moving in an L shape
        int deltaX = Math.abs(xp - this.xp);
        int deltaY = Math.abs(yp - this.yp);
        if ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)) {
            // Check if the knight is blocked by other pieces
            Piece movePiece = Chess.getPiece(xp * 64, yp * 64);
            if (movePiece != null && movePiece.isWhite == isWhite) {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }else if(movePiece != null){
                movePiece.kill();
            }

            // Update the position of the knight
            this.xp = xp;
            this.yp = yp;
            x = xp * 64;
            y = yp * 64;
            Chess.whiteTurn = !Chess.whiteTurn;
            return;
        }else{
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        }
    }

}
