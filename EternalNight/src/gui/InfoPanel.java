package gui;

import contract.INotify;
import gui.components.InventarioItem;
import model.Inventario;
import model.VialSangre;
import model.aliado.LordValen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoPanel extends JPanel implements INotify {
    private Inventario inventario;
    private JPanel gridPanel;

    public InfoPanel(Inventario objetos) {
        setOpaque(false);
        inventario = objetos;
        inventario.setListener(this);

        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(30, 30, 30, 30));
        setOpaque(false);  // Importante para que se vea el fondo degradado

        // Título del inventario
        JLabel title = new JLabel("INVENTARIO DEL JUGADOR", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        // Botón de salir (usar un icono adecuado)
        JButton exitButton = createIconButton(UIManager.getIcon("OptionPane.errorIcon"));
        exitButton.setToolTipText("Salir");
        exitButton.addActionListener(e -> exitApplication());

        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Panel para las tarjetas de ítems (usando GridLayout)
        gridPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        gridPanel.setOpaque(false);

        // Llenar con los ítems iniciales
        actualizarInventario();

        // Scroll para el inventario
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);


        actualizarInventario();
    }

    private JButton createIconButton(Icon icon) {
        JButton button = new JButton(icon);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return button;
    }

    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(this.getParent(),
                "¿Estás seguro de que quieres salir?",
                "Salir",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void actualizarInventario() {
        gridPanel.removeAll();
        for (VialSangre item : inventario.getObjetos()) {
            gridPanel.add(new InventarioItem(item, inventario));
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    @Override
    public void inventarioActualizado() {
        SwingUtilities.invokeLater(this::actualizarInventario);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    }
}
