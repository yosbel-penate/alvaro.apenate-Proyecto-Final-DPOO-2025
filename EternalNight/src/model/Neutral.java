package model;

import contract.IFunction;
import contract.IHabilidad;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Neutral implements IFunction {
    protected String nombre;
    protected BufferedImage symbol;

    public Neutral(String nombre, String symbol) {
        this.nombre = nombre;
        try {
            this.symbol = ImageIO.read(Objects.requireNonNull(getClass().getResource(symbol)));
        } catch (IOException e) {
            e.printStackTrace();
            this.symbol = null;
        }
    }
}
