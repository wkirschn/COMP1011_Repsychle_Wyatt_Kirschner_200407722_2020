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
import javafx.scene.paint.Color;

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
    private ComboBox<String> disposalComboBox;

    @FXML
    private Label materialLabel;

    @FXML
    private Label commentLabel;

    @FXML
    private Label ecoScoreLabel;

    @FXML
    private Label infoLabel;

    @FXML
    private Label objectLabel;



    @FXML
    void submitButton(ActionEvent event) {
        System.out.println("Send!");




    }

    public boolean inputValidation()
    {
        infoLabel.setTextFill(Color.RED);
        if(brandNameTextField.getText().trim().isEmpty()) {
        objectLabel.setText("Please enter a brand name!");
        return false;
        }
        if(productNameTextField.getText().trim().isEmpty()) {
            objectLabel.setText("Please enter a product name!");
            return false;
        }

        if(materialLabel.getText().trim().isEmpty()){
            objectLabel.setText("Please select a Resin ID!");
            return false;
        }
        if(disposalComboBox.getValue().isEmpty()) {
            objectLabel.setText("Please select a disposal method!");
            return false;
        }
        if(commentLabel.getText().isEmpty()) {
            objectLabel.setText("Please ensure a comment is passed!");
            return false;
        }
        if(ecoScoreLabel.getText().isEmpty()) {
            objectLabel.setText("Please ensure an EcoScore is Generated!");
            return false;
        }

        else {
            return true;
        }
    }

    public void materialLoader(int resinID) {   // When it comes to creating an object, then I will collect the information to parse into the database
        if (resinID >= 1 || resinID <= 7) {
            if (resinID == 1) {
                materialLabel.setText("PETE: Polyethylene Terephthalate");
            }
            if (resinID == 2) {
                materialLabel.setText("HDPE: High-density polyethylene");
            }
            if (resinID == 3) {
                materialLabel.setText("PVC: Polyvinyl Chloride");
            }
            if (resinID == 4) {
                materialLabel.setText("LDPE: Low-Density Polyethylene");
            }
            if (resinID == 5) {
                materialLabel.setText("PP: Polypropylene");
            }
            if (resinID == 6) {
                materialLabel.setText("PS: Polystyrene");
            }
            if (resinID == 7) {
                materialLabel.setText("Other: Other");
            }
        }
        else {
            throw new IllegalArgumentException("Resin ID is not within range!");
        }
    }

    public void ecoScoreGeneration(int resinID, String disposalInput){

        {
            if (resinID == 1) {
                outputEcoScore(resinID, disposalInput);
            }
            if (resinID == 2) {
                outputEcoScore(resinID, disposalInput);
            }
            if (resinID == 3) {
                outputEcoScore(resinID, disposalInput);
            }
            if (resinID == 4) {
                outputEcoScore(resinID, disposalInput);
            }
            if (resinID == 5) {
                outputEcoScore(resinID, disposalInput);
            }
            if (resinID == 6) {
                outputEcoScore(resinID, disposalInput);
            }
            if (resinID == 7) {
                outputEcoScore(resinID, disposalInput);
            }
        }




    }

    private void outputEcoScore(int resinID, String disposalInput) {
        if(disposalInput.contains("Recycle")) {
        System.out.printf("Resin ID: %s, Disposal Method: %s", resinID, disposalInput);
            commentLabel.setText(resinID + " " + disposalInput);
        }
        if(disposalInput.contains("Compost")) {
            System.out.printf("Resin ID: %s, Disposal Method: %s", resinID, disposalInput);
            commentLabel.setText(resinID + " " + disposalInput);
        }
        if(disposalInput.contains("Garbage")) {
            System.out.printf("Resin ID: %s, Disposal Method: %s", resinID, disposalInput);
            commentLabel.setText(resinID + " " + disposalInput);
        }
        if(disposalInput.contains("Sorting Facility")) {
            System.out.printf("Resin ID: %s, Disposal Method: %s", resinID, disposalInput);
            commentLabel.setText(resinID + " " + disposalInput);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
          SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,7);
        resinSpinner.setValueFactory(valueFactory);
        /*

        disposalComboBox.getItems().addAll("Recycle", "Garbage", "Local Disposal Facility");*/
        // Validation for Resin Spinner

       TextField resinEditor = resinSpinner.getEditor();

       resinEditor.textProperty().addListener((observableValue, oldValue, newValue) -> {
           System.out.printf("Old Resin ID: %s New Resin ID: %s%n", oldValue, newValue);
           try {
               Integer.parseInt(newValue); //Checks to see if it's an int
               materialLoader(Integer.parseInt(newValue));  // If so, then I can pass this new Value into a function that will change the material
           } catch (NumberFormatException e) {
               resinEditor.setText(oldValue);
               objectLabel.setTextFill(Color.RED);
               objectLabel.setText("Only whole numbers are allowed!");

           }

           resinSpinner.setEditable(true);
       });
        disposalComboBox.getItems().addAll("Recycle", "Compost", "Garbage", "Sorting Facility");
        disposalComboBox.setEditable(true);
        TextField disposalEditor = disposalComboBox.getEditor();

       disposalEditor.textProperty().addListener((observableValue, oldValue, newValue) -> {
          System.out.printf("Old Disposal Method: %s, New Disposal Method: %s%n", oldValue, newValue);
           try {
               System.out.println(resinSpinner.getValue() + newValue);      // Need to check to see when Resin and Disposal are Blank
               ecoScoreGeneration(resinSpinner.getValue(), newValue);

           } catch(IllegalArgumentException e) {
               disposalComboBox.setValue(oldValue);
               objectLabel.setTextFill(Color.RED);
               objectLabel.setText("Only proper disposal methods allowed!");

           }
           disposalComboBox.setEditable(true);


       });


    }
}
