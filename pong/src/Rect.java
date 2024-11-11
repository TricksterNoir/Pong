import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Rect {
    public double x, y, width, height;
    public Color color;

    public Rect(double x, double y, double width, double height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.fill(new RoundRectangle2D.Double(x, y, width, height,15,15));
    }
}
