package Personajes.Enemigos.Antagonistas;

import Personajes.Enemigos.IEnemigo;

public class Esra extends Jefe implements IEnemigo {
    String habilidadUnica;

    public Esra() {
    }

    public Esra(String habilidadUnica) {
        this.habilidadUnica = habilidadUnica;
    }

    @Override
    public String atacar() {
        return "";
    }
}
