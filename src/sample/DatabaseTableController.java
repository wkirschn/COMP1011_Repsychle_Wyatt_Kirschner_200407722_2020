/**
 *    Name:       Wyatt Kirschner
 *    Student ID: 200407722
 *    Date:       11/01/20
 *    Notes:    All notes will be placed in a README.md
 */
package sample;

import com.mysql.cj.xdevapi.Table;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.RED;

/**
 * This is responsible for allowing the Database Table to function
 */

public class DatabaseTableController implements Initializable {

    /**
     * This is for the entire TableView so I can insert the proper columns into the Table
     */

    @FXML
    private TableView < RepsychleObjectContainer > databaseTable;

    /**
     * This TableColumn is for the Primary Key
     */

    @FXML
    private TableColumn < RepsychleObjectContainer, Integer > idTable;

    /**
     * This TableColumn is for the Brand Name
     */

    @FXML
    private TableColumn < RepsychleObjectContainer, String > brandNameTable;

    /**
     * This TableColumn is for the Product Name
     */

    @FXML
    private TableColumn < RepsychleObjectContainer, String > productNameTable;

    /**
     * This TableColumn is for the Resin ID
     */

    @FXML
    private TableColumn < RepsychleObjectContainer, Integer > resinIdTable;

    /**
     * This TableColumn is for the Material
     */

    @FXML
    private TableColumn < RepsychleObjectContainer, String > materialTable;

    /**
     * This TableColumn is for the Disposal Method
     */

    @FXML
    private TableColumn < RepsychleObjectContainer, String > disposalTable;

    /**
     * This TableColumn is for the Comment for the product
     */

    @FXML
    private TableColumn < RepsychleObjectContainer, String > commentTable;

    /**
     * This TableColumn is for the ecoScore
     */

    @FXML
    private TableColumn < RepsychleObjectContainer, String > ecoScoreTable;

    /**
     * This ImageView is for the Resin Image (Future)
     */

    @FXML
    private ImageView resinImage;

    /**
     * This TextArea is for info for the product that is selected in the table
     */

    @FXML
    private TextArea infoTextArea;

    /**
     * This Button will be for changing the scene to the Pie Charts
     */


    @FXML
    void viewChartButton(ActionEvent event) throws IOException {
        new addItemController().transitionScene(event, "viewChart.fxml", 925, 600, "RePsychle - View Chart");
    }

    /**
     * This Button will be for exiting the program
     * @param event
     * @throws InterruptedException
     */

    @FXML
    void exitButton(ActionEvent event) throws InterruptedException {
        //  https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html;   https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html
        // This was used from these documents to create the exit needed


        Thread.sleep(500);
        Platform.exit();
    }

    /**
     * This Button will be for changing the scene to the Add Item scene
     * @param event
     * @throws IOException
     * @throws SQLException
     */


    @FXML
    void addProductButton(ActionEvent event) throws IOException, SQLException {
        new addItemController().transitionScene(event, "addItem.fxml", 800, 600, "RePsychle - Add Items");

    }

    /**
     * This will attempt to pull the information from the database and place it into the table
     * There is also a changeListener so when the row is selected, information about it will be displayed to the left in the textArea field
     * If not, then a SQLException or a NumberFormatException is printed
     * Based on
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ArrayList < RepsychleObjectContainer > objects = DBUtility.getAllProducts();
            ObservableList < RepsychleObjectContainer > productList = FXCollections.observableArrayList(objects);

            idTable.setCellValueFactory(new PropertyValueFactory < > ("id"));
            brandNameTable.setCellValueFactory(new PropertyValueFactory < > ("brandName"));
            productNameTable.setCellValueFactory(new PropertyValueFactory < > ("objectName"));
            resinIdTable.setCellValueFactory(new PropertyValueFactory < > ("resinIdCode"));
            materialTable.setCellValueFactory(new PropertyValueFactory < > ("material"));
            disposalTable.setCellValueFactory(new PropertyValueFactory < > ("disposal"));
            commentTable.setCellValueFactory(new PropertyValueFactory < > ("ecoDoc"));
            ecoScoreTable.setCellValueFactory(new PropertyValueFactory < > ("ecoScore")); // Would have been nice to find a way to custom sort
            databaseTable.setItems(productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
        try {
            System.out.println(newValue.getEcoScore());
            // I didn't want to create a new toString, so I've decided to just build a custom toCommentString to have better organization
            infoTextArea.setText(newValue.toCommentString(newValue.getBrandName(), newValue.getObjectName(), newValue.getResinIdCode(), newValue.getMaterial(), newValue.getDisposal(), newValue.getEcoDoc(), newValue.getEcoScore()));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        });


    }


}