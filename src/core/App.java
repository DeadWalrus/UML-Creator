package core;

import utils.DirectorySurfer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class App extends Application{
    private static DirectorySurfer surfer;
    public static void main(String[] args) throws Exception {
        surfer = new DirectorySurfer("/home/johnny/Documents");
        System.out.println("Starting surf");
        surfer.surf();
        surfer = new DirectorySurfer("/home/johnny/Documents/misc");
        System.out.println("Starting another surf");
        surfer.surf();
        launch(args);
    }
    public void start(Stage primaryStage){
        Scene s = new Scene(new BorderPane());
        primaryStage.setScene(s);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            surfer.shutdownExecutors();
        });
    }
}
