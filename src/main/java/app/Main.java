package app;

import processing.core.PApplet;

public class Main extends PApplet {

    private Maze maze = null;
    public static int mazeSize = 0;

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        this.maze = new Maze(mazeSize, 500);
    }

    /**
     * Method that draws the maze.
     */
    public void drawMaze() {
        Box[][] grid = this.maze.getGrid();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid.length; j++)
                for (int k = 0; k < 4; k++)
                    if (grid[i][j].getWall(k)) {
                        Point[] points = grid[i][j].getWallPoints(k);
                        line(points[0].getX(), points[0].getY(), points[1].getX(), points[1].getY());
                    }
    }

    @Override
    public void keyPressed() {
        if (key == 'q' || key == 'Q')
            exit();
        if (key == ENTER)
            drawMaze();
    }

    public static void main(String[] args) {
        String argMazeSize = "";
        boolean canExecute = true;
        try {
            argMazeSize = args[0];
        } catch (Exception e) {
            canExecute = false;
            System.out.println("----Error in first argument----");
            System.out.println(e.getMessage());
        }
        if (canExecute)
            try {
                Main.mazeSize = Integer.parseInt(argMazeSize);
            } catch (Exception e) {
                canExecute = false;
                System.out.println("----Error, the argument is not an integer----");
                System.out.println(e.getMessage());
            }
        if (canExecute)
            PApplet.main(Main.class.getName());
    }
}