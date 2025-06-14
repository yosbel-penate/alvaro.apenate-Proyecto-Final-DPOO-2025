package controller;

import model.Enemigo;
import model.Neutral;
import model.Objeto;
import model.Personaje;
import model.aliado.Kael;
import model.aliado.LordValen;
import model.aliado.Lyra;
import model.enemy.*;
import model.mapas.Obstaculo;
import model.mapas.Recurso;
import model.neutral.Alquimista;
import pattern.ParentSingleton;

import java.util.ArrayList;

public class Game extends ParentSingleton {

    public ArrayList<Personaje> getAliados() {
        ArrayList<Personaje> aliados = new ArrayList<>();
        aliados.add(new LordValen("Lord Valen", 1200, 150, 100, "/resource/personajes/lord_valen.png"));
        aliados.add(new Kael("Kael", 1200, 150, 100, ""));
        aliados.add(new Lyra("Lyra", 1200, 150, 100, ""));
        return aliados;
    }

    public ArrayList<Enemigo> getEnemigos() {
        ArrayList<Enemigo> enemigos = new ArrayList<>();
        enemigos.add(new Ghouls("Lobos Vampíricos", 1000, 100, 50, "Melee", "/resource/personajes/lobos_vampiricos.png"));
        enemigos.add(new CaballerosCadaver("Caballeros Cadaver", 1500, 10, 200, "Melee", ""));
        enemigos.add(new ArquerosSangre("Arqueros de Sangre", 1500, 10, 200, "Rango", ""));
        enemigos.add(new LanzadoresHachas("Lanzadores de Hachas", 1500, 10, 200, "Rango", ""));
        enemigos.add(new MagosCarmesi("Mago Carmesi", 2000, 300, 700, "Magicos", ""));
        enemigos.add(new Nigromantes("Nigromantes", 1500, 10, 200, "Magicos", ""));
        return enemigos;
    }

    public ArrayList<Neutral> getNeutrales() {
        ArrayList<Neutral> neutrales = new ArrayList<>();
        neutrales.add(new Alquimista("El Alquimista", ""));
        neutrales.add(new Alquimista("El Espectro", ""));
        return  neutrales;
    }

    public ArrayList<Objeto> getObjetos() {
        ArrayList<Objeto> objetos = new ArrayList<>();
        objetos.add(new Obstaculo("Piedra", "/resource/mapas/rocoso.png"));
        objetos.add(new Obstaculo("Camino", "/resource/mapas/tierra.png"));
        objetos.add(new Obstaculo("Trampa", "/resource/mapas/trampa.png"));
        objetos.add(new Recurso("Vial de Sangre", "/resource/recursos/VialSangre.png"));
        return objetos;
    }

    //public ArrayList<>

}
