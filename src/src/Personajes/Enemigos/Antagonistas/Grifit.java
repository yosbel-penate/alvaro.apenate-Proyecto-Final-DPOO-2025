package Personajes.Enemigos.Antagonistas;

import Personajes.Enemigos.IEnemigo;

public class Grifit extends Jefe implements IEnemigo {

    String habilidadUnica;

    public Grifit() {
    }

    public Grifit(String habilidadUnica) {
        this.habilidadUnica = habilidadUnica;
    }

    @Override
    public String atacar() {
        return "";
    }
}
