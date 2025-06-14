package gui;

import model.aliado.LordValen;
import model.enemy.Ghouls;

import javax.swing.*;
import java.awt.*;

public class CombatePanel extends JPanel {
    private LordValen jugador;
    private Ghouls lobo;
    private JTextArea logCombate;
    private JButton btnAtacar;
    private int saludMaximaJugador;
    private int saludMaximaLobo;
    private JProgressBar barraJugador;
    private JProgressBar barraLobo;

    public CombatePanel(LordValen jugador, Ghouls lobo) {
        this.jugador = jugador;
        this.lobo = lobo;
        setLayout(new BorderLayout());

        saludMaximaJugador = jugador.getSalud();
        saludMaximaLobo = lobo.getSalud();


        barraJugador = new JProgressBar(0, saludMaximaJugador);
        barraJugador.setValue(jugador.getSalud());
        barraJugador.setStringPainted(true);
        barraJugador.setForeground(Color.GREEN);

        barraLobo = new JProgressBar(0, saludMaximaLobo);
        barraLobo.setValue(lobo.getSalud());
        barraLobo.setStringPainted(true);
        barraLobo.setForeground(Color.RED);


        JPanel infoPanel = new JPanel(new GridLayout(2, 2));
        infoPanel.add(new JLabel("Jugador:"));
        infoPanel.add(barraJugador);
        infoPanel.add(new JLabel("Lobo:"));
        infoPanel.add(barraLobo);

        // Área de log
        logCombate = new JTextArea(10, 30);
        logCombate.setEditable(false);

        // Botón de ataque
        btnAtacar = new JButton("Atacar");
        btnAtacar.addActionListener(e -> turnoJugador());

        add(infoPanel, BorderLayout.NORTH);
        add(new JScrollPane(logCombate), BorderLayout.CENTER);
        add(btnAtacar, BorderLayout.SOUTH);

        logCombate.append("¡Comienza el combate!\n");
    }

    private void actualizarInfo() {
        // Actualizar barras de vida
        barraJugador.setValue(jugador.getSalud());
        barraJugador.setString(jugador.getSalud() + "/" + saludMaximaJugador + " HP");

        barraLobo.setValue(lobo.getSalud());
        barraLobo.setString(lobo.getSalud() + "/" + saludMaximaLobo + " HP");

        // Cambiar color según la vida
        actualizarColorBarra(barraJugador);
        actualizarColorBarra(barraLobo);

        repaint();
    }

    private void actualizarColorBarra(JProgressBar barra) {
        float porcentaje = (float) barra.getValue() / barra.getMaximum();
        if (porcentaje > 0.6) barra.setForeground(Color.GREEN);
        else if (porcentaje > 0.3) barra.setForeground(Color.ORANGE);
        else barra.setForeground(Color.RED);
    }

    private void animarAtaque(JProgressBar barra) {
        Timer timer = new Timer(100, null);
        timer.addActionListener(e -> {
            barra.setBackground(barra.getBackground() == Color.RED ?
                    barra.getParent().getBackground() : Color.RED);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void turnoJugador() {
        int danio = jugador.atacar();
        lobo.recibirAtaque(danio);
        logCombate.append("Atacas al lobo y le haces " + danio + " de daño\n");
        animarAtaque(barraLobo);
        actualizarInfo();

        if (!lobo.estaVivo()) {
            logCombate.append("¡Has derrotado al lobo!\n");
            btnAtacar.setEnabled(false);

            return;
        }

        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}

            int danioLobo = lobo.atacar();
            jugador.recibirAtaque(danioLobo);
            logCombate.append("El lobo te ataca y te hace " + danioLobo + " de daño\n");
            animarAtaque(barraJugador);
            actualizarInfo();

            if (!jugador.estaVivo()) {
                logCombate.append("¡Has sido derrotado!\n");
                btnAtacar.setEnabled(false);
                gameOver();
            }
        });
    }
    private void gameOver() {
        JOptionPane.showMessageDialog(this, "¡Has sido derrotado! Fin del juego.");
        // Reiniciar el juego o cerrar la aplicación
        System.exit(0); // Opción simple, podrías implementar reinicio
    }
}

