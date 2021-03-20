package app;

/**
 * Class that models the behavior of points in the plane.
 * 
 * @author Jesus Israel Gutierrez Elizalde
 * @version 1.0
 */
public class Point {
    private float x;
    private float y;

    /**
     * Constructor with parameters
     * 
     * @param x -- float -- x component of a point.
     * @param y -- float -- y component of a point.
     */
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method that returns the x component of the point.
     * 
     * @return float -- x component.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Method that returns the y component of the point.
     * 
     * @return float -- y component.
     */
    public float getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Point comparing = (Point) o;
        return this.x == comparing.x && this.y == comparing.y;
    }
}
