package LaboratoryExercises.Lab01.MovableObject;

public class MovableCircle implements Movable {
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
        return "Movable circle with center coordinates (" + point.getCurrentXPosition() + "," + point.getCurrentYPosition() + ") and radius " + radius + "\n";
    }
}
