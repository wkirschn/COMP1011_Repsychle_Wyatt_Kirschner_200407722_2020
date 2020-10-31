/*




    Resources:
        https://docs.oracle.com/javafx/2/charts/pie-chart.htm
        https://www.tutorialspoint.com/javafx/pie_chart.htm
 */

package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class viewChartController implements Initializable {

    @FXML
    private PieChart ecoScorePieChart;


    @FXML
    private Button addProductButton;

    @FXML
    private Button viewTableButton;

    @FXML
    private Button exitProgramButton;

    @FXML
    private TextArea pieGraphTextArea;

    @FXML
    private RadioButton ecoScoreRadioButton;

    @FXML
    private RadioButton resinIdRadioButton;

    @FXML
    private RadioButton disposalRadioButton;

    @FXML
    private Label chartLabel;


    @FXML
    void addProductButton(ActionEvent event) throws IOException {
        new addItemController().transitionScene(event, "viewChart.fxml", 800, 600, "RePsychle - View Chart");
    }

    @FXML
    void exitProgramButton(ActionEvent event) throws InterruptedException {

        Thread.sleep(500);
        Platform.exit();
    }

    @FXML
    void viewTableProduct(ActionEvent event) throws IOException {
       new addItemController().transitionScene(event, "databaseTable.fxml", 950, 400, "RePsychle - View Table");
    }



    @FXML
private void setEcoScorePieChart() {

        if(ecoScoreRadioButton.isSelected() == false) {
            ecoScoreRadioButton.setSelected(true);
        }
        if(resinIdRadioButton.isSelected() == true) {
            resinIdRadioButton.setSelected(false);
        }
        if(disposalRadioButton.isSelected() == true) {
            disposalRadioButton.setSelected(false);
        }

    try{
        ArrayList<RepsychleObjectContainer> objects = DBUtility.getAllProducts();

        // Not the best soultion, but the best to my knowledge unless I parsed a special SQL statement
        // If I did that, it would defeat the purpose of using getEcoScore etc if I tailored it, no?

        int veryLow = 0, low = 0, medium = 0, high = 0, veryHigh = 0;

        for(int n = 0; n < objects.size() ; n++) {
            if(objects.get(n).getEcoScore().contains("VERY LOW")){
                veryLow++;
                System.out.println(veryLow);
            }
            if(objects.get(n).getEcoScore().contains("LOW")){
                low++;
                System.out.println(low);
            }
            if(objects.get(n).getEcoScore().contains("MEDIUM")){
                medium++;
            }
            if(objects.get(n).getEcoScore().contains("HIGH")){
                high++;
            }
            if(objects.get(n).getEcoScore().contains("VERY HIGH")){
                veryHigh++;
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        if(veryLow > 0) {
            pieChartData.add(new PieChart.Data("VERY LOW", veryLow));
        }
        if(low > 0) {
            pieChartData.add(new PieChart.Data("LOW", low));
        }
        if(medium > 0) {
            pieChartData.add(new PieChart.Data("MEDIUM", medium));
        }
        if(high > 0) {
            pieChartData.add(new PieChart.Data("HIGH", high));
        }
        if(veryHigh > 0) {
            pieChartData.add(new PieChart.Data("VERY HIGH", veryHigh));
        }
        pieGraphTextArea.setText(new RepsychleObjectContainer().toEcoScorePie(veryLow, low, medium, high, veryHigh));
        //  Using a counter for how many matching instances, then I can insert into the pie chart....
        ecoScorePieChart.setTitle("EcoScore");
        ecoScorePieChart.setData(pieChartData);
        chartLabel.setText("Eco Score Displayed!");
    }
    catch (SQLException e) {
        e.printStackTrace();
    }



}

@FXML
private void setResinIdPieChart() {
    if(resinIdRadioButton.isSelected() == false) {
        resinIdRadioButton.setSelected(true);
    }
    if(disposalRadioButton.isSelected() == true) {
        disposalRadioButton.setSelected(false);
    }
    if(ecoScoreRadioButton.isSelected() == true) {
        ecoScoreRadioButton.setSelected(false);
    }

    try{
        ArrayList<RepsychleObjectContainer> objects = DBUtility.getAllProducts();

        // Not the best soultion, but the best to my knowledge unless I parsed a special SQL statement
        // If I did that, it would defeat the purpose of using getEcoScore etc if I tailored it, no?

        int resinOne = 0, resinTwo = 0, resinThree = 0, resinFour = 0, resinFive = 0, resinSix = 0, resinSeven = 0;

        for(int n = 0; n < objects.size() ; n++) {
            if(objects.get(n).getResinIdCode() == 1){
                resinOne++;
            }
            if(objects.get(n).getResinIdCode() == 2){
                resinTwo++;
            }
            if(objects.get(n).getResinIdCode() == 3){
                resinThree++;
            }
            if(objects.get(n).getResinIdCode() == 4){
                resinFour++;
            }
            if(objects.get(n).getResinIdCode() == 5){
                resinFive++;
            }
            if(objects.get(n).getResinIdCode() == 6){
                resinSix++;
            }
            if(objects.get(n).getResinIdCode() == 7){
                resinSeven++;
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        if(resinOne > 0) {
            pieChartData.add(new PieChart.Data("1", resinOne));
        }
        if(resinTwo > 0) {
            pieChartData.add(new PieChart.Data("2", resinTwo));
        }
        if(resinThree > 0) {
            pieChartData.add(new PieChart.Data("3", resinThree));
        }
        if(resinFour > 0) {
            pieChartData.add(new PieChart.Data("4", resinFour));
        }
        if(resinFive > 0) {
            pieChartData.add(new PieChart.Data("5", resinFive));
        }
        if(resinSix > 0) {
            pieChartData.add(new PieChart.Data("6", resinSix));
        }
        if(resinSeven > 0) {
            pieChartData.add(new PieChart.Data("7", resinSeven));
        }
        ecoScorePieChart.setTitle("Resin ID");
        pieGraphTextArea.setText(new RepsychleObjectContainer().toResinIdPie(resinOne, resinTwo, resinThree, resinFour, resinFive, resinSix, resinSeven));
        //  Using a counter for how many matching instances, then I can insert into the pie chart....
        ecoScorePieChart.setData(pieChartData);
        chartLabel.setText("Resin ID Displayed!");
    }
    catch (SQLException e) {
        e.printStackTrace();
    }



}

    @FXML
    private void setDisposalPieChart() {
        if(disposalRadioButton.isSelected() == false) {
            disposalRadioButton.setSelected(true);
        }

        if(ecoScoreRadioButton.isSelected() == true) {
            ecoScoreRadioButton.setSelected(false);
        }

        if(resinIdRadioButton.isSelected() == true) {
            resinIdRadioButton.setSelected(false);
        }

        try{
            ArrayList<RepsychleObjectContainer> objects = DBUtility.getAllProducts();
            int recycleCounter = 0, compostCounter = 0, garbageCounter = 0, sortingCounter = 0;

            for(int n = 0; n < objects.size() ; n++) {
                if(objects.get(n).getDisposal().contains("Recycle")){
                    recycleCounter++;
                }
                if(objects.get(n).getDisposal().contains("Compost")){
                    compostCounter++;
                }
                if(objects.get(n).getDisposal().contains("Garbage")){
                    garbageCounter++;
                }
                if(objects.get(n).getDisposal().contains("Sorting Facility")){
                    sortingCounter++;
                }

            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            if(recycleCounter > 0) {
                pieChartData.add(new PieChart.Data("1", recycleCounter));
            }
            if(compostCounter > 0) {
                pieChartData.add(new PieChart.Data("2", compostCounter));
            }
            if(garbageCounter > 0) {
                pieChartData.add(new PieChart.Data("3", garbageCounter));
            }
            if(sortingCounter > 0) {
                pieChartData.add(new PieChart.Data("4", sortingCounter));
            }

            ecoScorePieChart.setTitle("Disposal Methods");
            pieGraphTextArea.setText(new RepsychleObjectContainer().toDisposalPie(recycleCounter, compostCounter, garbageCounter, sortingCounter));
            //  Using a counter for how many matching instances, then I can insert into the pie chart....
            ecoScorePieChart.setData(pieChartData);
            chartLabel.setText("Disposal Method Displayed!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }



    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //String image = "@img/1.png";
        ecoScoreRadioButton.setSelected(true);
    setEcoScorePieChart();


}
}
/*
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
 */