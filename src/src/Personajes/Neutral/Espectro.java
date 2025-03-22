package Personajes.Neutral;

public class Espectro extends Neutral{

    int cantidadDeMisiones;
    String riesgoDeMisiones;
    int recompensaPorMision;

    public Espectro(int cantidadDeMisiones, String riesgoDeMisiones, int recompensaPorMision) {
        this.cantidadDeMisiones = cantidadDeMisiones;
        this.riesgoDeMisiones = riesgoDeMisiones;
        this.recompensaPorMision = recompensaPorMision;
    }

    public String ofrecerMision(){
        return "aqui tienes";
    }
}
