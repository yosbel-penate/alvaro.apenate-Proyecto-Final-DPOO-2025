package Mundo;

public class NocturneCitadel extends Mundo{

    String trampa;
    String recompensa;


    public NocturneCitadel(){
    }

    public NocturneCitadel(String ambiente, String trampa, String recompensa) {
        super(ambiente);
        this.trampa = trampa;
        this.recompensa = recompensa;
    }

    public String getTrampa() {
        return trampa;
    }

    public void setTrampa(String trampa) {
        this.trampa = trampa;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }
}
