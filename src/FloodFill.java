import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.*;

public class FloodFill extends JPanel {
    private BufferedImage image;
    private int width, height;
    private boolean useStack;
    private Stack<Point> stack;
    private Queue<Point> queue;
    private Timer timer;
    private int targetColor, newColor;
    private static final int PIXELS_PER_STEP = 10; // Processa múltiplos pixels por iteração

    public FloodFill(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void floodFillStack(int startX, int startY, int newColorRGB) {
        this.useStack = true;
        this.stack = new Stack<>();
        this.targetColor = image.getRGB(startX, startY);
        this.newColor = newColorRGB;

        if (targetColor != newColor) {
            stack.push(new Point(startX, startY));
        }

        iniciarAnimacao();
    }

    public void floodFillQueue(int startX, int startY, int newColorRGB) {
        this.useStack = false;
        this.queue = new LinkedList<>();
        this.targetColor = image.getRGB(startX, startY);
        this.newColor = newColorRGB;

        if (targetColor != newColor) {
            queue.add(new Point(startX, startY));
        }

        iniciarAnimacao();
    }

    private void iniciarAnimacao() {
        JFrame frame = new JFrame("Flood Fill Visualization");
        frame.add(this);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Timer mais rápido (5ms por iteração) e processando múltiplos pixels por vez
        timer = new Timer(5, e -> processNextSteps());
        timer.start();
    }

    private void processNextSteps() {
        int steps = 0;

        while (steps < PIXELS_PER_STEP) {
            if ((useStack && stack.isEmpty()) || (!useStack && queue.isEmpty())) {
                timer.stop();
                System.out.println("Flood Fill concluído!");
                return;
            }

            Point p = useStack ? stack.pop() : queue.poll();
            int x = p.x;
            int y = p.y;

            if (x < 0 || x >= width || y < 0 || y >= height) continue;
            if (image.getRGB(x, y) != targetColor) continue;

            image.setRGB(x, y, newColor);
            steps++;

            // Adiciona os vizinhos na estrutura correta
            if (useStack) {
                stack.push(new Point(x, y - 1));
                stack.push(new Point(x + 1, y));
                stack.push(new Point(x - 1, y));
                stack.push(new Point(x, y + 1));

            } else {
                queue.add(new Point(x + 1, y));
                queue.add(new Point(x - 1, y));
                queue.add(new Point(x, y + 1));
                queue.add(new Point(x, y - 1));
            }
        }

        repaint();  // Atualiza a interface gráfica após processar múltiplos pixels
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public BufferedImage getImage() {
        return image;
    }
}
