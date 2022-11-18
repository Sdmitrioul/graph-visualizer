package ru.skroba;

import ru.skroba.api.DrawingApiFactories;
import ru.skroba.graph.Graph;
import ru.skroba.graph.GraphFactories;

import java.util.Arrays;
import java.util.Optional;

public class Visualizer {
    private static final long DEFAULT_HEIGHT = 600;
    private static final long DEFAULT_WIDTH = 600;
    
    public static void main(String[] args) {
        checkArguments(args != null && args.length >= 3 && args.length <= 5);
        
        Optional<DrawingApiFactories> mApi = Arrays.stream(DrawingApiFactories.values())
                .filter(it -> it.getName()
                        .equals(args[0]))
                .findFirst();
        
        checkArguments(mApi.isPresent());
        
        DrawingApiFactories apiFactory = mApi.get();
        
        int i = 1;
        long height = DEFAULT_HEIGHT;
        long width = DEFAULT_WIDTH;
        
        if (args.length == 4) {
            height = Long.parseLong(args[1]);
            i++;
        }
        
        if (args.length == 5) {
            width = Long.parseLong(args[2]);
            i++;
        }
        
        final int index = i;
        
        Optional<GraphFactories> graphFactory = Arrays.stream(GraphFactories.values())
                .filter(it -> it.getName()
                        .equals(args[index]))
                .findFirst();
        
        checkArguments(graphFactory.isPresent());
        
        Graph graph = graphFactory.get().factory.getGraph(args[index + 1], apiFactory.factory.getApi(height, width));
        
        graph.drawGraph();
    }
    
    private static void checkArguments(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException("Wrong arguments: <api> [<height>] [<width>] <graphType> <fileName>!");
        }
    }
}
