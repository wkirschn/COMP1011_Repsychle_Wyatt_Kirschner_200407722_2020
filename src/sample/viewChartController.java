/**
 *    Name:       Wyatt Kirschner
 *    Student ID: 200407722
 *    Date:       11/01/20
 *    Notes:
 *        All notes will be placed in a README.md
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

/**
 * This is for creating the functionality of having the Pie Chart display the values of the ecoScore, resinID, and the disposal methods
 */

public class viewChartController implements Initializable {

    /**
     * This PieChart is for ecoScorePieChart that will be used to insert the values to display the information
     */
    @FXML
    private PieChart ecoScorePieChart;

    /**
     * This TextArea is for the information for the Pie Graph on the right, shows the count of each value
     */

    @FXML
    private TextArea pieGraphTextArea;

    /**
     * The RadioButton for the ecoScore
     */

    @FXML
    private RadioButton ecoScoreRadioButton;

    /**
     * The RadioButton for the resinID
     */

    @FXML
    private RadioButton resinIdRadioButton;

    /**
     *  This RadioButton is for the Disposal Method
     */

    @FXML
    private RadioButton disposalRadioButton;

    /**
     *  This the Label for the Title
     */

    @FXML
    private Label chartLabel;

    /**
     * This is to transition to the Add Product Button
     * @param event
     * @throws IOException
     */

    @FXML
    void addProductButton(ActionEvent event) throws IOException {
        new addItemController().transitionScene(event, "addItem.fxml", 800, 600, "RePsychle - View Chart");
    }

    /**
     * This is to exit the program
     * @param event
     * @throws InterruptedException
     */

    @FXML
    void exitProgramButton(ActionEvent event) throws InterruptedException {

        Thread.sleep(500);
        Platform.exit();
    }

    /**
     * This is to transition to the Database Table
     * @param event
     * @throws IOException
     */

    @FXML
    void viewTableProduct(ActionEvent event) throws IOException {
        new addItemController().transitionScene(event, "databaseTable.fxml", 1100, 450, "RePsychle - View Table");
    }

    /**
     * If the ecoScore RadioButton is selected, then a ecoScore Pie Chart is generated
     * Validation to make sure that other radio buttons are not selected
     * This method is creating a count by looping through all the objects in the ArrayList
     * The counter will increase when required, and then passed using the specific method to create an output for the textArea
     * The result is also displayed and a message saying so
     * If this fails, an SQL Exception is thrown
     */

    @FXML

