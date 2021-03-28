package app;

import processing.core.PApplet;

/**
 * Main class that executes the program.
 * 
 * @author Jesus Israel Gutierrez Elizalde
 * @version 1.0
 */
public class Main extends PApplet {

    private Maze maze;
    private int mazeSize;

    /**
     * Constructor with parameters of a main object.
     * 
     * @param mazeSize -- int - size of the square shaped maze.
     */
    public Main(int mazeSize) {
        this.maze = new Maze(mazeSize, 500);
        this.mazeSize = mazeSize;
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        background(255);
    }

    @Override
    public void draw() {

    }

    /**
     * Method that draws the maze.
     */
    public void drawMaze() {
        background(255);
        float wallSizePerBox = this.maze.getWallSizePerBox();
        Box[][] grid = this.maze.getGrid();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].isSolution()) {
                    Point[] vertices = grid[i][j].getVerticesOfTheBox();
                    fill(255, 150, 150);
                    noStroke();
                    rect(vertices[0].getX(), vertices[0].getY(), wallSizePerBox, wallSizePerBox);
                }
                for (int k = 0; k < 4; k++)
                    if (grid[i][j].getWall(k)) {
                        Point[] points = grid[i][j].getWallPoints(k);
                        stroke(0);
                        line(points[0].getX(), points[0].getY(), points[1].getX(), points[1].getY());
                    }
            }
    }

    @Override
    public void keyPressed() {
        if (key == 'q' || key == 'Q')
            exit();
        if (key == ENTER) {
            if (this.maze.getStateOfTheMaze() == 2)
                this.maze = new Maze(this.mazeSize, 500);
            if (this.maze.getStateOfTheMaze() == 1)
                this.maze.generateSolution();
            if (this.maze.getStateOfTheMaze() == 0)
                this.maze.generateMaze();
            drawMaze();
        }
    }

    public static void main(String[] args) {
        String argMazeSize = "";
        boolean canExecute = true;
        int mazeSize = 0;
        String[] sketchArgs = { "Maze" };
        Main myMain = null;
        try {
            argMazeSize = args[0];
        } catch (Exception e) {
            canExecute = false;
            System.out.println("----Error in first argument----");
            System.out.println(e.getMessage() + "\n");
        }
        try {
            mazeSize = Integer.parseInt(argMazeSize);
        } catch (Exception e) {
            canExecute = false;
            System.out.println("----Error, the argument is not an integer----");
            System.out.println(e.getMessage() + "\n");
        }
        try {
            myMain = new Main(mazeSize);
        } catch (Exception e) {
            canExecute = false;
            System.out.println("----Error----\n" + e.getMessage() + "\n");
        }
        if (canExecute)
            PApplet.runSketch(sketchArgs, myMain);
    }
}