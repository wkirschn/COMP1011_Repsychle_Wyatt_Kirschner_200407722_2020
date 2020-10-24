/*
    Name:       Wyatt Kirschner
    Student ID: 200407722
    Date:       10/24/20
    Notes:
        I have made some modifications so far. Changed how the Regular Expressions work with the various reasons of
        data input. I have also started to incorporate how an Add Product scene will limit the scope of entry on
        certain items. The EcoScore will be generated from the Resin ID, since this rating does state how easy it is
        to recycle or how harmful the product is!

        I'm planning on having the input of how the item being disposed of generating a comment based on the user's actions.

        The comments section will also be auto generated based the Resin ID selected and the dispsosal method

        I would need to next have functions that will set the label values as required.

        The purpose of this is to generate the hardcode needed to have these objects being created with success

        I will then need to place this into the database and then the charts / table


 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 550));
        primaryStage.setTitle("RePsychle - Wyatt Kirschner - 200407722");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
