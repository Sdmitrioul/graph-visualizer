package ru.skroba.graph;

import ru.skroba.drawing.DrawingApi;

/* Bridge */
public abstract class Graph {
    private DrawingApi drawingApi;
    
    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }
    
    public abstract void drawGraph();
}
