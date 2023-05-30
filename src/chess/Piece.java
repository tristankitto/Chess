package Chess;

import java.util.*;

public abstract class Piece {
    int xp;
    int yp;
    int x;
    int y;
    int index;
    boolean isWhite;
    boolean startingMove;
    boolean enPassant;
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

    public void kill() {
        ps.remove(this);
    }

    public void move(int i, int j) {
        return;
    }

    public boolean canEnPassant() {
        return false;
    }

    public void setEnPassant(boolean enPassant) {
        return;
    }

    public boolean getStartingMove() {
        return false;
    }

    public void castlingMove(int direction) {
        return;
    }

}
