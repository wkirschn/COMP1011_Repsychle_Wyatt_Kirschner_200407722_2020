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


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    @FXML
    private TextField brandNameTextField;

    @FXML
    private TextField productNameTextField;

    @FXML
    private Spinner<Integer> resinSpinner;

    @FXML
    private Label disposalLabel;

    @FXML
    private Label materialLabel;

    @FXML
    private Label commentLabel;

    @FXML
    private Label ecoScoreLabel;


    @FXML
    void submitButton(ActionEvent event) {
        System.out.println("Send!");
       // RepsychleObjectContainer fillOut = new RepsychleObjectContainer(001, resinSpinner.getValue(), brandNameTextField.getText(),
         //       productNameTextField.getText(), materialComboBox.getValue(), disposalComboBox.getValue(), "High", commentTextArea.getText());
        System.out.println(brandNameTextField.getText());
        System.out.println(productNameTextField.getText());
        System.out.println(resinSpinner.getValue());
        System.out.println(materialLabel.getText());
        //System.out.println(disposalComboBox.getValue());
        System.out.println(commentLabel.getText());
        System.out.println(ecoScoreLabel.getText());
       //RepsychleObjectContainer test = new RepsychleObjectContainer(001, resinSpinner.getValue(), brandNameTextField.getText(), productNameTextField.getText(), materialComboBox.getValue(), disposalComboBox.getValue(), 40.00, commentTextArea.getText());
    //int id, int resinIdCode, String brandName, String objectName, String material, String disposal, Double ecoScore, String ecoDoc
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
          SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,7);
        resinSpinner.setValueFactory(valueFactory);
        /*

        disposalComboBox.getItems().addAll("Recycle", "Garbage", "Local Disposal Facility");*/
    }
}
