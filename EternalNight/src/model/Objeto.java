package model;

import contract.ICurar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Objeto implements ICurar {
    protected String nombre;
    protected BufferedImage symbol;

    public Objeto(String nombre, String symbol) {
        this.nombre = nombre;
        try {
            this.symbol = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(symbol)));
        } catch(IOException e) {
            e.printStackTrace();
            this.symbol = null;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BufferedImage getSymbol() {
        return symbol;
    }
}
