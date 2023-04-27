package Chess;

import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 2 : 8;
    }
    
}
