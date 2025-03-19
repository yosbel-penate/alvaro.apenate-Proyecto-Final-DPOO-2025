package Mundo;

public abstract class Mundo {
    protected String ambiente;

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
