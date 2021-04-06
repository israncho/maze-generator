package app;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Class that models the behavior of a square shaped maze.
 * 
 * @author Jesus Israel Gutierrez Elizalde
 * @version 1.0
 */
public class Maze {

    private Box[][] grid;
    /** 0 = untouched grid, 1 = maze generated, 2 = maze solved */
    private int stateOfTheMaze;
    private Box startBox;
    private Box endBox;
    private float wallSizePerBox;

    /**
     * Constructor with parameters of a maze. Creates the grid in which we will
     * create a maze.
     * 
     * @param gridSize  -- int - size of the grid.
     * @param planeSize -- int - size of the plane.
     */
    public Maze(int gridSize, int planeSize) {
        if (gridSize < 1)
            throw new IllegalArgumentException("The maze (grid) size must be at least 1.");
        if (planeSize < 1)
            throw new IllegalArgumentException("The plane size must be at least 1.");
        this.grid = new Box[gridSize][gridSize];
        this.wallSizePerBox = planeSize / (float) gridSize; // cast because we want a float, not an integer.
        // System.out.println(wallSizePerBox);
        float xAxis = 0;
        float yAxis = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Point[] verticesNewBox = new Point[4];
                verticesNewBox[0] = new Point(xAxis, yAxis);
                verticesNewBox[1] = new Point(xAxis + this.wallSizePerBox, yAxis);
                verticesNewBox[2] = new Point(xAxis, yAxis + this.wallSizePerBox);
                verticesNewBox[3] = new Point(xAxis + this.wallSizePerBox, yAxis + this.wallSizePerBox);
                int[] position = { i, j };
                this.grid[i][j] = new Box(verticesNewBox, position);
                xAxis += this.wallSizePerBox;
            }
            xAxis = 0;
            yAxis += wallSizePerBox;
        }
        this.stateOfTheMaze = 0;
        this.startBox = this.grid[0][0];
        this.endBox = this.grid[this.grid.length - 1][this.grid[0].length - 1];
        this.startBox.setAsSolution();
        this.endBox.setAsSolution();
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
     * Method that returns the current state of the maze.
     * 
     * 0 = untouched grid, 1 = maze generated, 2 = maze solved
     * 
     * @return int -- Current state of the maze.
     */
    public int getStateOfTheMaze() {
        return this.stateOfTheMaze;
    }

    /**
     * Method that returns the wall size per box in the maze.
     * 
     * @return float -- wall size per box.
     */
    public float getWallSizePerBox() {
        return this.wallSizePerBox;
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
        if (box == null)
            throw new IllegalArgumentException("Error. Null pointer in isNextBoxUsed().");
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
            throw new IllegalArgumentException("Error. Null pointer in randomMove(box).");
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

    /**
     * Private method that models the behavior of movement and erasement of walls.
     * 
     * @param box -- box from which moving.
     * @return Box -- box to which it moved.
     */
    private Box movement(Box box) {
        if (box == null)
            throw new IllegalArgumentException("Error. null pointer in movement(box).");
        int[] parameterBoxPosition = box.getPosition();
        int row = parameterBoxPosition[0];
        int column = parameterBoxPosition[1];
        int movement = randomMove(box);
        Box aux = null;
        if (movement == 0) {
            box.setWall(0, false);
            aux = this.grid[row - 1][column];
            aux.setWall(2, false);
        }
        if (movement == 1) {
            box.setWall(1, false);
            aux = this.grid[row][column + 1];
            aux.setWall(3, false);
        }
        if (movement == 2) {
            box.setWall(2, false);
            aux = this.grid[row + 1][column];
            aux.setWall(0, false);
        }
        if (movement == 3) {
            box.setWall(3, false);
            aux = this.grid[row][column - 1];
            aux.setWall(1, false);
        }
        aux.use();
        return aux;
    }

    /**
     * Method that generates the maze creating all the paths randomly.
     */
    public void generateMaze() {
        if (this.stateOfTheMaze != 0)
            throw new IllegalArgumentException("To generate the maze the state of it must be \"untouched grid\".");
        Stack<Box> stack = new Stack<>();
        Random random = new Random();
        int randomRow = random.nextInt(this.grid.length);
        int randomColumn = random.nextInt(this.grid[randomRow].length);
        Box startBox = this.grid[randomRow][randomColumn];
        startBox.use();
        stack.push(startBox);
        while (!stack.isEmpty()) {
            Box aux = stack.peek();
            if (allAdjacentUsed(aux))
                stack.pop();
            else
                stack.push(movement(aux));
        }
        this.stateOfTheMaze++;
    }

    /**
     * Private method that models the behavior of a posible movement in the maze
     * that has been generated. Posible moves are the normal moves in a maze,
     * depending on the walls.
     * 
     * @param box -- box from which it moves.
     * @return LinkedList<Box> -- list of posible moves.
     */
    private LinkedList<Box> movementWithWalls(Box box) {
        if (box == null)
            throw new IllegalArgumentException("Error. null pointer in movement(box).");
        LinkedList<Box> posibleMovements = new LinkedList<>();
        int[] positionOfTheBox = box.getPosition();
        int row = positionOfTheBox[0];
        int column = positionOfTheBox[1];
        if (!box.getWall(0))
            posibleMovements.addLast(this.grid[row - 1][column]);
        if (!box.getWall(1))
            posibleMovements.addLast(this.grid[row][column + 1]);
        if (!box.getWall(2))
            posibleMovements.addLast(this.grid[row + 1][column]);
        if (!box.getWall(3))
            posibleMovements.addLast(this.grid[row][column - 1]);
        return posibleMovements;
    }

    /**
     * Method that generates the solution of a generated maze.
     */
    public void generateSolution() {
        if (this.stateOfTheMaze != 1)
            throw new IllegalArgumentException("To generate the maze the state of it must be \"generated maze\".");
        Queue<Box> queue = new LinkedList<>();
        queue.add(this.startBox);
        boolean notFinished = true;
        while (notFinished) {
            Box aux = queue.peek();
            LinkedList<Box> movements = movementWithWalls(aux);
            movements.remove(aux.getPrevious());
            for (Box box : movements) {
                box.setPrevious(aux);
                queue.add(box);
                if (box.equals(this.endBox))
                    notFinished = false;
            }
            queue.remove();
        }
        Box anotherAux = this.endBox;
        while (!anotherAux.equals(this.startBox)) {
            anotherAux.setAsSolution();
            anotherAux = anotherAux.getPrevious();
        }
        this.stateOfTheMaze++;
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
