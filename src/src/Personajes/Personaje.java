package Personajes;

public class Personaje {
    protected int vida;
    protected int ataque;
    protected String nombre;
    protected String especialidad;

    public Personaje(){

    }

    public Personaje(int vida, int ataque, String nombre, String especialidad) {
        this.vida = vida;
        this.ataque = ataque;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
