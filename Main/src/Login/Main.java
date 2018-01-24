package Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginF.fxml"));
//        loader.setController( new LoginMain());
        Parent root = FXMLLoader.load(getClass().getResource("LoginF.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene( new Scene(root,500,300));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
