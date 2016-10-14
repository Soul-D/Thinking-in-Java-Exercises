package polymorphism;

import polymorphism.shape.Shape;

public class Ex4_Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Ex4_Rectangle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Ex4_Rectangle.erase()");
    }

    @Override
    public void printMessage() {
        System.out.println("Ex4_Rectangle");
    }
}
