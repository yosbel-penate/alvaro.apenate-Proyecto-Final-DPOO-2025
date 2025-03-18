package Mundo;

public abstract class Mundo {
    String ambiente;

    public Mundo() {
    }

    public Mundo(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }
}
