package ru.skroba.api;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

public class AwtDrawingApi extends Frame implements DrawingApi {
    private final List<Shape> edges = new LinkedList<>();
    private final List<Shape> nodes = new LinkedList<>();
    private final List<Text> names = new LinkedList<>();
    private final long height;
    private final long width;
    
    public AwtDrawingApi(final long height, final long width) throws HeadlessException {
        this.height = height;
        this.width = width;
    }
    
    @Override
    public void paint(final Graphics g) {
        Graphics2D area = (Graphics2D) g;
        
        area.setPaint(Color.BLACK);
        
        for (Shape shape : edges) {
            area.draw(shape);
        }
        
        for (Shape shape : nodes) {
            area.draw(shape);
            area.setPaint(Color.YELLOW);
            area.fill(shape);
            area.setPaint(Color.BLACK);
        }
        
        area.setPaint(Color.BLACK);
        
        int[] widths = area.getFontMetrics()
                .getWidths();
        
        for (Text text : names) {
            area.drawString(text.name, text.x - (width(text.name, widths) >> 1), text.y + (area.getFontMetrics()
                    .getHeight() >> 1));
        }
    }
    
    private static int width(String str, int[] widths) {
        int sum = 0;
        for (char c : str.toCharArray()) {
            sum += widths[c];
        }
        return sum;
    }
    
    private record Text(String name, long x, long y) {
    }
    
    @Override
    public long getDrawingAreaWidth() {
        return width;
    }
    
    @Override
    public long getDrawingAreaHeight() {
        return height;
    }
    
    @Override
    public void drawCircle(final long x, final long y, final long r) {
        nodes.add(new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r));
    }
    
    @Override
    public void drawLine(final long xFrom, final long yFrom, final long xTo, final long yTo) {
        edges.add(new Line2D.Float(xFrom, yFrom, xTo, yTo));
    }
    
    @Override
    public void drawNumber(final long x, final long y, final String text) {
        names.add(new Text(text, x, y));
    }
    
    @Override
    public void showGraph() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        this.setTitle("Graph drawer");
        this.setSize((int) getDrawingAreaWidth(), (int) getDrawingAreaHeight());
        this.setVisible(true);
    }
}
