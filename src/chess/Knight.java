package Chess;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite ? 3 : 9;
    }
    
}
