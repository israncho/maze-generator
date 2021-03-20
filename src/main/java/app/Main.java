package app;

import processing.core.PApplet;

public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Main.class.getName());
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {

    }

    @Override
    public void keyPressed(){
        if (key == 'q'|| key == 'Q')
            exit();
    }

}