package Chess;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite? 5 : 11;
    }
    
}
