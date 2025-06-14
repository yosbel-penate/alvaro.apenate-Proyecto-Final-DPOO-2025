package model.enemy;

import model.Enemigo;

public class LanzadoresHachas extends Enemigo {
    private final String tipo;

    public LanzadoresHachas(String nombre, int salud, int ataque, int defensa, String tipo, String symbol) {
        super(nombre, salud, ataque, defensa, symbol);
        this.tipo = tipo;
    }


    @Override
    public void usar() {

    }
}


