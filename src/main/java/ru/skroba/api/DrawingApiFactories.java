package ru.skroba.api;

public enum DrawingApiFactories {
    AWT_API("awt", AwtDrawingApi::new), FX_API("fx", FxDrawingApi::new);
    
    public final DrawingApiFactory factory;
    private final String name;
    
    DrawingApiFactories(final String name, final DrawingApiFactory factory) {
        this.name = name;
        this.factory = factory;
    }
    
    public String getName() {
        return name;
    }
    
    @FunctionalInterface
    public interface DrawingApiFactory {
        DrawingApi getApi(long height, long width);
    }
}
