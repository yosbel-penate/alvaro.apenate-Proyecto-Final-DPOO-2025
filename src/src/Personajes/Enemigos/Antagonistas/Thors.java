package Personajes.Enemigos.Antagonistas;

import Personajes.Enemigos.IEnemigo;

public class Thors extends Jefe implements IEnemigo {

    String habilidadUnica;

    public Thors() {
    }

    public Thors(String habilidadUnica) {
        this.habilidadUnica = habilidadUnica;
    }
}
