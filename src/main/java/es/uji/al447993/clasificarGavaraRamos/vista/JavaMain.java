package es.uji.al447993.clasificarGavaraRamos.vista;

import es.uji.al447993.clasificarGavaraRamos.modelo.lecturaFicheros.SongReader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        Label labelSuperior = new Label("Selecciona un método para calcular la distancia:");
        labelSuperior.setStyle("-fx-font-weight: bold;");

        ToggleGroup grupo = new ToggleGroup();
        ToggleButton dist1 = new ToggleButton("EuclideanDistance");
        ToggleButton dist2 = new ToggleButton("ManhattanDistance");

// Estilo circular/redondeado para botones
        String estiloBoton = "-fx-background-radius: 20; -fx-padding: 5 10;";
        dist1.setStyle(estiloBoton);
        dist2.setStyle(estiloBoton);

        dist1.setToggleGroup(grupo);
        dist2.setToggleGroup(grupo);

// HBox para poner los botones uno al lado del otro
        HBox botonesSup = new HBox(15, dist1, dist2);
        botonesSup.setAlignment(Pos.CENTER);

// VBox contenedor que agrupa el Texto + el HBox de botones
        VBox contenedorSuperior = new VBox(15, labelSuperior, botonesSup);
        contenedorSuperior.setAlignment(Pos.CENTER);
        contenedorSuperior.setStyle("-fx-background-color: white;");

        bloqueSuperior.getChildren().add(contenedorSuperior);


// --- BLOQUE INFERIOR IZQUIERDO ---
        Label labelInferior = new Label("Selecciona una forma de recomendar:");
        labelInferior.setStyle("-fx-font-weight: bold;");

        ToggleGroup grupo2 = new ToggleGroup();
        ToggleButton algo1 = new ToggleButton("Kmeans");
        ToggleButton algo2 = new ToggleButton("KNN");

        algo1.setStyle(estiloBoton);
        algo2.setStyle(estiloBoton);

        algo1.setToggleGroup(grupo2);
        algo2.setToggleGroup(grupo2);

        // HBox para los botones inferiores
        HBox botonesInf = new HBox(15, algo1, algo2);
        botonesInf.setAlignment(Pos.CENTER);

        // VBox contenedor para el bloque inferior
        VBox contenedorInferior = new VBox(15, labelInferior, botonesInf);
        contenedorInferior.setAlignment(Pos.CENTER);
        contenedorInferior.setStyle("-fx-background-color: white;");

        bloqueInferior.getChildren().add(contenedorInferior);

        // -------------------------------------------------------------------------------------------------------------------------
        // Lado Derecho
        // Espaciado de 10 entre elementos
        VBox seccionDerecha = new VBox(10);
        HBox.setHgrow(seccionDerecha, Priority.ALWAYS);
        seccionDerecha.setPadding(new Insets(10));
        seccionDerecha.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: white;");

        Label tituloDerecha = new Label("Songs Title");
        tituloDerecha.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");


        ListView<String> listaCanciones = new ListView<>();
        VBox.setVgrow(listaCanciones, Priority.ALWAYS);

        SongReader reader = new SongReader();

        listaCanciones.getItems().addAll(reader.loadSongs());


        Button btnSugerir = new Button("Sugerir");
        btnSugerir.setStyle("-fx-background-radius: 20; -fx-padding: 8 20;");
        btnSugerir.setOnAction(e -> {
            abrirVentanaSugerir  nuevaVentana = new abrirVentanaSugerir();
            nuevaVentana.show();
        });

        // Para poner el botón a la derecha, usamos un HBox como contenedor
        HBox contenedorBoton = new HBox(btnSugerir);
        contenedorBoton.setAlignment(Pos.CENTER_RIGHT);

        // Añadimos todo a la sección derecha
        seccionDerecha.getChildren().addAll(tituloDerecha, listaCanciones, contenedorBoton);


        // --------------------------------------------------------------------------------------------------------------------------
        // Principal
        // Añadimos las dos mitades al principal
        principal.getChildren().addAll(seccionIzquierda, seccionDerecha);

        // Diseño del Principal
        principal.setPadding(new Insets(3, 3, 3, 3));
        principal.setSpacing(3);

        Scene scene = new Scene(principal, 700, 500);
        this.setScene(scene);
        this.setTitle("Programación Avanzada");
    }
}
