package model.aliado;

import model.Personaje;

public class Lyra extends Personaje {
    public Lyra(String nombre, int salud, int ataque, int defensa, String symbol) {
        super(nombre, salud, ataque, defensa, symbol);
    }

    @Override
    public int atacar() {
        return ataque;
    }

    @Override
    public void usar() {

    }
}