package LaboratoryExercises.Lab01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ObjectCanNotBeMovedException extends Exception {
    public ObjectCanNotBeMovedException(String message) {
        super(message);
    }
}

interface Movable {
    public void moveUp() throws ObjectCanNotBeMovedException;

    public void moveDown() throws ObjectCanNotBeMovedException ;

    public void moveRight() throws ObjectCanNotBeMovedException ;

    public void moveLeft() throws ObjectCanNotBeMovedException ;

    public int getCurrentXPosition();

    public int getCurrentYPosition();
}

class MovablePoint implements Movable {
    int x;
    int y;
    int xSpeed;
    int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public boolean outOfBounds(int x, int y) {
        return x < 0 || x > MovablesCollection.getXMax() || y < 0 || y > MovablesCollection.getYMax();
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        int newY = y + ySpeed;
        if (outOfBounds(x, newY))
            throw new ObjectCanNotBeMovedException("Point (" + x + "," + newY + ") is out of bounds");
        this.y = newY;
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        int newY = y - ySpeed;
        if (outOfBounds(x, newY))
            throw new ObjectCanNotBeMovedException("Point (" + x + "," + newY + ") is out of bounds");
        this.y = newY;
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        int newX = x + xSpeed;
        if (outOfBounds(newX, y))
            throw new ObjectCanNotBeMovedException("Point (" + newX + "," + y + ") is out of bounds");
        this.x = newX;
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        int newX = x - xSpeed;
        if (outOfBounds(newX, y))
            throw new ObjectCanNotBeMovedException("Point (" + newX + "," + y + ") is out of bounds");
        this.x = newX;
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public String toString() {
        return "Movable point with coordinates (" + x + "," + y + ")\n";
    }
}

class MovableCircle implements Movable {
    int radius;
    MovablePoint point;

    public MovableCircle(int radius, MovablePoint point) {
        this.radius = radius;
        this.point = point;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        point.moveUp();
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        point.moveDown();
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        point.moveRight();
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        point.moveLeft();
    }

    @Override
    public int getCurrentXPosition() {
        return point.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return point.getCurrentYPosition();
    }

    @Override
    public String toString() {
        return "Movable circle with center coordinates (" + point.getCurrentXPosition() + "," + point.getCurrentYPosition() + ") and radius " + radius+"\n";
    }
}

class MovableObjectNotFittableException extends Exception {
    public MovableObjectNotFittableException(int x, int y, int radius) {
        super("Movable circle with center (" + x + "," + y + ") and radius " + radius + " can not be fitted into the collection");
    }
}

class MovablesCollection {
    private List<Movable> movables;
    private static int MAX_X;
    private static int MAX_Y;

    public MovablesCollection(int max_x, int max_y) {
        this.movables = new ArrayList<>();
        MAX_X = max_x;
        MAX_Y = max_y;
    }

    public void addMovableObject(Movable m) {
        try {
            if (isFittable(m)) movables.add(m);
        } catch (MovableObjectNotFittableException e) {
            System.out.println(e.getMessage());
        }
    }

    public void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) {
        for (Movable m : movables) {
            if ((type == TYPE.POINT && m instanceof MovablePoint) || (type == TYPE.CIRCLE && m instanceof MovableCircle)) {
                try {
                    switch (direction) {
                        case UP:
                            m.moveUp();

                            break;
                        case DOWN:
                            m.moveDown();
                            break;
                        case LEFT:
                            m.moveLeft();
                            break;
                        case RIGHT:
                            m.moveRight();
                            break;
                    }
                } catch (ObjectCanNotBeMovedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collection of movable objects with size ").append(movables.size()).append(":\n");
        for (Movable m : movables) {
            sb.append(m);
        }
        return sb.toString();
    }

    private boolean isFittable(Movable m) throws MovableObjectNotFittableException {
        if (m instanceof MovablePoint) {
            MovablePoint p = (MovablePoint) m;
            return p.x >= 0 && p.x <= MAX_X && p.y >= 0 && p.y <= MAX_Y;
        } else {
            MovablePoint p = ((MovableCircle) m).point;
            int radius = ((MovableCircle) m).radius;
            if (p.x - radius >= 0 && p.x + radius <= MAX_X && p.y - radius >= 0 && p.y + radius <= MAX_Y) return true;
            else throw new MovableObjectNotFittableException(p.x, p.y, radius);
        }
    }

    public static void setxMax(int maxX) {
        MAX_X = maxX;
    }

    public static void setyMax(int maxY) {
        MAX_Y = maxY;
    }

    public static int getXMax() {
        return MAX_X;
    }

    public static int getYMax() {
        return MAX_Y;
    }
}


enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class CirclesTest {
    public static void main(String[] args) {
        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
            }
        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());
    }
}
