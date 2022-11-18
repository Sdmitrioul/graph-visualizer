package ru.skroba.api;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class FxDrawingApi extends Application implements DrawingApi {
    private static final List<Edge> edges = new LinkedList<>();
    private static final List<Text> texts = new LinkedList<>();
    private static final List<Node> nodes = new LinkedList<>();
    private static long height;
    private static long width;
    
    public FxDrawingApi() {
        this(600, 600);
    }
    
    public FxDrawingApi(final long height, final long width) {
        FxDrawingApi.height = height;
        FxDrawingApi.width = width;
    }
    
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Graph drawer");
        
        Group root = new Group();
        Canvas canvas = new Canvas(getDrawingAreaWidth(), getDrawingAreaHeight());
        
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        
        graphicsContext.setFill(Color.BLACK);
        
        for (Node node : nodes) {
            graphicsContext.fillOval(node.x - node.r, node.y - node.r, 2 * node.r, 2 * node.r);
        }
        
        for (Edge edge : edges) {
            graphicsContext.strokeLine(edge.xFrom, edge.yFrom, edge.xTo, edge.yTo);
        }
        
        graphicsContext.setFill(Color.WHITE);
        
        for (Text text : texts) {
            double size = graphicsContext.getFont()
                    .getSize() / 2;
            graphicsContext.fillText(text.name, text.x - size * text.name.length(), text.y + size);
        }
        
        root.getChildren()
                .add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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
        nodes.add(new Node(x, y, r));
    }
    
    @Override
    public void drawLine(final long xFrom, final long yFrom, final long xTo, final long yTo) {
        edges.add(new Edge(xFrom, yFrom, xTo, yTo));
    }
    
    @Override
    public void drawNumber(final long x, final long y, final String text) {
        texts.add(new Text(text, x, y));
    }
    
    @Override
    public void showGraph() {
        launch();
    }
    
    private record Node(long x, long y, long r) {
    }
    
    private record Text(String name, long x, long y) {
    }
    
    private record Edge(long xFrom, long yFrom, long xTo, long yTo) {
    }
}
