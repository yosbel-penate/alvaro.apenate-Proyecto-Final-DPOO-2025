package Personajes.Neutral;

public class Alquimista extends Neutral{

    int cantidadDeObjetos;
    int precioDeObjetos;

    public Alquimista(int cantidadDeObjetos, int precioDeObjetos) {
        this.cantidadDeObjetos = cantidadDeObjetos;
        this.precioDeObjetos = precioDeObjetos;
    }

    public String venderObjeto(){
        return "buena compra";
    }
}
