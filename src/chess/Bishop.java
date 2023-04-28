package Chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 2 : 8;
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

        // Check if the bishop is moving diagonally
        int deltaX = xp - this.xp;
        int deltaY = yp - this.yp;
        if (Math.abs(deltaX) != Math.abs(deltaY)) {
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        }

        Piece movePiece = Chess.getPiece(xp * 64, yp * 64);

        // Check if there are any pieces blocking the movement
        int directionX = deltaX > 0 ? 1 : -1;
        int directionY = deltaY > 0 ? 1 : -1;
        int distance = Math.abs(deltaX);
        for (int i = 1; i < distance; i++) {
            int checkX = this.xp + i * directionX;
            int checkY = this.yp + i * directionY;
            Piece blockingPiece = Chess.getPiece(checkX * 64, checkY * 64);
            if (blockingPiece != null) {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
        }

        if (movePiece != null) {
            movePiece.kill();
        }

        // Update the position of the bishop
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        Chess.whiteTurn = !Chess.whiteTurn;
    }

    public List<Point> getValidMoves() {
        List<Point> validMoves = new ArrayList<Point>();

        // Check moves in all four directions
        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dy = -1; dy <= 1; dy += 2) {
                if (dx != 0 && dy != 0) {
                    // Not a valid diagonal move, skip it
                    continue;
                }
                for (int i = 1; i < 8; i++) {
                    int x = xp + i * dx;
                    int y = yp + i * dy;
                    if (x < 0 || x > 7 || y < 0 || y > 7) {
                        // Move is off the board, stop checking in this direction
                        break;
                    }
                    Piece piece = Chess.getPiece(x * 64, y * 64);
                    if (piece == null) {
                        // Empty square, this move is valid
                        validMoves.add(new Point(x, y));
                    } else if (piece.isWhite != isWhite) {
                        // Can take opponent's piece, this move is valid
                        validMoves.add(new Point(x, y));
                        break;
                    } else {
                        // Own piece in the way, stop checking in this direction
                        break;
                    }
                }
            }
        }

        return validMoves;
    }

}
