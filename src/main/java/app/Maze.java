package app;

import java.util.LinkedList;
import java.util.Random;

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

    /**
     * Method that returns the grid of the maze.
     * 
     * @return Box[][] -- grid of the maze.
     */
    public Box[][] getGrid() {
        return this.grid;
    }

    /**
     * Private method that given a box and a direction tells us if the next adjacent
     * box in that direction it was already used.
     *
     * 0 = Up. 1 = Right. 2 = Down. 3 = Left.
     * 
     * @param box              -- box in the maze.
     * @param directionNextBox -- direction of the next adyacent box.
     * @return boolean -- true if the box was already used and false if not.
     */
    private boolean isNextBoxUsed(Box box, int directionNextBox) {
        if (directionNextBox < 0 || directionNextBox > 3)
            throw new IllegalArgumentException("The only allowed values are between 0 and 3.");
        // [row,column] - coordinates of the box in the maze
        int[] boxPosition = box.getPosition();
        int row = boxPosition[0];
        int column = boxPosition[1];
        if (directionNextBox == 0) {
            if (row == 0)
                return true;
            return this.grid[row - 1][column].isUsed();
        }
        if (directionNextBox == 1) {
            if (column == this.grid[column].length - 1)
                return true;
            return this.grid[row][column + 1].isUsed();
        }
        if (directionNextBox == 2) {
            if (row == this.grid.length - 1)
                return true;
            return this.grid[row + 1][column].isUsed();
        }
        if (directionNextBox == 3) {
            if (column == 0)
                return true;
            return this.grid[row][column - 1].isUsed();
        }
        return false;
    }

    /**
     * Private method that returns a movement depending on the adjacent boxes of the
     * box passed as parameter.
     * 
     * 0 = Up. 1 = Right. 2 = Down. 3 = Left.
     * 
     * @param box -- box from which we will move.
     * @return int -- movement.
     */
    private int randomMove(Box box) {
        if (box == null)
            throw new IllegalArgumentException("Error. null pointer in randomMove(box).");
        Random random = new Random();
        LinkedList<Integer> movements = new LinkedList<>();
        if (!isNextBoxUsed(box, 0))
            movements.addLast(0);
        if (!isNextBoxUsed(box, 1))
            movements.addLast(1);
        if (!isNextBoxUsed(box, 2))
            movements.addLast(2);
        if (!isNextBoxUsed(box, 3))
            movements.addLast(3);
        return movements.get(random.nextInt(movements.size()));
    }

    /**
     * Private method that returns a boolean depending if all the adjacent boxes of
     * the box passed as parameter are used.
     * 
     * @param box -- box to verify.
     * @return boolean -- true if all adjacent boxes are used and false if not.
     */
    private boolean allAdjacentUsed(Box box) {
        if (box == null)
            throw new IllegalArgumentException("Error. null pointer in allAdjacentUsed(box).");
        return isNextBoxUsed(box, 0) && isNextBoxUsed(box, 1) && isNextBoxUsed(box, 2) && isNextBoxUsed(box, 3);
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
