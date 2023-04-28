package Chess;

import java.util.ArrayList;

public class King extends Piece{

    public King(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 0 : 6;
    }

    public void move(int xp, int yp) {
        if((Chess.whiteTurn && !isWhite || (!Chess.whiteTurn && isWhite))){
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        }

        // Check if the move is within one square of the current position
        int deltaX = Math.abs(this.xp - xp);
        int deltaY = Math.abs(this.yp - yp);
        if (deltaX > 1 || deltaY > 1) {
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        }
    
        // Check if the destination square is not occupied by a piece of the same color
        Piece movePiece = Chess.getPiece(xp * 64, yp * 64);
        if (movePiece != null && movePiece.isWhite == this.isWhite) {
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        }else if(movePiece != null){
            movePiece.kill();
        }
    
        // Update the position of the king
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        Chess.whiteTurn = !Chess.whiteTurn;
    }
    
    
}
