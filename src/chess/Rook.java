package Chess;

import java.util.ArrayList;

public class Rook extends Piece {

    public boolean startingMove = true;

    public Rook(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 4 : 10;
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

        int deltaX = xp - this.xp;
        int deltaY = yp - this.yp;

        Piece movePiece = Chess.getPiece(xp * 64, yp * 64);

        // Check if the rook is moving horizontally or vertically
        if ((deltaX == 0 && deltaY != 0) || (deltaY == 0 && deltaX != 0)) {
            int stepX = deltaX == 0 ? 0 : (deltaX > 0 ? 1 : -1);
            int stepY = deltaY == 0 ? 0 : (deltaY > 0 ? 1 : -1);

            // Check if there are any pieces blocking the rook's path
            int currentX = this.xp + stepX;
            int currentY = this.yp + stepY;
            while (currentX != xp || currentY != yp) {
                Piece blockingPiece = Chess.getPiece(currentX * 64, currentY * 64);
                if (blockingPiece != null) {
                    // There is a piece blocking the rook's path, return the rook to its original
                    // position
                    x = this.xp * 64;
                    y = this.yp * 64;
                    return;
                }
                currentX += stepX;
                currentY += stepY;
            }

            if (movePiece != null && movePiece.isWhite == isWhite) {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            } else if (movePiece != null) {
                movePiece.kill();
            }

            // Move the rook to the target square
            this.xp = xp;
            this.yp = yp;
            x = xp * 64;
            y = yp * 64;
            Chess.whiteTurn = !Chess.whiteTurn;
        } else {
            // Invalid move, return the rook to its original position
            x = this.xp * 64;
            y = this.yp * 64;
        }
    }

    public boolean getStartingMove() {
        return startingMove;
    }

    public void castlingMove(int direction) {
        if (direction == 1) {
            this.xp = 3;
            x = xp * 64;
        } else {
            this.xp = 5;
            x = xp * 64;
        }
        startingMove = false;
    }

}
