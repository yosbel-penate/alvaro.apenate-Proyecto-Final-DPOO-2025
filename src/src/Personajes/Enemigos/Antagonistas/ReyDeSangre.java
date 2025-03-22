package Personajes.Enemigos.Antagonistas;

import Personajes.Enemigos.IEnemigo;

public class ReyDeSangre extends Jefe implements IEnemigo {

    String habilidadUnica;

    public ReyDeSangre() {
    }

    public ReyDeSangre(String habilidadUnica) {
        this.habilidadUnica = habilidadUnica;
    }
}
