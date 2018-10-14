package Views;

import Controllers.ContactsController;
import Controllers.ProfileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));



        FXMLLoader loader = new FXMLLoader(getClass().getResource("contacts.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Create new profile");
        ContactsController controller = (ContactsController)loader.getController();
        controller.setStage(primaryStage);


        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
