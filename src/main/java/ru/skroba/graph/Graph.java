package ru.skroba.graph;

import javafx.util.Pair;
import ru.skroba.api.DrawingApi;

import java.util.List;

/* Bridge */
public abstract class Graph {
    private static final int DEFAULT_MARGIN = 50;
    private static final int DEFAULT_NODE_RADIUS = 20;
    
    private final DrawingApi drawingApi;
    
    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }
    
    protected static void checkInput(boolean condition) {
        if (!condition) {
            System.err.println("Wrong format of input file");
            throw new RuntimeException();
        }
    }
    
    public void drawGraph() {
        final long height = drawingApi.getDrawingAreaHeight();
        final long width = drawingApi.getDrawingAreaWidth();
        
        final long centerX = width / 2;
        final long centerY = height / 2;
        
        final long defaultRadius = (Math.min(centerX, centerY) - DEFAULT_MARGIN) * 3 / 4;
        
        final double phi = 2 * Math.PI / countOfNodes();
        
        for (int i = 0; i < countOfNodes(); i++) {
            drawingApi.drawCircle((long) (centerX + defaultRadius * Math.cos(i * phi)),
                    (long) (centerY + defaultRadius * Math.sin(i * phi)), DEFAULT_NODE_RADIUS);
        }
        
        for (Pair<Integer, Integer> pair : getEdges()) {
            int from = pair.getKey();
            int to = pair.getValue();
            
            long fromX = (long) (centerX + (defaultRadius - DEFAULT_NODE_RADIUS) * Math.cos(from * phi));
            long fromY = (long) (centerY + (defaultRadius - DEFAULT_NODE_RADIUS) * Math.sin(from * phi));
            long toX = (long) (centerX + (defaultRadius - DEFAULT_NODE_RADIUS) * Math.cos(to * phi));
            long toY = (long) (centerY + (defaultRadius - DEFAULT_NODE_RADIUS) * Math.sin(to * phi));
            
            drawingApi.drawLine(fromX, fromY, toX, toY);
        }
        
        final String[] nodesNames = nodesNames();
        for (int i = 0; i < nodesNames.length; i++) {
            final String nodeName = nodesNames[i];
            
            long fromX = (long) (centerX + defaultRadius * Math.cos(i * phi));
            long fromY = (long) (centerY + defaultRadius * Math.sin(i * phi));
            
            drawingApi.drawNumber(fromX, fromY, nodeName);
        }
        
        drawingApi.showGraph();
    }
    
    protected abstract int countOfNodes();
    
    protected abstract List<Pair<Integer, Integer>> getEdges();
    
    protected abstract String[] nodesNames();
}
