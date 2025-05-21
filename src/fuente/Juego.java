
package src.fuente;

import javafx.scene.Parent;
import src.gui.Gui;

// Clase Juego
public class Juego {
    private Gui gui;
    private boolean isGameRunning;

    public Juego() {
        gui = new Gui();
        isGameRunning = false;
    }

    public void start() {
        isGameRunning = true;
        showMenu();
    }

    public void stop() {
        isGameRunning = false;
    }

    private void showMenu() {
        gui.showMenu();
        // Esperar a que el usuario seleccione una opción
        while (isGameRunning && gui.isMenuVisible()) {
            // Actualizar el juego
            update();
            // Renderizar el juego
            render();
            // Esperar un breve período de tiempo
            try {
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gui.hideMenu();
    }

    private void update() {
        // Actualizar el estado del juego
    }

    private void render() {
        // Renderizar el juego
        gui.renderGame();
    }

    public Parent getGui() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGui'");
    }
}

