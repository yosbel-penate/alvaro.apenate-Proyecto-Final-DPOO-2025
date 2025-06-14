package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SplashScreen extends JWindow {
    private JProgressBar progressBar;
    private int progress = 0;

    public SplashScreen() {
        // Configurar tamaño y centrado
        setSize(600, 400);
        centerWindow();

        // Panel principal con imagen de fondo
        JPanel content = new JPanel(new BorderLayout()) {
            private Image backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/SplashScreen.jpg")));
                } catch (IOException e) {
                    System.err.println("Error al cargar imagen de fondo: " + e.getMessage());
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Dibujar imagen escalada al tamaño del panel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Configurar panel con semi-transparencia para mejor legibilidad
        JPanel overlay = new JPanel();
        overlay.setLayout(new BoxLayout(overlay, BoxLayout.Y_AXIS));
        overlay.setOpaque(false);
        overlay.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título del juego
        JLabel title = new JLabel("Eternal Night", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Versión
        JLabel version = new JLabel("Versión 1.0", SwingConstants.CENTER);
        version.setFont(new Font("Arial", Font.PLAIN, 16));
        version.setForeground(Color.WHITE);
        version.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Espacio flexible
        overlay.add(Box.createVerticalGlue());

        // Barra de progreso
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(0, 0, 150)); // Verde
        progressBar.setBackground(new Color(0, 0, 0, 150)); // Negro semi-transparente
        progressBar.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Añadir componentes al overlay
        overlay.add(title);
        overlay.add(Box.createVerticalStrut(10));
        overlay.add(version);
        overlay.add(Box.createVerticalGlue());
        overlay.add(progressBar);

        content.add(overlay, BorderLayout.CENTER);
        setContentPane(content);
    }

    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);
    }

    public void showSplash(int duration, Runnable callback) {
        setVisible(true);

        // Timer para actualizar la barra de progreso
        Timer progressTimer = new Timer(duration/100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progress++;
                progressBar.setValue(progress);
                progressBar.setString("Cargando " + progress + "%");

                if (progress >= 100) {
                    ((Timer)e.getSource()).stop();
                    callback.run();
                }
            }
        });

        progressTimer.start();
    }
}
