package Login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ErrorCall  {
    public  Scene errorScene(String error) throws IOException{
        Scene root = FXMLLoader.load(getClass().getResource("Login/Error.fxml"));
        return root;
    }
public ErrorCall(){
        //some code will come here
}

}
