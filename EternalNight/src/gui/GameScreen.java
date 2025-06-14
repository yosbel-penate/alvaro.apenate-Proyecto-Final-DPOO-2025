package gui;

import model.Inventario;
import model.aliado.LordValen;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JFrame {
    private boolean running = true;
    private Tenebris tenebris;
    private InfoPanel infoPanel;
    private LordValen jugador;
    private Inventario inventario;

    public GameScreen() {
        jugador = new LordValen("Lord Valen", 600, 150, 50, "/resource/personajes/lord_valen.png");
        inventario = new Inventario(jugador);
        setTitle("Tenebris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setUndecorated(true);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);

        tenebris = new Tenebris(jugador, inventario);
        infoPanel = new InfoPanel(inventario);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tenebris, infoPanel);
        splitPane.setDividerSize(5);
        splitPane.setResizeWeight(11.0/12.0);
        splitPane.setEnabled(false);

        add(splitPane);
    }

    public void startGame() {
        setVisible(true);

        new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                tenebris.repaint();
            }
        }).start();
    }
}
