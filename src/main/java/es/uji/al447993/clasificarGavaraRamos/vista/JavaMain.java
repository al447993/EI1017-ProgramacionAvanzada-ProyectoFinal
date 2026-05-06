package es.uji.al447993.clasificarGavaraRamos.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JavaMain extends Stage {
    public JavaMain() {

        HBox principal = new HBox();
        principal.setStyle("-fx-background-color: grey;");

        // Lado Izquierdo   (ALWAYS ocupa el 50% de la pantalla)
        VBox seccionIzquierda = new VBox();
        HBox.setHgrow(seccionIzquierda, Priority.ALWAYS);

        // Creacion de Bloque superior Izquierdo
        StackPane bloqueSuperior = new StackPane();
        VBox.setVgrow(bloqueSuperior, Priority.ALWAYS);
        bloqueSuperior.setStyle("-fx-border-color: black; -fx-border-width: 2;");

        // Creacion de Bloque inferior Izquierdo
        StackPane bloqueInferior = new StackPane();
        VBox.setVgrow(bloqueInferior, Priority.ALWAYS);
        bloqueInferior.setStyle("-fx-border-color: black; -fx-border-width: 2;");

        // Agregamos al lado Izquierdo
        seccionIzquierda.getChildren().addAll(bloqueSuperior, bloqueInferior);

        //----------------------------------------------------------------------------------------------------------------------------
        // Bloque superior Izquierdo

        ToggleGroup grupo = new ToggleGroup();
        ToggleButton dist1 = new ToggleButton("EuclideanDistance");
        ToggleButton dist2 = new ToggleButton("ManhattanDistance");

        dist1.setOnAction(e -> System.out.println("Selecciona EuclideanDistance"));
        dist2.setOnAction(e -> System.out.println("Selecciona ManhattanDistance"));

        dist1.setToggleGroup(grupo);
        dist2.setToggleGroup(grupo);

        VBox distancia = new VBox(10, dist1, dist2);
        distancia.setAlignment(Pos.CENTER);
        distancia.setStyle("-fx-background-color: white;");

        // Agregamos el VBox al bloque superior Izquierdo
        bloqueSuperior.getChildren().add(distancia);

        // Diseño Laodo Izquierdo
        seccionIzquierda.setSpacing(3);

        // ---------------------------------------------------------------------------------------------------------------------------
        // Bloque inferior Izquierdo

        ToggleGroup grupo2 = new ToggleGroup();
        ToggleButton algo1 = new ToggleButton("Kmeans");
        ToggleButton algo2 = new ToggleButton("KNN");

        algo1.setOnAction(e -> System.out.println("Selecciona Kmeans"));
        algo2.setOnAction(e -> System.out.println("Selecciona KNN"));

        algo1.setToggleGroup(grupo2);
        algo2.setToggleGroup(grupo2);

        VBox algoritmo = new VBox(10, algo1, algo2);
        algoritmo.setAlignment(Pos.CENTER);
        algoritmo.setStyle("-fx-background-color: white;");

        bloqueInferior.getChildren().add(algoritmo);

        // -------------------------------------------------------------------------------------------------------------------------
        // Lado Derecho
        StackPane seccionDerecha = new StackPane();
        HBox.setHgrow(seccionDerecha, Priority.ALWAYS);
        seccionDerecha.setStyle("-fx-border-color: black; -fx-border-width: 2;");


        // --------------------------------------------------------------------------------------------------------------------------
        // Principal
        // Añadimos las dos mitades al principal
        principal.getChildren().addAll(seccionIzquierda, seccionDerecha);

        // Diseño del Principal
        principal.setPadding(new Insets(3, 3, 3, 3));
        principal.setSpacing(3);

        Scene scene = new Scene(principal, 700, 500);
        this.setScene(scene);
        this.setTitle("Pogramacion Avanzada");
    }
}
