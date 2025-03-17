package Mundo;

public class Sanguinia extends Mundo{

    String mecanica;
    String jefe;

    public Sanguinia(){
    }

    public Sanguinia(String ambiente, String mecanica, String jefe) {
        super(ambiente);
        this.mecanica = mecanica;
        this.jefe = jefe;
    }

    public String getMecanica() {
        return mecanica;
    }

    public void setMecanica(String mecanica) {
        this.mecanica = mecanica;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }
}
