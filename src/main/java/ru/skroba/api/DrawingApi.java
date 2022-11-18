package ru.skroba.api;

public interface DrawingApi {
    long getDrawingAreaWidth();
    
    long getDrawingAreaHeight();
    
    void drawCircle(long x, long y, long r);
    
    void drawLine(long xFrom, long yFrom, long xTo, long yTo);
    
    void drawNumber(long x, long y, String text);
    
    void showGraph();
}
