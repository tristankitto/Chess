package Chess;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 1 : 7;
    }

    public void move(int xp, int yp) {
        if ((Chess.whiteTurn && !isWhite || (!Chess.whiteTurn && isWhite))) {
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

        // Check if the move is diagonal or horizontal/vertical
        int deltaX = Math.abs(this.xp - xp);
        int deltaY = Math.abs(this.yp - yp);
        if (deltaX != deltaY && deltaX != 0 && deltaY != 0) {
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        }

        // Check if any pieces are blocking the queen's path
        int xDirection = Integer.signum(xp - this.xp);
        int yDirection = Integer.signum(yp - this.yp);
        int checkX = this.xp + xDirection;
        int checkY = this.yp + yDirection;
        while (checkX != xp || checkY != yp) {
            Piece blockingPiece = Chess.getPiece(checkX * 64, checkY * 64);
            if (blockingPiece != null) {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
            checkX += xDirection;
            checkY += yDirection;
        }

        // Check if the destination square is not occupied by a piece of the same color
        Piece movePiece = Chess.getPiece(xp * 64, yp * 64);
        if (movePiece != null && movePiece.isWhite == this.isWhite) {
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        } else if (movePiece != null) {
            movePiece.kill();
        }

        // Update the position of the queen
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        Chess.whiteTurn = !Chess.whiteTurn;
    }

}
