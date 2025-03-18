package Personajes;

public abstract class Personaje {
    protected int vida;
    protected int ataque;
    protected String nombre;
    protected String faccion;
    protected String historia;

    public Personaje(){

    }

    public abstract String atacar();

    public abstract String moverse();

    public abstract String morir();




}




