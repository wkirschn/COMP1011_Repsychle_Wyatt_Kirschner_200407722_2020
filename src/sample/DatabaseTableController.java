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
    private TableColumn<RepsychleObjectContainer, String> disposalTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, String> commentTable;

    @FXML
    private TableColumn<RepsychleObjectContainer, String> ecoScoreTable;

    @FXML
    private ImageView resinImage;

    @FXML
    private TextArea infoTextArea;

    @FXML
    private Button viewChartButton;

    @FXML
    void viewChartButton(ActionEvent event) throws IOException {
        new addItemController().transitionScene(event, "viewChart.fxml", 1000, 500, "RePsychle - View Chart");
    }

    @FXML
    void exitButton(ActionEvent event) throws InterruptedException {
        //  https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html;   https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html
        // This was used from these documents to create the exit needed


        Thread.sleep(500);
        Platform.exit();
    }

    @FXML
    void addProductButton(ActionEvent event) throws IOException, SQLException {
        new addItemController().transitionScene(event, "addItem.fxml", 900, 775, "RePsychle - Add Items");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // infoTextArea.setDisable(true);
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
        ///    public String toCommentString(String brandNameTable, String productNameTable, int resinIdTable, String materialTable, String disposalTable, String ecoCommentTable, String ecoScoreTable) {

        databaseTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            //System.out.printf("Old Resin ID: %s New Resin ID: %s%n", oldValue, newValue);
            try {
                System.out.println(newValue.getEcoScore());
                // I didn't want to create a new toString, so I've decided to just build a custom toCommentString to have better organization
                infoTextArea.setText(newValue.toCommentString(newValue.getBrandName(), newValue.getObjectName(), newValue.getResinIdCode(), newValue.getMaterial(), newValue.getDisposal(), newValue.getEcoDoc(), newValue.getEcoScore()));






            } catch (NumberFormatException e) {
                //  resinEditor.setText(oldValue);
                //objectLabel.setTextFill(RED);
                //objectLabel.setText("Only whole numbers are allowed!");
            }

            //resinSpinner.setEditable(true);
        });


        System.out.println();



    }


}


