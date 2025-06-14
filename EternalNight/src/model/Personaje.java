package model;

import contract.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Personaje implements IAtaque, IHabilidad, IMove {
    protected String nombre;
    protected BufferedImage symbol;
    protected int salud;
    protected int ataque;
    protected int defensa;
    protected int x, y;

    public Personaje(String nombre, int salud, int ataque, int defensa, String symbol) {
        this.nombre = nombre;
        try {
            this.symbol = ImageIO.read(Objects.requireNonNull(getClass().getResource(symbol)));
        } catch (IOException e) {
            e.printStackTrace();
            this.symbol = null;
        }
        this.salud = salud;
        this.ataque = ataque;
        this.defensa = defensa;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void mover(int x, int y) {
        this.x = x;
        this.y = y;

        System.out.println(nombre + " se mueve a (" + x + ", " + y + ")");
    }

    @Override
    public int atacar() {
        return ataque;
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public void recibirAtaque(int cantidad) {
        int ataqueReal = Math.max(0, cantidad - defensa);
        salud -= ataqueReal;
        System.out.println(nombre + " recibe " + ataqueReal + " de daño. Salud restante: " + salud);
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud += salud;
    }
}