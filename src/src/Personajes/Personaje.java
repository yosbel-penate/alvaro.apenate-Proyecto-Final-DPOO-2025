package Personajes;

public abstract class Personaje {
    protected int vida;
    protected int ataque;
    protected String nombre;
    protected String historia;

    public Personaje(){

    }

    public abstract String moverse();

    public abstract String morir();

}




