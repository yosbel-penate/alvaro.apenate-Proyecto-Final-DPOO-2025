package Mundo;

public class Tenebris extends Mundo{

     String enemigoUnico ;
     String peligro ;


     public Tenebris(){
     }

    public Tenebris(String ambiente, String enemigounico, String peligro) {
        super(ambiente);
        this.enemigoUnico = enemigounico;
        this.peligro = peligro;
    }

    public String getEnemigounico() {
        return enemigoUnico;
    }

    public void setEnemigounico(String enemigounico) {
        this.enemigoUnico = enemigounico;
    }

    public String getPeligro() {
        return peligro;
    }

    public void setPeligro(String peligro) {
        this.peligro = peligro;
    }
}
