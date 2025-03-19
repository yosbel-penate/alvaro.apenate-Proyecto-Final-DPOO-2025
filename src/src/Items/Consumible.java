package Items;

public class Consumible extends Item{
    String efecto;
    int durabilidad;

    @Override
    public int aumentarCantidad() {
        return 0;
    }

    @Override
    public int disminuirCantidad() {
        return 0;
    }
}
