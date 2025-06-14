package model.aliado;

import model.Personaje;

public class LordValen extends Personaje {
    public LordValen(String nombre, int salud, int ataque, int defensa, String symbol) {
        super(nombre, salud, ataque, defensa, symbol);
    }

    @Override
    public int atacar() {
        System.out.println(nombre + " lanza un corte con su espada de sangre.");
        return ataque;
    }

    @Override
    public void usar() {
        System.out.println(nombre + " manipula la sangre enemiga para debilitarlos.");
    }
}
