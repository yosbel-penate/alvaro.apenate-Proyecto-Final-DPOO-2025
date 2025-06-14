package model;

import contract.INotify;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private Personaje personaje;
    private List<VialSangre> objetos = new ArrayList<>();
    private INotify listener;


    public Inventario(Personaje personaje) {
        this.personaje = personaje;
    }


    public void addObjeto(VialSangre objeto){
        objetos.add(objeto);
        notificarCambios();
    }

    public void setListener(INotify notify) {
        this.listener = notify;
    }

    public void curar(VialSangre item) {
        personaje.setSalud(20);
        objetos.remove(item);
        notificarCambios();
    }

    public List<VialSangre> getObjetos() {
        return objetos;
    }

    private void notificarCambios() {
        if (listener != null) {
            listener.inventarioActualizado();
        }
    }

    public Personaje getPersonaje() {
        return personaje;
    }
}
