package gui;

import model.Inventario;
import model.VialSangre;
import model.aliado.LordValen;
import model.enemy.Ghouls;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Tenebris extends JPanel {
    private int rows = 25;
    private int cols = 30;
    private List<String> obstaculos;
    private boolean debeRegenerarMapa = true;
    private Map<Point, String> posicionesObstaculos = new HashMap<>();
    private List<Point> posicionesEnemigos = new ArrayList<>();
    Map<Point, Ghouls> lobosMapa = new HashMap<>();
    private List<Point> posicionesRecursos = new ArrayList<>();
    private Inventario inventario;
    private int saludMaximaJugador;
    private int jugadorX = 0;
    private int jugadorY = 0;
    private boolean enCombate = false;
    private int recursosRecolectados = 0;
    private int cellSize;
    private LordValen jugador;
    private VialSangre vialSangre;

    private BufferedImage imgTerreno;
    private BufferedImage imgArbol;
    private BufferedImage imgJugador;
    private BufferedImage imgRocoso;
    private BufferedImage imgLobos;
    private Image imgRecurso;

    public Tenebris(LordValen gamer, Inventario objetos) {
        setOpaque(false);
        jugador = gamer;
        inventario = objetos;
        vialSangre = new VialSangre("Vial de Sangre", "/resource/recursos/VialSangre.png");
        saludMaximaJugador = jugador.getSalud();

        try {
            imgRocoso = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/mapas/rocoso.png")));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen de fondo");
            imgRocoso = null;
        }
        try {
            imgTerreno = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/mapas/tierra.png")));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen de fondo");
            imgTerreno = null;
        }
        try {
            imgArbol = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/mapas/trampa.png")));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen de fondo");
            imgArbol = null;
        }
        try {
            imgLobos = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/personajes/lobos_vampiricos.png")));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen de de los lobos");
            imgLobos = null;
        }
        try {
            imgJugador = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/personajes/lord_valen.png")));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen de fondo");
            imgJugador = null;
        }
        try {
            imgRecurso = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/recursos/VialSangre.png")));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen de fondo");
            imgRecurso = null;
        }

        configurarControles();
        setFocusable(true);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    private void configurarControles() {
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!enCombate) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W -> moverJugador(0, -1);  // Arriba
                        case KeyEvent.VK_S -> moverJugador(0, 1);   // Abajo
                        case KeyEvent.VK_A -> moverJugador(-1, 0);  // Izquierda
                        case KeyEvent.VK_D -> moverJugador(1, 0);   // Derecha
                        case KeyEvent.VK_E -> recolectarRecurso();  // Recolectar
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && !enCombate) {
                    // Convertir coordenadas de ratón a posición en grid
                    int columna = e.getX() / cellSize;
                    int fila = e.getY() / cellSize;
                    atacar(columna, fila);
                }
            }
        });
    }

    private void moverJugador(int dx, int dy) {
        int nuevaX = jugadorX + dx;
        int nuevaY = jugadorY + dy;

        // Verificar límites del mapa
        if (nuevaX < 0 || nuevaX >= cols || nuevaY < 0 || nuevaY >= rows) {
            return;
        }

        if (hayObstaculoEn(nuevaX, nuevaY)) {
            return;
        }

        verificarProximidadConLobos();

        jugadorX = nuevaX;
        jugadorY = nuevaY;
        System.out.println("Jugador " + jugadorX + ", " + jugadorY);
        repaint();
    }

    private void verificarProximidadConLobos() {
        for (Point loboPos : posicionesEnemigos) {
            int distanciaX = Math.abs(jugadorX - loboPos.x);
            int distanciaY = Math.abs(jugadorY - loboPos.y);
            int distancia = Math.max(distanciaX, distanciaY); // Distancia Chebyshev

            if (distancia <= 2) {
                iniciarCombate(loboPos);
                break; // Solo un combate a la vez
            }
        }
    }

    private void recolectarRecurso() {
        if (hayRecursoEn(jugadorX, jugadorY)) {
            recursosRecolectados++;
            recolectarRecurso(jugadorX, jugadorY);
            repaint();

            inventario.addObjeto(vialSangre);
        }

    }

    private void atacar(int columna, int fila) {

        boolean enRango = Math.abs(jugadorX - columna) <= 1 &&
                Math.abs(jugadorY - fila) <= 1;

        if (enRango && hayEnemigoEn(columna, fila)) {
            // Eliminar enemigo
            Point pos = new Point(columna, fila);
            eliminarLobo(pos);
            repaint();
            System.out.println("¡Enemigo eliminado!");
        }
    }

    private Ghouls obtenerLoboPorPosicion(Point pos) {
        return lobosMapa.get(pos);
    }

    private void eliminarLobo(Point pos) {
        lobosMapa.remove(pos);
        posicionesEnemigos.remove(pos);
        // Cambiar el elemento en esa posición a "camino"
        obstaculos.set(pos.y * cols + pos.x, "camino");
    }

    private void iniciarCombate(Point loboPos) {
        // Obtener referencia al lobo (necesitarás una estructura mejor)
        Ghouls lobo = obtenerLoboPorPosicion(loboPos);

        // Crear diálogo de combate
        CombatePanel panelCombate = new CombatePanel(jugador, lobo);

        JDialog dialogoCombate = new JDialog((Frame)SwingUtilities.getWindowAncestor(this), "Combate", true);
        dialogoCombate.setSize(400, 300);
        dialogoCombate.add(panelCombate);
        dialogoCombate.setLocationRelativeTo(this);
        dialogoCombate.setVisible(true);

        // Si el lobo fue derrotado, eliminarlo del mapa
        if (!lobo.estaVivo()) {
            eliminarLobo(loboPos);
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (debeRegenerarMapa) {
            posicionesObstaculos.clear();
            posicionesEnemigos.clear();
            posicionesRecursos.clear();
        }

        // 2. Calcular tamaño y dimensiones
        cellSize = Math.min(getWidth() / cols, getHeight() / rows);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        cols = Math.max(1, getWidth() / cellSize);
        rows = Math.max(1, getHeight() / cellSize);

        if (obstaculos == null || debeRegenerarMapa) {
            String[] elementos = {
                    "lobo", "lobo", "lobo", "lobo", "lobo",
                    "recurso", "recurso", "recurso", "camino",
                    "piedra", "recurso", "recurso", "recurso",
                    "recurso", "recurso", "recurso", "recurso",
                    "recurso", "recurso",
                    "camino", "camino"
            };

            int totalCeldas = rows * cols;
            obstaculos = new ArrayList<>(Arrays.asList(elementos));
            int piedra = 0;
            while (obstaculos.size() < totalCeldas) {
                if(piedra < 150) {
                    piedra++;
                    obstaculos.add("piedra");
                } else {
                    obstaculos.add("camino");
                }
            }
            Collections.shuffle(obstaculos);

            // Buscar posición inicial válida (camino) para el jugador
            List<Point> posicionesCamino = new ArrayList<>();
            for (int i = 0; i < totalCeldas; i++) {
                int fila = i / cols;
                int columna = i % cols;
                String elemento = obstaculos.get(i);
                Point posicion = new Point(columna, fila);

                posicionesObstaculos.put(posicion, elemento);
                if ("lobo".equals(elemento)) {
                    Ghouls lobo = new Ghouls("Lobos Vampíricos", 400, 100, 50, "Melee", "/resource/personajes/lobos_vampiricos.png");
                    lobosMapa.put(posicion, lobo);
                    posicionesEnemigos.add(posicion); // Mantener para pintado
                } else if ("recurso".equals(elemento)) {
                    posicionesRecursos.add(posicion);
                }

                // Guardar posiciones de camino válidas
                if ("camino".equals(elemento)) {
                    posicionesCamino.add(posicion);
                }
            }

            // Posición inicial aleatoria en un camino
            if (!posicionesCamino.isEmpty()) {
                Point posInicial = posicionesCamino.get(new Random().nextInt(posicionesCamino.size()));
                jugadorX = posInicial.x;
                jugadorY = posInicial.y;
            } else { // Fallback seguro
                jugadorX = 0;
                jugadorY = 0;
            }

            debeRegenerarMapa = false;
        }

        // Pintar el mapa
        for (int i = 0; i < rows * cols; i++) {
            int fila = i / cols;
            int columna = i % cols;
            String elemento = obstaculos.get(i);
            Image imagen = null;

            switch (elemento) {
                case "lobo":
                    Point posicion = new Point(columna, fila);
                    imagen = imgLobos;
                    // Verificar si está en combate (opcional)
                    if (lobosMapa.get(posicion) != null && !lobosMapa.get(posicion).estaVivo()) {
                        imagen = imgTerreno; // Ya no está el lobo
                    }
                    break;
                case "arbol": imagen = imgArbol; break;
                case "recurso": imagen = imgRecurso; break;
                case "piedra": imagen = imgRocoso; break;
                default: imagen = imgTerreno;
            }

            if (imagen != null) {
                g2d.drawImage(imagen, columna * cellSize, fila * cellSize, cellSize, cellSize, this);
            }
        }

        g2d.drawImage(
                imgJugador,
                jugadorX * cellSize,
                jugadorY * cellSize,
                cellSize,
                cellSize,
                this
        );

        pintarHUD(g2d);
    }

    public boolean hayObstaculoEn(int x, int y) {
        Point pos = new Point(x, y);
        String elemento = posicionesObstaculos.get(pos);
        return "arbol".equals(elemento) || "piedra".equals(elemento);
    }

    public boolean hayRecursoEn(int x, int y) {
        Point pos = new Point(x, y);
        String elemento = posicionesObstaculos.get(pos);
        return "recurso".equals(elemento);
    }

    public boolean hayEnemigoEn(int x, int y) {
        return posicionesEnemigos.contains(new Point(x, y));
    }

    public void recolectarRecurso(int x, int y) {
        Point pos = new Point(x, y);
        if (posicionesRecursos.remove(pos)) {
            posicionesObstaculos.put(pos, "camino");

            // Actualizar el mapa gráfico
            int index = y * cols + x;
            if (index < obstaculos.size()) {
                obstaculos.set(index, "camino");
            }
        }
    }

    private void pintarHUD(Graphics2D g2d) {
        int barWidth = 200;
        int barHeight = 20;
        int x = 10;
        int y = 10;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(x, y, barWidth, barHeight);

        float vidaPorcentaje = (float) jugador.getSalud() / saludMaximaJugador;
        g2d.setColor(vidaPorcentaje > 0.6 ? Color.GREEN :
                vidaPorcentaje > 0.3 ? Color.ORANGE : Color.RED);
        g2d.fillRect(x, y, (int)(barWidth * vidaPorcentaje), barHeight);

        g2d.setColor(Color.WHITE);
        g2d.drawString("Jugador: " + jugador.getSalud() + "/" + saludMaximaJugador + " HP", x, y + 15);
    }
}