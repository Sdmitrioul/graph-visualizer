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

public class MatrixGraph extends Graph {
    private final boolean[][] matrix;
    private final String[] names;
    private List<Pair<Integer, Integer>> edges = null;
    
    public MatrixGraph(final DrawingApi drawingApi, final boolean[][] matrix) {
        super(drawingApi);
        this.matrix = matrix;
        this.names = new String[matrix.length];
        
        IntStream.range(0, matrix.length)
                .forEach(it -> names[it] = Integer.toString(it));
    }
    
    public MatrixGraph(final DrawingApi drawingApi, final boolean[][] matrix, final String[] names) {
        super(drawingApi);
        this.matrix = matrix;
        this.names = names;
    }
    
    public static MatrixGraph readFromFile(final DrawingApi drawingApi, final String name) {
        try (final BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(name), StandardCharsets.UTF_8))) {
            String input = reader.readLine();
            String[] parts = (input == null ? "" : input).split(" ");
            
            boolean[][] matrix = new boolean[parts.length][parts.length];
            
            for (int i = 0; i < parts.length; i++) {
                if (i != 0) {
                    input = reader.readLine();
                    parts = (input == null ? "" : input).split(" ");
                }
                
                checkInput(input != null && parts.length == matrix.length);
                
                for (int j = 0; j < parts.length; j++) {
                    matrix[i][j] = parts[j].equals("1");
                }
            }
            
            return new MatrixGraph(drawingApi, matrix);
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
        return matrix.length;
    }
    
    @Override
    protected List<Pair<Integer, Integer>> getEdges() {
        if (edges == null) {
            final List<Pair<Integer, Integer>> edges = new LinkedList<>();
            
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j]) {
                        edges.add(new Pair<>(i, j));
                    }
                }
            }
            
            this.edges = edges;
        }
        
        return edges;
    }
    
    @Override
    protected String[] nodesNames() {
        return names;
    }
}
