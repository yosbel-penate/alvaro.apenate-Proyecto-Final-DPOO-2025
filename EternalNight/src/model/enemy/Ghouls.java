package model.enemy;

import model.Enemigo;

public class Ghouls extends Enemigo {
    private final String tipo;

    public Ghouls(String nombre, int salud, int ataque, int defensa, String tipo, String symbol) {
        super(nombre, salud, ataque, defensa, symbol);
        this.tipo = tipo;
    }


    @Override
    public void usar() {

    }
}
