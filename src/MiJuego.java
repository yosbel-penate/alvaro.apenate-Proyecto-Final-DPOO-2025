package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.fuente.Juego;

public class MiJuego extends Application {
    private Juego juego;


    @Override
public void start(Stage primaryStage) {
    juego = new Juego();
    Scene scene = new Scene(juego.getGui(), 800, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Mi Juego");
    primaryStage.show();
}


    @Override
    public void stop() {
        juego.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

