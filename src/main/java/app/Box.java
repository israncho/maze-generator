package app;

/**
 * Class that models the behavior of boxes. Our maze is made up of boxes.
 * 
 * @author Jesus Israel Gutierrez Elizalde
 * @version 1.0
 */
public class Box {
    /**
     * indexes of each wall: 0 = Above. 1 = Right. 2 = Below. 3 = Left.
     */
    private boolean[] walls = new boolean[4];
    private boolean used;
    private boolean isSolution;
    /**
     * indexes of each vertex: 0 = Top Left. 1 = Top Right. 2 = Lower Left. 3 =
     * Lower Right
     */
    private Point[] verticesOfTheBox;
    /** tuple of integers representing position [x,y] */
    private int[] position;

    /**
     * Constructor by parameters of a box.
     * 
     * @param verticesOfTheBox -- array of points that will be the vertices of the
     *                         box
     * @param position         -- coordinates of the box in the maze
     */
    public Box(Point[] verticesOfTheBox, int[] position) {
        if (verticesOfTheBox.length != 4)
            throw new IllegalArgumentException("Wrong number of vertices.");
        if (!hasSquareForm(verticesOfTheBox))
            throw new IllegalArgumentException("Vertices don't form a square.");
        if (position.length != 2)
            throw new IllegalArgumentException("Wrong number of coordinates.");
        if (position[0] < 0 || position[1] < 0)
            throw new IllegalArgumentException("All coordinates should be positive.");
        this.verticesOfTheBox = verticesOfTheBox;
        this.position = position;
        for (int i = 0; i < walls.length; i++)
            this.walls[i] = true;
        this.used = false;
        this.isSolution = false;
    }

    /**
     * Method that tells us if a box has a wall. Remember indexes of each wall 0 =
     * Above. 1 = Right. 2 = Below. 3 = Left.
     * 
     * @param wallIndex -- int - index of the wall we're verifying.
     * @return boolean -- true if the box has the wall or false if not.
     */
    public boolean getWall(int wallIndex) {
        if (wallIndex < 0 || wallIndex > 3)
            throw new IllegalArgumentException("Wrong wall index number try using a number in [0,3].");
        return this.walls[wallIndex];
    }

    /**
     * Method to set the walls value of a box. Remember indexes of each wall 0 =
     * Above. 1 = Right. 2 = Below. 3 = Left.
     * 
     * @param wallIndex         -- int - index of the wall wich value will be
     *                          assigned.
     * @param newValueOfTheWall -- boolean - true put wall and false remove wall.
     */
    public void setWall(int wallIndex, boolean newValueOfTheWall) {
        if (wallIndex < 0 || wallIndex > 3)
            throw new IllegalArgumentException("Wrong wall index number try using a number in [0,3].");
        this.walls[wallIndex] = newValueOfTheWall;
    }

    /**
     * Method that tells us if the box is used.
     * 
     * @return boolean -- true if is used and false if not
     */
    public boolean isUsed() {
        return this.used;
    }

    /**
     * Method to use a box.
     */
    public void use() {
        this.used = true;
    }

    /**
     * Method that tells us if the box is part of the solution of the maze.
     * 
     * @return boolean -- true if is part of the solution and false if not.
     */
    public boolean isSolution() {
        return this.isSolution;
    }

    /**
     * Method to set a box as solution of the maze.
     */
    public void setAsSolution() {
        this.isSolution = true;
    }

    /**
     * Method tha returns the vertices of the box.
     * 
     * @return Point[] -- vertices of the box.
     */
    public Point[] getVerticesOfTheBox() {
        return this.verticesOfTheBox;
    }

    /**
     * Method that returns the position of box in the maze.
     * 
     * @return int[] -- position of the box.
     */
    public int[] getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Box comparing = (Box) o;
        if (this.position[0] != comparing.position[0] || this.position[1] != comparing.position[1])
            return false;
        for (int i = 0; i < this.walls.length; i++)
            if (this.walls[i] != comparing.walls[i])
                return false;
        return true;
    }

    private boolean hasSquareForm(Point[] points) {
        return points[0].getY() == points[1].getY() && points[2].getY() == points[3].getY()
                && points[0].getX() == points[2].getX() && points[1].getX() == points[3].getX();
    }

    @Override
    public String toString() {
        return "Position: (" + this.position[0] + "," + this.position[1] + "). Vertices: [ TL"
                + this.verticesOfTheBox[0].toString() + ", TR" + this.verticesOfTheBox[1].toString() + ", LL"
                + this.verticesOfTheBox[2].toString() + ", LR" + this.verticesOfTheBox[3].toString() + "]";
    }
}
