package es.uji.al447993.clasificarGavaraRamos.vista;

import es.uji.al447993.clasificarGavaraRamos.modelo.Modelo;
import es.uji.al447993.clasificarGavaraRamos.controlador.implementacionControlador;
import es.uji.al447993.clasificarGavaraRamos.modelo.implementacionModelo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class JavaFXApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        VBox layout = new VBox(30);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        layout.setStyle("""
                -fx-background-color: linear-gradient(to bottom right, #1e1e2f, #2d2d44);
                """);

        VBox panelCentral = new VBox(25);
        panelCentral.setAlignment(Pos.CENTER);
        panelCentral.setPadding(new Insets(40));

        panelCentral.setMaxWidth(700);

        panelCentral.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 25;
                -fx-border-radius: 25;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 20, 0, 0, 6);
                """);

        // Icono principal
        Image princi = new Image(getClass().getResourceAsStream("/images/icono.png"));
        ImageView imageView = new ImageView(princi);

        Label icono = new Label("", imageView);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        icono.setStyle("""
                -fx-font-size: 70px;
                """);

        Label mensaje = new Label("Proyecto Programación Avanzada");

        // Salto de linea auto
        mensaje.setWrapText(true);
        mensaje.setStyle("""
                -fx-font-size: 34px;
                -fx-font-weight: bold;
                -fx-text-fill: #1e1e2f;
                """);


        Label descripcion = new Label("Sistema inteligente de recomendación de canciones\n" + "utilizando algoritmos KNN y KMeans");
        descripcion.setTextAlignment(TextAlignment.CENTER);
        descripcion.setStyle("""
                -fx-font-size: 16px;
                -fx-text-fill: #555555;
                """);

        Label nombres = new Label("Desarrollado por Sonia Gavara y Julián Ramos");
        nombres.setStyle("""
                -fx-font-size: 15px;
                -fx-font-style: italic;
                -fx-text-fill: #777777;
                """);

        Button btnSiguiente = new Button("Continuar");
        btnSiguiente.setCursor(Cursor.HAND);
        String estiloNormal = """
                -fx-background-color: #6c63ff;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 16px;
                -fx-background-radius: 30;
                -fx-padding: 14 40;
                -fx-cursor: hand;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 4);
                """;
        String estiloHover = """
                -fx-background-color: #5848ff;
                -fx-text-fill: white;
                -fx-font-weight: bold;
                -fx-font-size: 16px;
                -fx-background-radius: 30;
                -fx-padding: 14 40;
                -fx-cursor: hand;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 14, 0, 0, 6);
                """;

        btnSiguiente.setStyle(estiloNormal);
        btnSiguiente.setOnMouseEntered(e -> btnSiguiente.setStyle(estiloHover));
        btnSiguiente.setOnMouseExited(e -> btnSiguiente.setStyle(estiloNormal));

        btnSiguiente.setOnAction(e -> {
            Vista mainWindow = new implementacionVista();
            Modelo modelo = new implementacionModelo();
            new implementacionControlador(modelo, mainWindow);
            mainWindow.show();
            primaryStage.close();
        });

        // Agregas las cosas
        panelCentral.getChildren().addAll(icono, mensaje, descripcion, nombres, btnSiguiente);

        layout.getChildren().add(panelCentral);
        Scene scene = new Scene(layout, 1100, 700);
        primaryStage.setTitle("Programación Avanzada");
        primaryStage.setScene(scene);
        primaryStage.show();
        Image imagen = new Image(getClass().getResourceAsStream("/images/icono.png"));
        primaryStage.getIcons().add(imagen);
    }

}
