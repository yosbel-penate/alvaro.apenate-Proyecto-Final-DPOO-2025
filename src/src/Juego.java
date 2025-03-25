import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;


public class Juego {
    public static void main(String[] args) {

        String nombre;
        String descripcion;
        double duracion;


        JFrame ventana = new JFrame("Eternal Nights");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        ventana.add(panel);

        ventana.setLocationRelativeTo(null);

        ventana.setVisible(true);




        //no se guien por lo que esta escrito , aun no es definitivo
        //ola
        
        


    }
}