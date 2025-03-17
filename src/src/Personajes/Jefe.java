package Personajes;

public class Jefe extends Enemigo {
    int pelos;
    int dientes;


    public Jefe() {
    }

    public Jefe(int vida, int ataque, String nombre, String especialidad, String debilidad, int pelos, int dientes) {
        super(vida, ataque, nombre, especialidad, debilidad);
        this.pelos = pelos;
        this.dientes = dientes;
    }

    public String intimidar() {
        String llamado = "que mirah bobo";
        return llamado;
    }



}
