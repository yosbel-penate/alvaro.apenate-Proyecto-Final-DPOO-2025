package Items;

public abstract class Item {
    protected String tipo;
    protected String nombre;
    protected String descripcion;
    protected int cantidad;
    protected double peso;
    protected int precio;
    protected String utilidad;
    protected String rareza;
    protected boolean equipable;
    protected boolean usable;


    protected abstract int aumentarCantidad();
    protected abstract int disminuirCantidad();
}
