package es.uji.al447993.clasificarGavaraRamos.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class abrirVentanaSugerir extends Stage {

    private Spinner<Integer> selectorNumerico;
    private VBox contenidoScroll;

    public abrirVentanaSugerir() {

        // Bloquea la ventana principal
        this.initModality(Modality.APPLICATION_MODAL);


        VBox layoutPrincipal = new VBox(20);
        layoutPrincipal.setPadding(new Insets(20));

        layoutPrincipal.setStyle("""
                -fx-background-color: linear-gradient(to bottom right, #1e1e2f, #2d2d44);
                """);

        Label titulo = new Label("🎵 Recomendaciones");
        titulo.setStyle("""
                -fx-font-size: 24px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
                """);

        VBox panelSuperior = new VBox(15);
        panelSuperior.setPadding(new Insets(20));

        panelSuperior.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 20;
                -fx-border-radius: 20;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0, 0, 4);
                """);

        Label lblNum = new Label("Número de recomendaciones:");
        lblNum.setStyle("""
                -fx-font-weight: bold;
                -fx-font-size: 14px;
                -fx-text-fill: #333;
                """);

        // =============================================================================================================
        // Spinner

        this.selectorNumerico = new Spinner<>();

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);

        selectorNumerico.setValueFactory(valueFactory);

        selectorNumerico.setEditable(true);
        selectorNumerico.setPrefWidth(100);

        selectorNumerico.setStyle("""
                -fx-background-radius: 12;
                -fx-font-size: 14px;
                """);

        HBox filaNumerica = new HBox(15, lblNum, selectorNumerico);
        filaNumerica.setAlignment(Pos.CENTER_LEFT);

        panelSuperior.getChildren().add(filaNumerica);

        // =========================================================
        // Información dentro del panel y diseño
        VBox panelInfo = new VBox(15);
        VBox.setVgrow(panelInfo, Priority.ALWAYS);
        panelInfo.setPadding(new Insets(20));

        panelInfo.setStyle("""
                -fx-background-color: white;
                -fx-background-radius: 20;
                -fx-border-radius: 20;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0, 0, 4);
                """);

        Label lblInfo = new Label("Información adicional");
        lblInfo.setStyle("""
                -fx-font-weight: bold;
                -fx-font-size: 16px;
                -fx-text-fill: #333;
                """);

        ScrollPane scrollPane = new ScrollPane();
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("""
                -fx-background: transparent;
                -fx-background-color: transparent;
                """);

        this.contenidoScroll = new VBox(10);
        contenidoScroll.setPadding(new Insets(10));

        contenidoScroll.setStyle("""
                -fx-text-fill: black;
                -fx-background-color: #f8f9fa;
                -fx-background-radius: 15;
                """);

        scrollPane.setContent(contenidoScroll);
        panelInfo.getChildren().addAll(lblInfo, scrollPane);

        // =============================================================================================================
        // Boton
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setStyle("""
                -fx-background-color: #e74c3c;
                -fx-text-fill: white;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-background-radius: 30;
                -fx-padding: 12 25;
                -fx-cursor: hand;
                """);

        btnCerrar.setOnAction(e -> this.close());

        HBox contenedorBoton = new HBox(btnCerrar);
        contenedorBoton.setAlignment(Pos.CENTER_RIGHT);

        // =============================================================================================================
        // Agregar a la ventana principal
        layoutPrincipal.getChildren().addAll(titulo, panelSuperior, panelInfo, contenedorBoton);

        Scene scene = new Scene(layoutPrincipal, 700, 500);

        this.setScene(scene);
        this.setTitle("Programación Avanzada");
        Image imagen = new Image(getClass().getResourceAsStream("/images/icono.png"));
        this.getIcons().add(imagen);
    }

    public Spinner<Integer> getSelectorNumerico() {
        return selectorNumerico;
    }

    public void actualizarLista(List<String> canciones) {
        contenidoScroll.getChildren().clear();
        for (String cancion : canciones) {
            Label label = new Label(cancion);
            label.setStyle("" + "-fx-font-size: 13px;" + " -fx-padding: 5;" + " -fx-border-color: #ddd;" + " -fx-border-width: 0 0 1 0;" + " -fx-text-fill: black;");
            contenidoScroll.getChildren().add(label);
        }
    }
}

