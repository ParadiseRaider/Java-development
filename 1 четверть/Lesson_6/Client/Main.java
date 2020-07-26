package Lesson_6.Client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResourceAsStream("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 350, 375);
        primaryStage.setScene(scene);
        primaryStage.show();
        Controller c = fxmlLoader.getController();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                c.closing();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
