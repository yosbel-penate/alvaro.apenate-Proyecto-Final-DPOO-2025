
package src.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Clase Gui
public class Gui extends Canvas {
    private GraphicsContext gc;
    private boolean isMenuVisible;
    private int selectedOption;

    public Gui() {
        gc = this.getGraphicsContext2D();
        isMenuVisible = true;
        selectedOption = 0;
        setOnKeyPressed(this::handleKeyPress);
    }

    public void showMenu() {
        isMenuVisible = true;
        renderMenu();
    }

    public void hideMenu() {
        isMenuVisible = false;
        renderGame();
    }

    private void handleKeyPress(KeyEvent event) {
        if (isMenuVisible) {
            if (event.getCode() == KeyCode.UP) {
                selectedOption = (selectedOption - 1 + 3) % 3;
                renderMenu();
            } else if (event.getCode() == KeyCode.DOWN) {
                selectedOption = (selectedOption + 1) % 3;
                renderMenu();
            } else if (event.getCode() == KeyCode.ENTER) {
                handleMenuSelection();
            }
        }
    }

    private void handleMenuSelection() {
        switch (selectedOption) {
            case 0:
                // Iniciar el juego
                hideMenu();
                break;
            case 1:
                // Abrir las opciones
                break;
            case 2:
                // Salir del juego
                System.exit(0);
                break;
        }
    }

    private void renderMenu() {
        // Dibujar fondo del menú
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar opciones del menú
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        double y = getHeight() / 2 - 50;
        gc.fillText("Jugar", getWidth() / 2 - 50, y);
        y += 50;
        gc.fillText("Opciones", getWidth() / 2 - 75, y);
        y += 50;
        gc.fillText("Salir", getWidth() / 2 - 50, y);

        // Resaltar la opción seleccionada
        gc.setFill(Color.YELLOW);
        y = getHeight() / 2 - 50 + selectedOption * 50;
        gc.fillRect(getWidth() / 2 - 75, y, 150, 40);
    }

    public void renderGame() {
        // Dibujar el juego
    }

    public boolean isMenuVisible() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isMenuVisible'");
    }
}


