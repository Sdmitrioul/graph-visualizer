# Graph Visualizer

Repository was created for Software Design course in ITMO (CT) university by
Skroba Dmitriy.

### Purpose:

Gain practical experience in using bridge structural pattern.

### What`s has been done:

1. Created Visualization [Api](./src/main/java/ru/skroba/api/DrawingApi.java)
   for drawing in using `java.awt` library or `JavaFX`.
2. Implementations of visualization
   api [AWT](./src/main/java/ru/skroba/api/AwtDrawingApi.java)
   and [JavaFX](./src/main/java/ru/skroba/api/FxDrawingApi.java).
3. [Created abstract class](./src/main/java/ru/skroba/graph/Graph.java) - bridge
   for different implementation graph and visualizer.
4. Extend bridge for [Edges](./src/main/java/ru/skroba/graph/EdgesGraph.java)
   graph and [Matrix](./src/main/java/ru/skroba/graph/MatrixGraph.java) graph
   storaging.

### Run application:

* Clone repository.
* Go to project root directory.
* Run `./gradlew run --args="<api> [<height>] [<width>] <graphType> <fileName>"`
    * You can choose api between: `awt`   or `fx`.
    * Height and Width of Scene is optional parameters.
    * You can choose graph type between: `edge` or `matrix`.
* You can see some examples of two types of graph [here](src/main/resources).