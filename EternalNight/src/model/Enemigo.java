package model;

public abstract class Enemigo extends Personaje {

    public Enemigo(String nombre, int salud, int ataque, int defensa, String symbol) {
        super(nombre, salud, ataque, defensa, symbol);
    }

}