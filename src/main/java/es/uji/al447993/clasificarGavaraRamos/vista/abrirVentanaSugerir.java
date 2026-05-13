package es.uji.al447993.clasificarGavaraRamos.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        // --- CONTENEDOR PRINCIPAL ---
        VBox layoutPrincipal = new VBox(20);
        layoutPrincipal.setPadding(new Insets(20));
        layoutPrincipal.setStyle("-fx-background-color: #f4f4f4;");

        Label lblNum = new Label("Número de recomendaciones:");
        lblNum.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        this.selectorNumerico = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        selectorNumerico.setValueFactory(valueFactory);
        // Permite escribir manualmente
        selectorNumerico.setEditable(true);
        selectorNumerico.setPrefWidth(80);

        HBox filaNumerica = new HBox(15, lblNum, selectorNumerico);
        filaNumerica.setAlignment(Pos.CENTER_LEFT);

        // PANEL  ------------------------------------------------------------------------------------------------------------------------------------

        Label lblInfo = new Label("Información adicional:");
        lblInfo.setStyle("-fx-font-weight: bold;");

        ScrollPane scrollPane = new ScrollPane();
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        this.contenidoScroll = new VBox(10);
        contenidoScroll.setPadding(new Insets(10));
        scrollPane.setContent(contenidoScroll);
        scrollPane.setFitToWidth(true);

        contenidoScroll.getChildren().add(new Label("Detalle de recomendación #1"));

        //-----------------------------------------------------------------------------------------------------------------------------------------

        //BOTÓN CERRAR
        Button btnCerrar = new Button("Cerrar");


        btnCerrar.setStyle("-fx-background-radius: 20; -fx-padding: 8 25; -fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");

        btnCerrar.setOnAction(e -> this.close());

        HBox contenedorBoton = new HBox(btnCerrar);
        contenedorBoton.setAlignment(Pos.CENTER_RIGHT);


        layoutPrincipal.getChildren().addAll(filaNumerica, lblInfo, scrollPane, contenedorBoton);

        Scene scene = new Scene(layoutPrincipal, 700, 500);
        this.setScene(scene);
        this.setTitle("Programacion Avanzada");
    }

    public Spinner<Integer> getSelectorNumerico() {
        return selectorNumerico;
    }

    public void actualizarLista(List<String> canciones) {
        contenidoScroll.getChildren().clear();
        for(String cancion: canciones) {
            Label label = new Label(cancion);
            label.setStyle("-fx-font-size: 13px; -fx-padding: 5; -fx-border-color: #ddd; -fx-border-width: 0 0 1 0;");
            contenidoScroll.getChildren().add(label);
        }
    }
}

