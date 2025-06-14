package model.mapas;

import model.Objeto;
import model.Personaje;

public class Obstaculo extends Objeto {

    public Obstaculo(String nombre, String symbol) {
        super(nombre, symbol);
    }

    @Override
    public void usar(Personaje personaje) {

    }
}
