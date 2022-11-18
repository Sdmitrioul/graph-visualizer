package ru.skroba.graph;

import ru.skroba.api.DrawingApi;

public enum GraphFactories {
    EDGES("edge", (fn, api) -> EdgesGraph.readFromFile(api, fn)), MATRIX("matrix",
            (fn, api) -> MatrixGraph.readFromFile(api, fn));
    
    public final GraphFactory factory;
    private final String name;
    
    GraphFactories(final String name, final GraphFactory factory) {
        this.name = name;
        this.factory = factory;
    }
    
    public String getName() {
        return name;
    }
    
    @FunctionalInterface
    public interface GraphFactory {
        Graph getGraph(String fileName, DrawingApi api);
    }
}
