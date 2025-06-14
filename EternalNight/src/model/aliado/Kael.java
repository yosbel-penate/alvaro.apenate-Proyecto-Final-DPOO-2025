package model.aliado;

import model.Personaje;

public class Kael extends Personaje {
    public Kael(String nombre, int salud, int ataque, int defensa, String symbol) {
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