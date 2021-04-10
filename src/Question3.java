interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {

    }
}

class Triangle implements Shape {
    @Override
    public void draw() {

    }
}

class Square implements Shape {
    @Override
    public void draw() {

    }
}

class Graph {
    Shape circle;
    Shape triangle;
    Shape square;

    Graph() {

    }
    public Shape getCircle() {
        return circle;
    }

    public void setCircle(Shape circle) {
        this.circle = circle;
    }

    public Shape getTriangle() {
        return triangle;
    }

    public void setTriangle(Shape triangle) {
        this.triangle = triangle;
    }

    public Shape getSquare() {
        return square;
    }

    public void setSquare(Shape square) {
        this.square = square;
    }

}

abstract class GraphBuilder {
    Graph graph = new Graph();
    abstract void buildCircle();
    abstract void buildTriangle();
    abstract void buildSquare();

    public Graph build() {
        return graph;
    }
}

class Director {
    private GraphBuilder builder;

    Director(GraphBuilder builder) {
        this.builder = builder;
    }

    public Graph concrete() {
        builder.buildCircle();
        builder.buildTriangle();
        builder.buildSquare();

        return builder.build();
    }
}

public class Question3 {
    public static void main(String[] args) {

    }
}
