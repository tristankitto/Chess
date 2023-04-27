package Chess;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 4 : 10;
    }
    
}
