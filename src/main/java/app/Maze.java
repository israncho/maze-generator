package app;

/**
 * Class that models the behavior of a square shaped maze.
 * 
 * @author Jesus Israel Gutierrez Elizalde
 * @version 1.0
 */
public class Maze {

    private Box[][] grid;

    /**
     * Constructor with parameters of a maze. Creates the grid in which we will
     * create a maze.
     * 
     * @param gridSize  -- int - size of the grid.
     * @param planeSize -- int - size of the plane.
     */
    public Maze(int gridSize, int planeSize) {
        if (gridSize < 1)
            throw new IllegalArgumentException("The grid size must be at least 1.");
        if (planeSize < 1)
            throw new IllegalArgumentException("The plane size must be at least 1.");
        this.grid = new Box[gridSize][gridSize];
        float wallSizePerBox = planeSize / gridSize;
        float xAxis = 0;
        float yAxis = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Point[] verticesNewBox = new Point[4];
                verticesNewBox[0] = new Point(xAxis, yAxis);
                verticesNewBox[1] = new Point(xAxis + wallSizePerBox, yAxis);
                verticesNewBox[2] = new Point(xAxis, yAxis + wallSizePerBox);
                verticesNewBox[3] = new Point(xAxis + wallSizePerBox, yAxis + wallSizePerBox);
                int[] position = { i, j };
                this.grid[i][j] = new Box(verticesNewBox, position);
                xAxis += wallSizePerBox;
            }
            xAxis = 0;
            yAxis += wallSizePerBox;
        }
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++)
                string += grid[i][j].toString() + "\n";
            string += "\n";
        }
        return string;
    }

}
