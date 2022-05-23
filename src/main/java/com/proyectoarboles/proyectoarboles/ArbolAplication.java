package com.proyectoarboles.proyectoarboles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Clase ArbolAplication parte del uso de JavaFx
 *
 * @author Rodrigo Natali Jonatan Angelica
 */
public class ArbolAplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ArbolAplication.class.getResource("VistaArboles.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AB");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
