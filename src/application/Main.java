package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("paint.fxml"));
    Scene scene = new Scene(root);
    Image icon = new Image(getClass().getResourceAsStream("/images/painticon.png"));
    stage.setScene(scene);
    stage.setTitle("Paint");
    stage.show();
    stage.getIcons().add(icon);
  }
  
 
}
