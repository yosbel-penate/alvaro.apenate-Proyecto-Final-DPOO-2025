package Personajes;

public class Jugador extends Personaje implements Ipersonaje {


    public Jugador(){

    }

    @Override
    public String atacar() {
        return "te estoy golpeando";
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
