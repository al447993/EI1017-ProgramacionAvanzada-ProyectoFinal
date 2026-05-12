package es.uji.al447993.clasificarGavaraRamos.vista;

import es.uji.al447993.clasificarGavaraRamos.controlador.Controlador;
import es.uji.al447993.clasificarGavaraRamos.controlador.implementacionControlador;
import es.uji.al447993.clasificarGavaraRamos.modelo.Modelo;
import es.uji.al447993.clasificarGavaraRamos.modelo.implementacionModelo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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

        // 1. Contenedor Principal
        VBox layout = new VBox(25);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        // Sintaxis para degradar colores
        layout.setStyle("-fx-background-color: linear-gradient(to bottom right, #ffffff, #e6f2ff);");

        Label mensaje = new Label("¡Bienvenido a nuestro Proyecto!");
        mensaje.setStyle(
                "-fx-font-size: 32px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #1a2a6c; " + // Azul oscuro elegante
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );


        Label nombres = new Label("Presentado por: Sonia Gavara y Julián Ramos");
        nombres.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-text-fill: #555555; " +
                        "-fx-font-style: italic;"
        );

        // Cursor.HAND para que cuando estemos en el boton se ponga la manito.
        Button btnSiguiente = new Button("Continuar");
        btnSiguiente.setCursor(Cursor.HAND);
        String estiloNormal =
                "-fx-background-color: #005088; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 30; " + // Muy redondeado
                        "-fx-padding: 12 40 12 40; " +
                        "-fx-font-size: 15px; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);";

        String estiloHover = estiloNormal + "-fx-background-color: #0070c0;";

        btnSiguiente.setStyle(estiloNormal);

        // Efectos visuales al pasar el mouse
        btnSiguiente.setOnMouseEntered(e -> btnSiguiente.setStyle(estiloHover));
        btnSiguiente.setOnMouseExited(e -> btnSiguiente.setStyle(estiloNormal));

        // Funcionamiento del Boton.
        btnSiguiente.setOnAction(e -> {
            JavaMain mainWindow = new JavaMain();
            mainWindow.show();
            primaryStage.close();
        });

        layout.getChildren().addAll(mensaje, nombres, btnSiguiente);

        Scene scene = new Scene(layout, 700, 500);
        primaryStage.setTitle("Programación Avanzada");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
