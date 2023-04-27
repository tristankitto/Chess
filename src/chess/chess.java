package chess;

import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class chess {

    private static void createGUI() throws Exception {
        JFrame frame = new JFrame("Chess");
        Image icon = ImageIO.read(chess.class.getClassLoader().getResource("chessicon.png"));
        frame.setIconImage(icon);
        int width = 8 * 64;
        int height = 8 * 64;
        frame.setSize(width+16,height+39);
        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (white) {
                            g.setColor(Color.white.darker());
                        } else {
                            g.setColor(Color.black);
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
            }
        };
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        createGUI();
    }
}