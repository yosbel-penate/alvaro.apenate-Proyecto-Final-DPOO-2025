package gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SplashScreen splash = new SplashScreen();
            splash.showSplash(5000, () -> {
                splash.dispose();

                try {
                    GameScreen game = new GameScreen();
                    game.startGame();
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
