package app;

/**
 * Class that models the behavior of boxes. Our maze is made up of boxes.
 * 
 * @author Jesus Israel Gutierrez Elizalde
 * @version 1.0
 */
public class Box {
    private boolean[] walls;
    private boolean used;
    private boolean isSolution;

    /**
     * 
     * @param vertexOfTheBox
     */
    public Box (Point[] vertexOfTheBox){
        if (vertexOfTheBox.length != 4)
            throw new IllegalArgumentException("Wrong number of vertices.");
        this.walls = new boolean[4];
        for (int i = 0; i < walls.length; i++) 
           this.walls[i] = true; 
        this.used = false;
        this.isSolution = false;
    }
}
