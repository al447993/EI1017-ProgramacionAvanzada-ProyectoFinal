package es.uji.al447993.clasificarGavaraRamos.vista;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        VBox layout = new VBox();
        Label label = new Label("Hola Mundo :D");
        Button button = new Button("Un Boton :D");
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(layout, 200, 100));
        primaryStage.setTitle("JavaFXApp");
        primaryStage.show();
    }

}
