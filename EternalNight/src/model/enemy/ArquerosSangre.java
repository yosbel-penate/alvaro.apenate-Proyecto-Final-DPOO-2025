package model.enemy;

import model.Enemigo;

public class ArquerosSangre extends Enemigo {
    private final String tipo;

    public ArquerosSangre(String nombre, int salud, int ataque, int defensa, String tipo, String symbol) {
        super(nombre, salud, ataque, defensa, symbol);
        this.tipo = tipo;
    }

    @Override
    public void usar() {

    }
}

