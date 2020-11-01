/**
 *    Name:       Wyatt Kirschner
 *    Student ID: 200407722
 *    Date:       11/01/20
 *    Notes:
 *        All notes will be placed in a README.md
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *  Main extends the Application so we can Override the original main method and to set the stage
 */


public class Main extends Application {

    /**
     * This is to set the stage by introducing the .fxml, the window dimensions, application icon, stylesheet, title, and showing the stage
     * @param primaryStage
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("addItem.fxml"));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.getIcons().add(new Image("sample/img/RePsychle Favicon Icon.png"));
        root.getStylesheets().add("stylesheet.css");
        primaryStage.setTitle("RePsychle - Add Items");
        primaryStage.show();
    }

    /**
     * Main launches the application
     * @param args
     */


    public static void main(String[] args) {
        launch(args);
    }
}