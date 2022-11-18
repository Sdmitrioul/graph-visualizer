package ru.skroba.graph;

import javafx.util.Pair;
import ru.skroba.api.DrawingApi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class EdgesGraph extends Graph {
    private final List<Pair<Integer, Integer>> edges;
    private final String[] names;
    private final int size;
    
    public EdgesGraph(final DrawingApi drawingApi, final List<Pair<Integer, Integer>> edges, final int size) {
        super(drawingApi);
        this.edges = edges;
        this.names = new String[size];
        this.size = size;
        
        IntStream.range(0, size)
                .forEach(it -> names[it] = Integer.toString(it));
    }
    
    public EdgesGraph(final DrawingApi drawingApi, final List<Pair<Integer, Integer>> edges, final String[] names,
                      final int size) {
        super(drawingApi);
        this.edges = edges;
        this.names = names;
        this.size = size;
    }
    
    public static EdgesGraph readFromFile(final DrawingApi drawingApi, final String name) {
        try (final BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(name), StandardCharsets.UTF_8))) {
            String input = reader.readLine();
            
            checkInput(input != null);
            
            assert input != null;
            String[] digits = input.split(" ");
            
            checkInput(digits.length == 2);
            
            final int size = Integer.parseInt(digits[0]);
            final int edgesCount = Integer.parseInt(digits[1]);
            
            final List<Pair<Integer, Integer>> edges = new LinkedList<>();
            
            for (int i = 0; i < edgesCount; i++) {
                input = reader.readLine();
                
                checkInput(input != null);
                
                digits = input.split(" ");
                
                checkInput(digits.length == 2);
                
                edges.add(new Pair<>(Integer.parseInt(digits[0]), Integer.parseInt(digits[1])));
            }
            
            return new EdgesGraph(drawingApi, edges, size);
        } catch (FileNotFoundException e) {
            System.err.println("Can't find input file: " + name);
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error while reading file: " + name);
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected int countOfNodes() {
        return size;
    }
    
    @Override
    protected List<Pair<Integer, Integer>> getEdges() {
        return edges;
    }
    
    @Override
    protected String[] nodesNames() {
        return names;
    }
}
