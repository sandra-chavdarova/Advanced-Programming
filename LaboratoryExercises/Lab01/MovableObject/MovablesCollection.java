package LaboratoryExercises.Lab01.MovableObject;

import java.util.ArrayList;
import java.util.List;

class MovableObjectNotFittableException extends Exception {
    public MovableObjectNotFittableException(int x, int y, int radius) {
        super("Movable circle with center (" + x + "," + y + ") and radius " + radius + " can not be fitted into the collection");
    }
}

public class MovablesCollection {
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
