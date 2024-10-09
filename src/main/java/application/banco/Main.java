package application.banco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    /*
    La clase Main del programa para lanzar la aplicación
    En ella, se utiliza el cargador del FXML (FXMLLoader) con el recurso en cuestion (nuestro fxml) y lo muestra.

     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("banco.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Banck!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}