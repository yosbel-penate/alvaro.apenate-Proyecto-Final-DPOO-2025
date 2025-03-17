package Personajes;

public class Enemigo extends Personaje {

    String debilidad;


    public Enemigo(){

    }

    public Enemigo(int vida, int ataque, String nombre, String especialidad, String debilidad) {
        super(vida, ataque, nombre, especialidad);
        this.debilidad = debilidad;
    }

    public String getDebilidad() {
        return debilidad;
    }

    public void setDebilidad(String debilidad) {
        this.debilidad = debilidad;
    }
}
