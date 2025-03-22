package Personajes.Protagonistas;

import Personajes.Personaje;

public class Jugador extends Personaje implements IJugador {


    public Jugador(){

    }
    public String atacar(){
        return "muereteeeeee";
    }

    @Override
    public String moverse() {
        return "que bien se siente caminar";
    }

    @Override
    public String morir() {
        return "ay me duele";
    }

    @Override
    public void interacctuar() {

    }

    @Override
    public void abrirInventario() {

    }

    @Override
    public void abrirMapa() {

    }
}
