package es.uji.al447993.clasificarGavaraRamos.vista;

import es.uji.al447993.clasificarGavaraRamos.controlador.Controlador;
import es.uji.al447993.clasificarGavaraRamos.controlador.implementacionControlador;
import es.uji.al447993.clasificarGavaraRamos.modelo.Modelo;
import es.uji.al447993.clasificarGavaraRamos.modelo.implementacionModelo;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Modelo modelo = new implementacionModelo();
        Vista view = new implementacionVista();
        Controlador controlador = new implementacionControlador();

        modelo.setView(view);
        view.setModel(modelo);

        //Creacion de Menbsaje con Botones
        Label mensaje = new Label("¡Bienvenido a nuestro Proyecto de Programación!");
        Label nombres = new Label("Presentado por : Sonia Gavara y Julián Ramos");
        Button btnSiguiente = new Button("Continuar");

        // Diseño de Texto y de Boton
        mensaje.setStyle("-fx-font-size: 28px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #2c3e50; " +
                "-fx-font-family: 'cursive';");

        nombres.setStyle("+fx-font-size: 15px; "
                + "-fx-font-weight: bold;");

        btnSiguiente.setStyle("-fx-padding: 10 30 10 30; -fx-font-size: 14px;");

        btnSiguiente.setOnAction(e -> {

            // Abrimos la ventana del JavaMain
            JavaMain mainWindow = new JavaMain();
            mainWindow.show();

            primaryStage.close();
        });

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(mensaje, nombres, btnSiguiente);


        Scene scene = new Scene(layout, 700, 500);
        primaryStage.setTitle("Programación Avanzada");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
