package Chess;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(int xp, int yp, boolean isWhite, ArrayList<Piece> ps) {
        super(xp, yp, isWhite, ps);
        super.index = isWhite? 1 : 7;
    }
    
}
