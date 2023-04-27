package Chess;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Chess {

    protected static ArrayList<Piece> ps = new ArrayList<Piece>();
    protected static Image pieceImages[];
    protected static Piece[] pieces;
    protected static Piece selectedPiece;

    private static void createGUI() throws Exception {
        JFrame frame = new JFrame("Chess");
        Image icon = ImageIO.read(Chess.class.getClassLoader().getResource("chessicon.png"));
        frame.setIconImage(icon);
        int width = 8 * 64;
        int height = 8 * 64;
        frame.setSize(width + 16, height + 39);

        drawPieces();

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

                    g.drawImage(pieceImages[pieceIndex], p.x, p.y, this);
                }
            }
        };
        frame.add(panel);

        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println(getPiece(e.getX(), e.getY()).name);
                selectedPiece = getPiece(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX()/64, e.getY()/64);
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });

        frame.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece != null){
                    selectedPiece.x = e.getX()-38;
                    selectedPiece.y = e.getY()-64;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);frame.setVisible(true);

    }

    private static void drawPieces() throws Exception{
        BufferedImage allPieces = ImageIO.read(Chess.class.getClassLoader().getResource("chessPieces.png"));
        pieceImages = new Image[12];
        int i = 0;

        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                pieceImages[i] = allPieces.getSubimage(x, y, 200, 200).getScaledInstance(64, 64,
                        BufferedImage.SCALE_SMOOTH);
                i++;
            }
        }

        Piece bRook1 = new Piece(0,0,false, "rook", ps);
        Piece bKnight1 = new Piece(1,0,false,"knight", ps);
        Piece bBishop1 = new Piece(2,0,false,"bishop",ps);
        Piece bQueen= new Piece(3,0,false,"queen",ps);
        Piece bKing = new Piece(4,0,false,"king",ps);
        Piece bBishop2 = new Piece(5,0,false,"bishop",ps);
        Piece bKnight2= new Piece(6,0,false,"knight",ps);
        Piece bRook2 = new Piece(7,0,false,"rook",ps);
        Piece bPawn1 = new Piece(0,1,false,"pawn",ps);
        Piece bPawn2 = new Piece(1,1,false,"pawn",ps);
        Piece bPawn3 = new Piece(2,1,false,"pawn",ps);
        Piece bPawn4 = new Piece(3,1,false,"pawn",ps);
        Piece bPawn5 = new Piece(4,1,false,"pawn",ps);
        Piece bPawn6 = new Piece(5,1,false,"pawn",ps);
        Piece bPawn7 = new Piece(6,1,false,"pawn",ps);
        Piece bPawn8 = new Piece(7,1,false,"pawn",ps);

        Piece wRook1 = new Piece(0,7,true, "rook", ps);
        Piece wKnight1 = new Piece(1,7,true,"knight", ps);
        Piece wBishop1 = new Piece(2,7,true,"bishop",ps);
        Piece wQueen= new Piece(3,7,true,"queen",ps);
        Piece wKing = new Piece(4,7,true,"king",ps);
        Piece wBishop2 = new Piece(5,7,true,"bishop",ps);
        Piece wKnight2= new Piece(6,7,true,"knight",ps);
        Piece wRook2 = new Piece(7,7,true,"rook",ps);
        Piece wPawn1 = new Piece(0,6,true,"pawn",ps);
        Piece wPawn2 = new Piece(1,6,true,"pawn",ps);
        Piece wPawn3 = new Piece(2,6,true,"pawn",ps);
        Piece wPawn4 = new Piece(3,6,true,"pawn",ps);
        Piece wPawn5 = new Piece(4,6,true,"pawn",ps);
        Piece wPawn6 = new Piece(5,6,true,"pawn",ps);
        Piece wPawn7 = new Piece(6,6,true,"pawn",ps);
        Piece wPawn8 = new Piece(7,6,true,"pawn",ps);
    }

    public static Piece getPiece(int x, int y){
        int xp = x/64;
        int yp = y/64;
        for(Piece p: ps){
            if(p.xp == xp && p.yp == yp){
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        createGUI();
    }
}