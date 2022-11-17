package game;

import engine.*;

public class Main {

    public static void main(String[] args) {
        Engine gameEng = new Engine("Slime Evolve", new Window.WindowOptions(), new SlimeEvolve());
        gameEng.start();

    }
}
