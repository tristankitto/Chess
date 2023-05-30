package Chess;

import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean startingMove = true;
    public boolean enPassant = false;

    public Pawn(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 5 : 11;
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

        // Check if the pawn is moving forward
        int direction = isWhite ? -1 : 1;
        if (deltaX == 0 && deltaY == direction && movePiece == null) {
            // Pawn is moving one space forward
            this.xp = xp;
            this.yp = yp;
            x = xp * 64;
            y = yp * 64;
            startingMove = false;
            enPassant = false;
            Chess.whiteTurn = !Chess.whiteTurn;
        } else if (deltaX == 0 && deltaY == 2 * direction && startingMove
                && movePiece == null) {
            // Pawn is moving two spaces forward on its first move
            boolean pathClear = true;
            for (int i = 1; i <= 1; i++) {
                Piece blockingPiece = Chess.getPiece(xp * 64, (yp - direction * i) * 64);
                if (blockingPiece != null) {
                    pathClear = false;
                    break;
                }
            }
            if (pathClear) {
                this.xp = xp;
                this.yp = yp;
                x = xp * 64;
                y = yp * 64;
                startingMove = false;
                enPassant = true;
                Chess.whiteTurn = !Chess.whiteTurn;
            } else {
                // There is a piece blocking the pawn's path
                x = this.xp * 64;
                y = this.yp * 64;
            }
        } else if ((deltaX == 1 || deltaX == -1) && deltaY == direction && movePiece != null
                && movePiece.isWhite != isWhite) {
            // Pawn is taking a piece diagonally
            this.xp = xp;
            this.yp = yp;
            x = xp * 64;
            y = yp * 64;
            startingMove = false;
            enPassant = false;
            Chess.whiteTurn = !Chess.whiteTurn;
            movePiece.kill();
        } else if ((deltaX == 1 || deltaX == -1) && deltaY == direction && movePiece == null
                && Chess.getPiece(xp * 64, (yp - direction) * 64) != null
                && Chess.getPiece(xp * 64, (yp - direction) * 64) instanceof Pawn
                && Chess.getPiece(xp * 64, (yp - direction) * 64).canEnPassant()
                && Chess.getPiece(xp * 64, (yp - direction) * 64).isWhite != isWhite) {
            this.xp = xp;
            this.yp = yp;
            x = xp * 64;
            y = yp * 64;
            startingMove = false;
            enPassant = false;
            Chess.whiteTurn = !Chess.whiteTurn;
            Chess.getPiece(xp * 64, (yp - direction) * 64).kill();
        } else {
            // Invalid move, return the pawn to its original position
            x = this.xp * 64;
            y = this.yp * 64;
        }
    }

    public boolean canEnPassant() {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

}
