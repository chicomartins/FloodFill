import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class FloodFill {
    private BufferedImage image;
    private int width, height;

    public FloodFill(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void floodFillStack(int startX, int startY, int newColorRGB) {
        int targetColor = image.getRGB(startX, startY);
        if (targetColor == newColorRGB) return;

        Pilha<Point> stack = new Pilha<>();
        stack.push(new Point(startX, startY));

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            int x = p.x;
            int y = p.y;

            if (x < 0 || x >= width || y < 0 || y >= height) continue;

            if (image.getRGB(x, y) != targetColor) continue;

            image.setRGB(x, y, newColorRGB);

            stack.push(new Point(x + 1, y));
            stack.push(new Point(x - 1, y));
            stack.push(new Point(x, y + 1));
            stack.push(new Point(x, y - 1));
        }
    }

    public void floodFillQueue(int startX, int startY, int newColorRGB) {
        int targetColor = image.getRGB(startX, startY);
        if (targetColor == newColorRGB) return;

        Fila<Point> queue = new Fila<>();
        queue.enqueue(new Point(startX, startY));

        while (!queue.isEmpty()) {
            Point p = queue.dequeue();
            int x = p.x;
            int y = p.y;

            if (x < 0 || x >= width || y < 0 || y >= height) continue;
            if (image.getRGB(x, y) != targetColor) continue;

            image.setRGB(x, y, newColorRGB);

            queue.enqueue(new Point(x + 1, y));
            queue.enqueue(new Point(x - 1, y));
            queue.enqueue(new Point(x, y + 1));
            queue.enqueue(new Point(x, y - 1));
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
