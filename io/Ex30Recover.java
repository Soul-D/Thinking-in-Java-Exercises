package io;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Ex30Recover {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("./src/main/java/io/CADState.out"));
        Ex30Circle.deserializeStaticState(in);
        Ex30Square.deserializeStaticState(in);
        Line.deserializeStaticState(in);
        List<Shape> shapes = (List<Shape>) in.readObject();
        System.out.println(shapes);
    }
}

class Ex30Circle extends Circle {

    public Ex30Circle(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public static void serializeStaticState(ObjectOutputStream os) throws IOException {
        try {
            Field field = Circle.class.getDeclaredField("color");
            field.setAccessible(true);
            os.writeInt((Integer) field.get(Circle.class));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeStaticState(ObjectInputStream os) throws IOException {
        new Circle(0, 0, 0).setColor(os.readInt());
    }
}

class Ex30Square extends Square {

    public Ex30Square(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    public static void serializeStaticState(ObjectOutputStream os) throws IOException {
        try {
            Field field = Circle.class.getDeclaredField("color");
            field.setAccessible(true);
            os.writeInt((Integer) field.get(Circle.class));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeStaticState(ObjectInputStream os) throws IOException {
        new Square(0, 0, 0).setColor(os.readInt());
    }
}

class Ex30StoreCADState {
    public static void main(String[] args) throws Exception {
        List<Shape> shapes = new ArrayList<Shape>();
        for (int i = 0; i < 10; i++)
            shapes.add(Shape.randomFactory());
        for (int i = 0; i < 10; i++)
            (shapes.get(i)).setColor(Shape.GREEN);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./src/main/java/io/CADState.out"));
        Ex30Circle.serializeStaticState(out);
        Ex30Square.serializeStaticState(out);
        Line.serializeStaticState(out);
        out.writeObject(shapes);
        System.out.println(shapes);
    }
}
