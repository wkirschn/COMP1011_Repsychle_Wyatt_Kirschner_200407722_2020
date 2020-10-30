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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class DatabaseTableController implements Initializable {

    @FXML
    private TableView<RepsychleObjectContainer> databaseTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, Integer> idTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, String> brandNameTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, String> productNameTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, Integer> resinIdTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, String> materialTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, String>disposalTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, String> commentTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, String> ecoScoreTable;

    @FXML
    private ImageView resinImage;

    @FXML
    void exitButton(ActionEvent event) throws InterruptedException {
        //  https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html;   https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html
        // This was used from these documents to create the exit needed


        Thread.sleep(500);
        Platform.exit();
    }

    @FXML
    void addProductButton(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addItem.fxml"));
        Stage primaryStage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(root, 900, 775));
        primaryStage.setTitle("RePsychle - Add Items");
        primaryStage.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ArrayList<RepsychleObjectContainer> objects = DBUtility.getAllProducts();
            ObservableList<RepsychleObjectContainer> productList = FXCollections.observableArrayList(objects);
            //idTable.setCellFactory(new Callback<TableColumn.CellDataFeatures>);

            idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
            brandNameTable.setCellValueFactory(new PropertyValueFactory<>("brandName"));
            productNameTable.setCellValueFactory(new PropertyValueFactory<>("objectName"));
            resinIdTable.setCellValueFactory(new PropertyValueFactory<>("resinIdCode"));
            materialTable.setCellValueFactory(new PropertyValueFactory<>("material"));
            disposalTable.setCellValueFactory(new PropertyValueFactory<>("disposal"));
            commentTable.setCellValueFactory(new PropertyValueFactory<>("ecoDoc"));
            ecoScoreTable.setCellValueFactory(new PropertyValueFactory<>("ecoScore"));  // Would have been nice to find a way to custom sort
            databaseTable.setItems(productList);

        } catch (SQLException e) {
            e.printStackTrace();
        }



        System.out.println();


    }
}
