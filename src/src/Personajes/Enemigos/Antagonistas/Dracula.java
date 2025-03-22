package Personajes.Enemigos.Antagonistas;

import Personajes.Enemigos.IEnemigo;

public class Dracula extends Jefe implements IEnemigo {
    String habilidadUnica;

    public Dracula() {
    }

    public Dracula(String habilidadUnica) {
        this.habilidadUnica = habilidadUnica;
    }

    @Override
    public String atacar() {
        return "";
    }
}
