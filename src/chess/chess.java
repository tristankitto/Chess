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
                    g.drawImage(pieceImages[p.index], p.x, p.y, this);
                }
                if(selectedPiece != null){
                    g.drawImage(pieceImages[selectedPiece.index], selectedPiece.x, selectedPiece.y, this);
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
                // System.out.println(getPiece(e.getX(), e.getY()).name);
                selectedPiece = getPiece(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedPiece != null) {
                    selectedPiece.move(e.getX() / 64, e.getY() / 64);
                    frame.repaint();
                }
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
                if (selectedPiece != null) {
                    selectedPiece.x = e.getX() - 38;
                    selectedPiece.y = e.getY() - 64;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private static void drawPieces() throws Exception {
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

        new Rook(0, 0, false, ps);
        new Knight(1, 0, false, ps);
        new Bishop(2, 0, false, ps);
        new Queen(3, 0, false, ps);
        new King(4, 0, false, ps);
        new Bishop(5, 0, false, ps);
        new Knight(6, 0, false, ps);
        new Rook(7, 0, false, ps);
        for (int p = 0; p < 8; p++) {
            new Pawn(p, 1, false, ps);
        }

        new Rook(0, 7, true, ps);
        new Knight(1, 7, true, ps);
        new Bishop(2, 7, true, ps);
        new Queen(3, 7, true, ps);
        new King(4, 7, true, ps);
        new Bishop(5, 7, true, ps);
        new Knight(6, 7, true, ps);
        new Rook(7, 7, true, ps);
        for (int p = 0; p < 8; p++) {
            new Pawn(p, 6, true, ps);
        }
    }

    public static Piece getPiece(int x, int y) {
        int xp = x / 64;
        int yp = y / 64;
        for (Piece p : ps) {
            if (p.xp == xp && p.yp == yp) {
                return p;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        createGUI();
    }
}