package classifier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Web Classifier");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return Main.primaryStage;
    }

    public static void main(String[] args) throws Exception {
        launch(args);

        return;
    }
}
