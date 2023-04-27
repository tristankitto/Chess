package Chess;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Chess {

    private static LinkedList<Piece> ps;
    private static Image pieceImages[];


    private static void createGUI() throws Exception {
        JFrame frame = new JFrame("Chess");
        Image icon = ImageIO.read(Chess.class.getClassLoader().getResource("chessicon.png"));
        frame.setIconImage(icon);
        int width = 8 * 64;
        int height = 8 * 64;
        frame.setSize(width + 16, height + 39);

        ps = new LinkedList<Piece>();
        pieceImages = drawPieces(ps);

        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (white) {
                            g.setColor(Color.white.darker());
                        } else {
                            g.setColor(Color.darkGray);
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                for (Piece p : ps) {
                    int pieceIndex = 0;
                    switch (p.name.toLowerCase()) {
                        case "king":
                            pieceIndex = 0;
                            break;
                        case "queen":
                            pieceIndex = 1;
                            break;
                        case "bishop":
                            pieceIndex = 2;
                            break;
                        case "knight":
                            pieceIndex = 3;
                            break;
                        case "rook":
                            pieceIndex = 4;
                            break;
                        case "pawn":
                            pieceIndex = 5;
                            break;
                    }
                    if (!p.isWhite) {
                        pieceIndex += 6;
                    }

                    g.drawImage(pieceImages[pieceIndex], p.xp * 64, p.yp * 64, this);
                }
            }
        };
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static Image[] drawPieces(LinkedList<Piece> ps) throws Exception{
        BufferedImage allPieces = ImageIO.read(Chess.class.getClassLoader().getResource("chessPieces.png"));
        Image pieceImages[] = new Image[12];
        int i = 0;

        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                pieceImages[i] = allPieces.getSubimage(x, y, 200, 200).getScaledInstance(64, 64,
                        BufferedImage.SCALE_SMOOTH);
                i++;
            }
        }

        ps.add(new Piece(0,0,false, "rook", ps));
        ps.add(new Piece(1,0,false,"knight", ps));
        ps.add(new Piece(2,0,false,"bishop",ps));
        ps.add(new Piece(3,0,false,"queen",ps));
        ps.add(new Piece(4,0,false,"king",ps));
        ps.add(new Piece(5,0,false,"bishop",ps));
        ps.add(new Piece(6,0,false,"knight",ps));
        ps.add(new Piece(7,0,false,"rook",ps));
        for(int p = 0; p<8; p++){
            ps.add(new Piece(p,1,false,"pawn",ps));
        }

        ps.add(new Piece(0,7,true, "rook", ps));
        ps.add(new Piece(1,7,true,"knight", ps));
        ps.add(new Piece(2,7,true,"bishop",ps));
        ps.add(new Piece(3,7,true,"queen",ps));
        ps.add(new Piece(4,7,true,"king",ps));
        ps.add(new Piece(5,7,true,"bishop",ps));
        ps.add(new Piece(6,7,true,"knight",ps));
        ps.add(new Piece(7,7,true,"rook",ps));
        for(int p = 0; p<8; p++){
            ps.add(new Piece(p,6,true,"pawn",ps));
        }

        return pieceImages;
    }

    public static void main(String[] args) throws Exception {
        createGUI();
    }
}