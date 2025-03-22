package Personajes.Enemigos.Antagonistas;

import Personajes.Enemigos.IEnemigo;
import Personajes.Personaje;

public class Jefe extends Personaje implements IEnemigo {


    public Jefe() {
    }


    @Override
    public String moverse() {
        return "me muevo xd";
    }

    @Override
    public String morir() {
        return "q dolor";
    }


    @Override
    public String intimidar() {
        return "que mira bobo";
    }

    @Override
    public String atacar() {
        return "";
    }
}