    private void setEcoScorePieChart() {

        if (ecoScoreRadioButton.isSelected() == false) {
            ecoScoreRadioButton.setSelected(true);
        }
        if (resinIdRadioButton.isSelected() == true) {
            resinIdRadioButton.setSelected(false);
        }
        if (disposalRadioButton.isSelected() == true) {
            disposalRadioButton.setSelected(false);
        }

        try {
            ArrayList < RepsychleObjectContainer > objects = DBUtility.getAllProducts();
            int veryLow = 0, low = 0, medium = 0, high = 0, veryHigh = 0;

            for (int n = 0; n < objects.size(); n++) {

                if (objects.get(n).getEcoScore().matches("VERY LOW")) {
                    veryLow++;

                }
                if (objects.get(n).getEcoScore().matches("LOW")) {
                    low++;

                }
               if (objects.get(n).getEcoScore().matches("MEDIUM")) {
                    medium++;
                }

               if (objects.get(n).getEcoScore().matches("HIGH")) {
                    high++;
                }

               if (objects.get(n).getEcoScore().matches("VERY HIGH")) {
                    veryHigh++;
                }
            }

            ObservableList < PieChart.Data > pieChartData = FXCollections.observableArrayList();

            if (veryLow > 0) {
                pieChartData.add(new PieChart.Data("VERY LOW", veryLow));
            }
            if (low > 0) {
                pieChartData.add(new PieChart.Data("LOW", low));
            }
            if (medium > 0) {
                pieChartData.add(new PieChart.Data("MEDIUM", medium));
            }
            if (high > 0) {
                pieChartData.add(new PieChart.Data("HIGH", high));
            }
            if (veryHigh > 0) {
                pieChartData.add(new PieChart.Data("VERY HIGH", veryHigh));
            }
            pieGraphTextArea.setText(new RepsychleObjectContainer().toEcoScorePie(veryLow, low, medium, high, veryHigh));
            //  Using a counter for how many matching instances, then I can insert into the pie chart....
            ecoScorePieChart.setTitle("EcoScore");
            ecoScorePieChart.setData(pieChartData);
            chartLabel.setText("Eco Score Displayed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * If the resinID RadioButton is selected, then a ecoScore Pie Chart is generated
         * Validation to make sure that other radio buttons are not selected
         * This method is creating a count by looping through all the objects in the ArrayList
         * The counter will increase when required, and then passed using the specific method to create an output for the textArea
         * The result is also displayed and a message saying so
         * If this fails, an SQL Exception is thrown
         */

    }

    @FXML
    private void setResinIdPieChart() {
        if (resinIdRadioButton.isSelected() == false) {
            resinIdRadioButton.setSelected(true);
        }
        if (disposalRadioButton.isSelected() == true) {
            disposalRadioButton.setSelected(false);
        }
        if (ecoScoreRadioButton.isSelected() == true) {
            ecoScoreRadioButton.setSelected(false);
        }

        try {
            ArrayList < RepsychleObjectContainer > objects = DBUtility.getAllProducts();

            // Not the best soultion, but the best to my knowledge unless I parsed a special SQL statement
            // If I did that, it would defeat the purpose of using getEcoScore etc if I tailored it, no?

            int resinOne = 0, resinTwo = 0, resinThree = 0, resinFour = 0, resinFive = 0, resinSix = 0, resinSeven = 0;

            for (int n = 0; n < objects.size(); n++) {
                if (objects.get(n).getResinIdCode() == 1) {
                    resinOne++;
                }
                if (objects.get(n).getResinIdCode() == 2) {
                    resinTwo++;
                }
                if (objects.get(n).getResinIdCode() == 3) {
                    resinThree++;
                }
                if (objects.get(n).getResinIdCode() == 4) {
                    resinFour++;
                }
                if (objects.get(n).getResinIdCode() == 5) {
                    resinFive++;
                }
                if (objects.get(n).getResinIdCode() == 6) {
                    resinSix++;
                }
                if (objects.get(n).getResinIdCode() == 7) {
                    resinSeven++;
                }
            }

            ObservableList < PieChart.Data > pieChartData = FXCollections.observableArrayList();

            if (resinOne > 0) {
                pieChartData.add(new PieChart.Data("1", resinOne));
            }
            if (resinTwo > 0) {
                pieChartData.add(new PieChart.Data("2", resinTwo));
            }
            if (resinThree > 0) {
                pieChartData.add(new PieChart.Data("3", resinThree));
            }
            if (resinFour > 0) {
                pieChartData.add(new PieChart.Data("4", resinFour));
            }
            if (resinFive > 0) {
                pieChartData.add(new PieChart.Data("5", resinFive));
            }
            if (resinSix > 0) {
                pieChartData.add(new PieChart.Data("6", resinSix));
            }
            if (resinSeven > 0) {
                pieChartData.add(new PieChart.Data("7", resinSeven));
            }
            ecoScorePieChart.setTitle("Resin ID");
            pieGraphTextArea.setText(new RepsychleObjectContainer().toResinIdPie(resinOne, resinTwo, resinThree, resinFour, resinFive, resinSix, resinSeven));
            //  Using a counter for how many matching instances, then I can insert into the pie chart....
            ecoScorePieChart.setData(pieChartData);
            chartLabel.setText("Resin ID Displayed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    /**
     * If the Disposal RadioButton is selected, then a ecoScore Pie Chart is generated
     * Validation to make sure that other radio buttons are not selected
     * This method is creating a count by looping through all the objects in the ArrayList
     * The counter will increase when required, and then passed using the specific method to create an output for the textArea
     * The result is also displayed and a message saying so
     * If this fails, an SQL Exception is thrown
     */


    @FXML
    private void setDisposalPieChart() {
        if (disposalRadioButton.isSelected() == false) {
            disposalRadioButton.setSelected(true);
        }

        if (ecoScoreRadioButton.isSelected() == true) {
            ecoScoreRadioButton.setSelected(false);
        }

        if (resinIdRadioButton.isSelected() == true) {
            resinIdRadioButton.setSelected(false);
        }

        try {
            ArrayList < RepsychleObjectContainer > objects = DBUtility.getAllProducts();
            int recycleCounter = 0, compostCounter = 0, garbageCounter = 0, sortingCounter = 0;

            for (int n = 0; n < objects.size(); n++) {
                if (objects.get(n).getDisposal().matches("Recycle")) {
                    recycleCounter++;
                }
                if (objects.get(n).getDisposal().matches("Compost")) {
                    compostCounter++;
                }
                if (objects.get(n).getDisposal().matches("Garbage")) {
                    garbageCounter++;
                }
                if (objects.get(n).getDisposal().matches("Sorting Facility")) {
                    sortingCounter++;
                }

            }

            ObservableList < PieChart.Data > pieChartData = FXCollections.observableArrayList();

            if (recycleCounter > 0) {
                pieChartData.add(new PieChart.Data("Recycle", recycleCounter));
            }
            if (compostCounter > 0) {
                pieChartData.add(new PieChart.Data("Compost", compostCounter));
            }
            if (garbageCounter > 0) {
                pieChartData.add(new PieChart.Data("Garbage", garbageCounter));
            }
            if (sortingCounter > 0) {
                pieChartData.add(new PieChart.Data("Sorting Facility", sortingCounter));
            }

            ecoScorePieChart.setTitle("Disposal Methods");
            pieGraphTextArea.setText(new RepsychleObjectContainer().toDisposalPie(recycleCounter, compostCounter, garbageCounter, sortingCounter));
            //  Using a counter for how many matching instances, then I can insert into the pie chart....
            ecoScorePieChart.setData(pieChartData);
            chartLabel.setText("Disposal Method Displayed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    /**
     * Set's the ecoScore Pie Chart by defualt
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //String image = "@img/1.png";

        ecoScoreRadioButton.setSelected(true);
        setEcoScorePieChart();


    }
}