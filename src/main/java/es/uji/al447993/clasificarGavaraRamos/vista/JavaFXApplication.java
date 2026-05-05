package es.uji.al447993.clasificarGavaraRamos.vista;

import es.uji.al447993.clasificarGavaraRamos.controlador.Controlador;
import es.uji.al447993.clasificarGavaraRamos.controlador.implementacionControlador;
import es.uji.al447993.clasificarGavaraRamos.modelo.Modelo;
import es.uji.al447993.clasificarGavaraRamos.modelo.implementacionModelo;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
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

        VBox layout = new VBox();
        ToggleGroup grupo = new ToggleGroup();
        ToggleButton radio1 = new ToggleButton("EuclideanDistance");
        ToggleButton radio2 = new ToggleButton("ManhattanDistance");

        radio1.setOnAction(e -> System.out.println("Selecciona EuclideanDistance"));
        radio2.setOnAction(e -> System.out.println("Selecciona ManhattanDistance"));

        radio1.setToggleGroup(grupo);
        radio2.setToggleGroup(grupo);

        Label label = new Label("Hola Mundo :D");
        Button button = new Button("Un Boton :D");

        Label label2 = new Label("Seleccionador de distancias: ");

        HBox hboxDistancia = new HBox(label2, radio1, radio2);
        hboxDistancia.setSpacing(10);

        hboxDistancia.setLayoutX(500);
        hboxDistancia.setLayoutY(500);

        label.setLayoutX(1);
        label.setLayoutY(1);
        button.setLayoutX(50);
        button.setLayoutY(50);

        layout.getChildren().addAll(label, button, hboxDistancia);

        primaryStage.setScene(new Scene(layout, 500, 500));
        primaryStage.setTitle("JavaFXApp");
        primaryStage.show();
    }

}
