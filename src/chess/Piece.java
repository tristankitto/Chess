package Chess;

import java.util.*;

public class Piece {
    int xp;
    int yp;
    int x;
    int y;
    boolean isWhite;
    ArrayList<Piece> ps;
    String name;

    public Piece(int xp, int yp, boolean isWhite, String name, ArrayList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        this.isWhite = isWhite;
        this.name = name;
        this.ps = ps;
        ps.add(this);
    }

    public void move(int xp, int yp) {
            this.xp = xp;
            this.yp = yp;
            x = xp * 64;
            y = yp * 64;
    }

    public void kill() {
        ps.remove(this);
    }
}
