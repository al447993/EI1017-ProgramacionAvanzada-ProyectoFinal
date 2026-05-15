package es.uji.al447993.clasificarGavaraRamos.vista;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class implementacionVista extends Stage implements Vista {

    private ListView<String> listaCanciones = new ListView<>();
    private ToggleGroup grupoDistancia;
    private ToggleGroup grupoAlgoritmo;
    private Button btnSugerir = new Button("Sugerir");

    public implementacionVista() {

        HBox principal = new HBox();
        principal.setSpacing(15);
        principal.setPadding(new Insets(15));
        principal.setStyle("""
                -fx-background-color: linear-gradient(to bottom right, #1e1e2f, #2d2d44);
                """);


        // =============================================================================================================
        // Zona Izquierda
        VBox seccionIzquierda = new VBox(15);
        HBox.setHgrow(seccionIzquierda, Priority.ALWAYS);

        // ---------- BLOQUE SUPERIOR ----------
        VBox bloqueSuperior = new VBox(20);
        VBox.setVgrow(bloqueSuperior, Priority.ALWAYS);

        bloqueSuperior.setAlignment(Pos.CENTER);
        bloqueSuperior.setPadding(new Insets(25));

        bloqueSuperior.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 20;
                -fx-border-radius: 20;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0, 0, 4);
                """);

        Label labelSuperior = new Label("Método de Distancia");
        labelSuperior.setStyle("""
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-text-fill: #333;
                """);

        ToggleGroup grupo = new ToggleGroup();
        ToggleButton dist1 = new ToggleButton("Euclidean");
        ToggleButton dist2 = new ToggleButton("Manhattan");

        String estiloBoton = """
                -fx-background-color: #e4e4e4;
                -fx-background-radius: 30;
                -fx-font-size: 14px;
                -fx-padding: 10 20;
                -fx-cursor: hand;
                """;

        String estiloSeleccionado = """
                -fx-background-color: #6c63ff;
                -fx-text-fill: white;
                -fx-background-radius: 30;
                -fx-font-size: 14px;
                -fx-padding: 10 20;
                """;

        dist1.setStyle(estiloBoton);
        dist2.setStyle(estiloBoton);

        dist1.setToggleGroup(grupo);
        dist2.setToggleGroup(grupo);

        dist1.selectedProperty().addListener((obs, oldV, selected) -> {
            dist1.setStyle(selected ? estiloSeleccionado : estiloBoton);
        });

        dist2.selectedProperty().addListener((obs, oldV, selected) -> {
            dist2.setStyle(selected ? estiloSeleccionado : estiloBoton);
        });

        HBox botonesSup = new HBox(15, dist1, dist2);
        botonesSup.setAlignment(Pos.CENTER);

        bloqueSuperior.getChildren().addAll(labelSuperior, botonesSup);

        // ---------- BLOQUE INFERIOR ----------
        VBox bloqueInferior = new VBox(20);
        VBox.setVgrow(bloqueInferior, Priority.ALWAYS);

        bloqueInferior.setAlignment(Pos.CENTER);
        bloqueInferior.setPadding(new Insets(25));

        bloqueInferior.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 20;
                -fx-border-radius: 20;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0, 0, 4);
                """);

        Label labelInferior = new Label("Forma de Recomendación");
        labelInferior.setStyle("""
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-text-fill: #333;
                """);

        ToggleGroup grupo2 = new ToggleGroup();

        ToggleButton algo1 = new ToggleButton("Por Canción");
        ToggleButton algo2 = new ToggleButton("Por Género");

        algo1.setStyle(estiloBoton);
        algo2.setStyle(estiloBoton);

        algo1.setToggleGroup(grupo2);
        algo2.setToggleGroup(grupo2);


        // Lo que hace es cuando seleccionamos algo? va a llamar a su propiedad (Toggle) y ejecutar el addListener
        // funcion lambda siempre 3 parametros se ignoran los dos primeros.
        // Lo importante es el selected, que si es verdadero pondra estiloSeleccionado si no estiloBoton.

        algo1.selectedProperty().addListener((obs, oldV, selected) -> {
            algo1.setStyle(selected ? estiloSeleccionado : estiloBoton);
        });

        algo2.selectedProperty().addListener((obs, oldV, selected) -> {
            algo2.setStyle(selected ? estiloSeleccionado : estiloBoton);
        });

        HBox botonesInf = new HBox(15, algo1, algo2);
        botonesInf.setAlignment(Pos.CENTER);

        bloqueInferior.getChildren().addAll(labelInferior, botonesInf);

        seccionIzquierda.getChildren().addAll(bloqueSuperior, bloqueInferior);

        // =============================================================================================================
        // Zona Derecha
        VBox seccionDerecha = new VBox(15);
        HBox.setHgrow(seccionDerecha, Priority.ALWAYS);

        seccionDerecha.setPadding(new Insets(20));
        seccionDerecha.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 20;
                -fx-border-radius: 20;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0, 0, 4);
                """);
        Label tituloDerecha = new Label("🎵 Songs");
        tituloDerecha.setStyle("""
                -fx-font-size: 22px;
                -fx-font-weight: bold;
                -fx-text-fill: #333;
                """);
        listaCanciones.setStyle("""
                -fx-background-radius: 15;
                -fx-border-radius: 15;
                -fx-font-size: 14px;
                """);

        VBox.setVgrow(listaCanciones, Priority.ALWAYS);

        btnSugerir.setText("Sugerir");
        btnSugerir.setStyle("""
                -fx-background-color: #6c63ff;
                -fx-text-fill: white;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-background-radius: 30;
                -fx-padding: 12 25;
                -fx-cursor: hand;
                """);

        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setStyle("""
                -fx-background-color: #ff4d4d;
                -fx-text-fill: white;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-background-radius: 30;
                -fx-padding: 12 25;
                -fx-cursor: hand;
                """);

        btnCerrar.setOnAction(e -> {
            this.close();
        });

        HBox contenedorBoton = new HBox(10, btnSugerir, btnCerrar);
        contenedorBoton.setAlignment(Pos.CENTER_RIGHT);

        seccionDerecha.getChildren().addAll(
                tituloDerecha,
                listaCanciones,
                contenedorBoton
        );

        this.grupoDistancia = grupo;
        this.grupoAlgoritmo = grupo2;

        // =============================================================================================================
        // Principal

        principal.getChildren().addAll(seccionIzquierda, seccionDerecha);
        Scene scene = new Scene(principal, 900, 550);
        this.setScene(scene);
        this.setTitle("Programacion Avanzada");

        // Icono
        Image imagen = new Image(getClass().getResourceAsStream("/images/icono.png"));
        this.getIcons().add(imagen);

    }

    @Override
    public void actualizarLista(List<String> canciones) {
        listaCanciones.getItems().setAll(canciones);
    }

    @Override
    public String getDistancia() {
        ToggleButton dist = (ToggleButton) grupoDistancia.getSelectedToggle();
        if (dist != null) return dist.getText();
        return "";
    }

    @Override
    public String getAlgoritmo() {
        ToggleButton algorithm = (ToggleButton) grupoAlgoritmo.getSelectedToggle();
        if (algorithm.getId() == "algo1") return "Kmeans";
        return "knn";
    }

    @Override
    public Button getBtnSugerir() {
        initialize();
        return btnSugerir;
    }

    @Override
    public String getCancion() {
        return listaCanciones.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize() {
        BooleanBinding isDistanceSelected = grupoDistancia.selectedToggleProperty().isNotNull();
        BooleanBinding isRecommendSelected = grupoAlgoritmo.selectedToggleProperty().isNotNull();
        BooleanBinding isSongSelected = listaCanciones.getSelectionModel().selectedItemProperty().isNotNull();

        btnSugerir.disableProperty().bind(isDistanceSelected.not().or(isRecommendSelected.not()).or(isSongSelected.not()));
    }

}
