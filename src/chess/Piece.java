package Chess;

import java.util.*;

public class Piece {
    int xp;
    int yp;
    int x;
    int y;
    int index;
    boolean isWhite;
    ArrayList<Piece> ps;

    public Piece(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        this.isWhite = isWhite;
        this.ps = ps;
        ps.add(this);
    }

    public void move(int xp, int yp) {
        Piece movePiece = Chess.getPiece(xp * 64, yp * 64);
        if (movePiece != null) {
            if (movePiece.isWhite != isWhite) {
                movePiece.kill();
            } else {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
        } else if (xp > 7 || yp > 7) {
            x = this.xp * 64;
            y = this.yp * 64;
            return;
        }
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
    }

    public void kill() {
        ps.remove(this);
    }
}
