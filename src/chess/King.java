package Chess;

import java.util.ArrayList;

public class King extends Piece{

    public King(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 0 : 6;
    }
    
}
