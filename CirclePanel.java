import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CirclePanel extends JPanel {

    boolean isCirclePainted;
    boolean applicationInitialized;
    boolean drawLine;
    int radius = 100;
    int clickedX;
    int clickedY;
    private int x;
    private int y;

    public CirclePanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                clickedX = e.getX();
                clickedY = e.getY();

                boolean isValidCoordinates = false;
                if (clickedX >= 50 &&
                        clickedX <= 250 &&
                        clickedY >= 50 &&
                        clickedY <= 250) {
                    System.out.println("Valid point clickedX " + clickedX + " clickedY " + clickedY);
                    isValidCoordinates = true;
                } else {
                    System.out.println("Invalid point clickedX " + clickedX + " clickedY " + clickedY);
                }
                if (!isCirclePainted) {
                    if(isValidCoordinates) {
                        applicationInitialized = true;
                        x = clickedX;
                        y = clickedY;
                        repaint();
                        JOptionPane.showMessageDialog(null, "Click the mouse anywhere in the JPanel");
                        isCirclePainted = true;
                    }else{
                        isCirclePainted = false;
                        JOptionPane.showMessageDialog(null, "Invalid coordinates selected for circle, Please try again.");
                    }
                } else {
                    paintComponent(getGraphics());
                    if (Math.abs(x - clickedX) <= 50 &&
                            Math.abs(y - clickedY) <= 50) {
                        JOptionPane.showMessageDialog(null, "Clicked inside circle");
                    } else {
                        JOptionPane.showMessageDialog(null, "Clicked outside circle");
                    }
                    JOptionPane.showMessageDialog(null, "Define the origin by clicking the mouse on the JPanel");
                    isCirclePainted = false;
                }
            }
        });
    }

    public static void main(String[] args) {
        CirclePanel applicationPanel = new CirclePanel();
        Dimension dimension = new Dimension(300, 300);
        applicationPanel.setPreferredSize(dimension);
        JFrame frame = new JFrame("ApplicationPanel");
        frame.setContentPane(applicationPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null, "Define the origin by clicking the mouse on the JPanel");

    }

    @Override
    public void paint(Graphics g) {
        if (applicationInitialized) {
            g.setColor(Color.YELLOW);
            System.out.println("Drawing circle at x " + x + " y " + y);
            g.fillOval(x - (radius / 2), y - (radius / 2), radius, radius);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.printf("Drawing line xStart %d, yStart %d, xEnd %d, yEnd %d", x, y, clickedX, clickedY);
        g.drawLine(x, y, clickedX, clickedY);
    }
}
