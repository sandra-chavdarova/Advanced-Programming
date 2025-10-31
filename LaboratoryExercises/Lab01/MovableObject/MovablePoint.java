package LaboratoryExercises.Lab01.MovableObject;

public class MovablePoint implements Movable {
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