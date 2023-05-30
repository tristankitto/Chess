package Chess;

import java.util.ArrayList;

public class King extends Piece {

    public boolean startingMove = true;

    public King(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 0 : 6;
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

        // Check if the move is within one square of the current position
        int deltaX = Math.abs(this.xp - xp);
        int deltaY = Math.abs(this.yp - yp);
        if (deltaX == 2 && deltaY == 0 && startingMove) {
            // left is 1, right is -1
            int direction = (this.xp - xp) / 2;
            System.out.println(direction);
            Piece rook;
            if (direction == 1) {
                rook = Chess.getPiece(0, yp * 64);
            } else {
                System.out.println("here");
                rook = Chess.getPiece(7 * 64, yp * 64);
            }
            int distance = (direction == 1) ? 3 : 2;
            boolean pathClear = true;
            for (int i = 1; i <= distance; i++) {
                Piece blockingPiece = Chess.getPiece((this.xp - direction * i) * 64, this.yp * 64);
                if (blockingPiece != null) {
                    pathClear = false;
                    break;
                }
            }
            if (rook != null && rook instanceof Rook && rook.getStartingMove() && pathClear) {
                this.xp = xp;
                this.yp = yp;
                x = xp * 64;
                y = yp * 64;
                Chess.whiteTurn = !Chess.whiteTurn;
                startingMove = false;
                rook.castlingMove(direction);
                return;
            } else {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }

        }
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
        } else if (movePiece != null) {
            movePiece.kill();
        }

        // Update the position of the king
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        Chess.whiteTurn = !Chess.whiteTurn;
        startingMove = false;
    }

}
