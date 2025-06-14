package gui.components;

import model.Inventario;
import model.VialSangre;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class InventarioItem extends JPanel {
    private final VialSangre item;
    private boolean selected;
    private Color color = Color.decode("#E0E0E0");
    private Inventario inventario;

    public InventarioItem(VialSangre item, Inventario inventario) {
        this.inventario = inventario;
        this.item = item;
        this.selected = false;
        setOpaque(false);
        setLayout(new BorderLayout(1, 1));
        setBorder(new EmptyBorder(2, 2, 2, 2));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Añadir efecto hover y selección
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                repaint();
            }

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selected = !selected;
                repaint();
            }
        });

        // Crear componentes de la tarjeta
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        setMaximumSize(new Dimension(120, 150));
        setPreferredSize(new Dimension(110, 140));  // Tamaño pequeño

        // Panel para la imagen circular
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int diameter = Math.min(getWidth(), getHeight()) - 10;
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                // Dibujar imagen
                try {
                    ImageIcon icon = new ImageIcon(item.getSymbol());
                    Image image = icon.getImage().getScaledInstance(diameter, diameter, Image.SCALE_SMOOTH);
                    g2d.drawImage(image, x, y, diameter, diameter, this);
                } catch (Exception e) {
                    // Fondo de respaldo
                    GradientPaint gradient = new GradientPaint(
                            0, 0, color.brighter(),
                            getWidth(), getHeight(), color.darker()
                    );
                    g2d.setPaint(gradient);
                    g2d.fillOval(x, y, diameter, diameter);
                }

                // Borde circular
                g2d.setColor(new Color(180, 180, 180));
                g2d.setStroke(new BasicStroke(1.0f));
                g2d.drawOval(x, y, diameter, diameter);
            }
        };
        imagePanel.setPreferredSize(new Dimension(50, 50));

        // Título centrado
        JLabel nameLabel = new JLabel(item.getNombre(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        nameLabel.setForeground(Color.BLACK);

        JButton useButton = new JButton("Usar");
        useButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        useButton.setBackground(new Color(70, 130, 180));
        useButton.setFocusPainted(false);
        useButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Hacer el botón más pequeño
        useButton.setPreferredSize(new Dimension(80, 25));

        // ActionListener para el botón
        useButton.addActionListener(e -> {
            if(inventario.getPersonaje().getSalud() < 600 && inventario.getPersonaje().getSalud() + 20 < 600) {
                inventario.curar(item);
            } else {
                notificar();
            }
        });

        // Panel para contener el botón (para centrarlo)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(useButton);

        // Organizar componentes
        add(imagePanel, BorderLayout.NORTH);
        add(nameLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Borde redondeado para toda la tarjeta
        setBorder(new RoundedBorder(15, new Color(230, 230, 230)));
    }

    private void notificar() {
        JOptionPane.showMessageDialog(this, "¡Tu vida esta completa, no necesitas curarte");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Crear un fondo redondeado para la tarjeta
        int arc = 20;
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                0, 0, (float) getWidth() / 2, getHeight(), arc, arc
        );

        // Color de fondo con transparencia
        Color bgColor = selected ?
                new Color(255, 255, 255, 30) :
                new Color(255, 255, 255, 20);

        g2d.setColor(bgColor);
        g2d.fill(roundedRectangle);

        // Borde si está seleccionado
        if (selected) {
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(3));
            g2d.draw(roundedRectangle);

            // Dibujar indicador de selección
            g2d.setColor(Color.GREEN);
            g2d.fillOval((getWidth() / 2) - 25, 10, 15, 15);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(180, 220);
    }
}
