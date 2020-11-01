/**
 *    Name:       Wyatt Kirschner
 *    Student ID: 200407722
 *    Date:       11/01/20
 *    Notes:
 *        All notes will be placed in a README.md
 */
package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
import static javafx.scene.paint.Color.RED;

/**
 * This is the addItemController, this is used to validate and provide the GUI for adding items to the
 */

public class addItemController implements Initializable {

    /**
     * This ImageView is for the RePsychle Logo
     */

    @FXML
    private ImageView logoImageView;

    /**
     * This TextField is for the Brand Name with Regular Expression Validation
     */

    @FXML
    private TextField brandNameTextField;

    /**
     * This TextField is for the Product Name with Regular Expression Validation
     */

    @FXML
    private TextField productNameTextField;

    /**
     * This Spinner is for the resin ID by having it set between 1 (including) and 7 (including
     */

    @FXML
    private Spinner < Integer > resinSpinner;

    /**
     * This ComboBox is for the Disposal Method, by having the following Strings included:
     * Select (Default), Recycle, Compost, Garbage, Sorting Facility
     */


    @FXML
    private ComboBox < String > disposalComboBox;

    /**
     * This Label is for setting the material, based on the Resin ID
     */

    @FXML
    private Label materialLabel;

    /**
     * This TextArea will be used to insert the generated ecoComment into the commentLabel. This is generated on the Resin ID and Material.
     */

    @FXML
    private TextArea commentLabel;

    /**
     * This Label will set the ecoScore, based on the Resin ID and the Material.
     */

    @FXML
    private Label ecoScoreLabel;

    /**
     * This label is used to output any communication that the application needs to do to inform the user of what is occuring.
     */

    @FXML
    private Label infoLabel;

    /**
     * This is for the resinImage that will load when the resinID is selected
     */

    @FXML
    private ImageView resinImage;





    /**
     * This is for putting the application to sleep for 500 miliseconds and then exiting
     */

    @FXML
    void exitButton(ActionEvent event) throws InterruptedException {
        //  https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html;   https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html
        // This was used from these documents to create the exit needed


        Thread.sleep(500);
        Platform.exit();

    }

    /**
     * This is used when checking if we can switch to the Table, if passes, the transitionScene method is called
     */

    @FXML
    public void parseConnection(ActionEvent event, String newView) throws SQLException, IOException {
        infoLabel.setText("");
        if (DBUtility.getAllProducts().isEmpty()) {
            infoLabel.setText("Please add an entry before viewing the statistics!");

        } else {
            infoLabel.setText("Switching to Statistics...");
            transitionScene(event, "databaseTable.fxml", 1100, 450, "RePsychle - View Table");

        }
    }

    /**
     * This is used when I want to pass in the required arguments to transition to a new scene, similar to Main
     * @param event
     * @param newView
     * @param width
     * @param height
     * @param setTitleName
     * @throws IOException
     */

    @FXML
    public void transitionScene(ActionEvent event, String newView, int width, int height, String setTitleName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(newView));
        Stage primaryStage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        root.getStylesheets().add("stylesheet.css");
        primaryStage.getIcons().add(new Image("sample/img/RePsychle Favicon Icon.png"));
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setTitle(setTitleName);
        primaryStage.show();
    }

    /**
     * This calls the parseConnection when the Statistics Button is called to check the database table
     * @param event
     * @throws SQLException
     * @throws IOException
     */


    @FXML
    void statisticsButton(ActionEvent event) throws SQLException, IOException {
        // There will be a check where it will determine if there is anything in the database table. If not, it will be asked to add an item first to the database
        parseConnection(event, "databaseTable.fxml");
    }

    /**
     * This method is for attempting to add this information to the database
     * There is also a check to validate if all the fields are filled before execution using inputValidation
     * If there is an error in parsing this information, then an Illegal Argument Exception is thrown
     * @param event
     */

    @FXML
    void submitButton(ActionEvent event) {
        if (inputValidation()) {
            try {
                RepsychleObjectContainer newObject = new RepsychleObjectContainer(
                        brandNameTextField.getText(),
                        productNameTextField.getText(),
                        resinSpinner.getValue(),
                        materialLabel.getText(),
                        disposalComboBox.getValue(),
                        commentLabel.getText(),
                        ecoScoreLabel.getText()
                );
                clearInputField();
                transitionScene(event, "databaseTable.fxml", 1100, 450, "RePsychle - View Table");




            } catch (IllegalArgumentException | IOException e) {
                e.printStackTrace();
                infoLabel.setText(e.getMessage());
            }




        } else {
            throw new IllegalArgumentException("Make sure that the fields are properly filled out!");
        }




    }

    /**
     * This resets the fields to the proper values I want displayed when a product is added to the database
     */

    public void clearInputField() {
        brandNameTextField.setText("");
        productNameTextField.setText("");
        resinSpinner.getValueFactory().setValue(1);
        materialLabel.setText("Based on Resin ID");
        disposalComboBox.setValue("Select");
        commentLabel.setText("");
        ecoScoreLabel.setText("Very Low / Low / Medium / High / Very High");

    }

    /**
     * This is the input validation that I am checking for... If false, then the method will be false and the product won't
     * be added to the database!
     * @return
     */


    public boolean inputValidation() {
        infoLabel.setTextFill(RED);
        if (brandNameTextField.getText().trim().isEmpty()) {
            infoLabel.setText("Please enter a brand name!");
            return false;
        }
        if (productNameTextField.getText().trim().isEmpty()) {
            infoLabel.setText("Please enter a product name!");
            return false;
        }

        if (materialLabel.getText().trim().isEmpty()) {
            infoLabel.setText("Please select a Resin ID!");
            return false;
        }
        if (disposalComboBox.getValue().isEmpty()) {
            infoLabel.setText("Please select a disposal method!");
            return false;
        }
        if (commentLabel.getText().isEmpty()) {
            infoLabel.setText("Please ensure a comment is passed!");
            return false;
        }
        if (ecoScoreLabel.getText().isEmpty()) {
            infoLabel.setText("Please ensure an EcoScore is Generated!");
            return false;
        } else {
            return true;
        }
    }

    /* public void setImage(int resinID) throws FileNotFoundException {      //Figure out how to set the image?
         switch(resinID){
             case 1:
                 FileInputStream inputStream = new FileInputStream("C:\\Users\\Owner\\IdeaProjects\\COMP1011_RePsychle_Wyatt_Kirschner_200407722_V4\\src\\sample\\img\\1.gif");
                 resinImage.setImage(Image.impl_fromPlatformImage(inputStream));

         }
     }*/

    /**
     * Checks to see what the resinID is, based on the resinID, the material will be set!
     * If not valid after the resinID check, an Illegal Argument is thrown!
     * @param resinID
     */

    public void materialLoader(int resinID) { // When it comes to creating an object, then I will collect the information to parse into the database

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
        } else {
            throw new IllegalArgumentException("Resin ID is not within range!");
        }
    }

    /**
     * This is slightly complicated but it works. This method takes the resinID, disposal method, and the material
     * Based on what is passed in, the comment will be generated!
     * The first part of the string is created with the resinID included
     * Then based on the disposal method, the comment will be added on with the new statement
     * If select is used, then the comment will be empty
     * This also calls on the ecoComment method since it's easier to isolate
     * @param resinId
     * @param disposalMethod
     * @param material
     */

    public void commentGenerator(int resinId, String disposalMethod, String material) {
        commentLabel.setText("");
        infoLabel.setText("");
        String comment = ("You have selected a product rated with a resin ID of: " + resinId + " and you are attempting to ");

        if (disposalMethod.contains("Recycle")) {
            comment += "recycle an object that contains " + material + ". The ecoScore of the object is " + outputEcoScore(resinId, disposalMethod) + ". " + ecoScoreComment(outputEcoScore(resinId, disposalMethod));
            commentLabel.setText(comment);
        } else if (disposalMethod.contains("Compost")) {
            comment += ("compost an object that contains " + material + ". The ecoScore of the object is " + outputEcoScore(resinId, disposalMethod) + ". " + ecoScoreComment(outputEcoScore(resinId, disposalMethod)));
            commentLabel.setText(comment);
        } else if (disposalMethod.contains("Garbage")) {
            comment += ("place this object in the trash that contains " + material + ". The ecoScore of the object is " + outputEcoScore(resinId, disposalMethod) + ". " + ecoScoreComment(outputEcoScore(resinId, disposalMethod)));
            commentLabel.setText(comment);
        } else if (disposalMethod.contains("Sorting Facility")) {
            comment += ("Bring this object to a sorting facility, that contains " + material + ". The ecoScore of the object is " + outputEcoScore(resinId, disposalMethod) + ". " + ecoScoreComment(outputEcoScore(resinId, disposalMethod)));
            commentLabel.setText(comment);
        } else {
            if (resinSpinner.getValue() != 1) {
                infoLabel.setText("Please select a disposal method!");
                commentLabel.setText(null);
            }
            if (disposalMethod.contains("Select")) {
                infoLabel.setText("Please select a disposal method!");
                commentLabel.setText(null);
                resinSpinner.getValueFactory().setValue(1);
            }
        }
    }

    /**
     * Based on what is passed in, the ecoScoreComment will return the last piece of the comment, based on the ecoScore
     * If not, then an Illegal Argument Exception is thrown
     * @param ecoScoreLabel
     * @return
     */

    private String ecoScoreComment(String ecoScoreLabel) {
        String ecoScoreComment;
        if (ecoScoreLabel.contains("VERY LOW")) {
            ecoScoreComment = "This object contains a material that cannot be disposed in the best way possible. It may cause environmental impact when being disposed. Avoid when possible.";
            return ecoScoreComment;
        } else if (ecoScoreLabel.contains("LOW")) {
            ecoScoreComment = "This object contains a material that can be disposed of in a way that cannot be disposed of in the best way possible. The environmental impact isn’t as prevalent. Use with caution.";
            return ecoScoreComment;
        } else if (ecoScoreLabel.contains("MEDIUM")) {
            ecoScoreComment = "This object contains a material that can be disposed of properly if placed in the proper area. There are great reuses for this material. Clean the material before using the provided disposal method.";
            return ecoScoreComment;
        } else if (ecoScoreLabel.contains("HIGH")) {
            ecoScoreComment = "This object contains a material that is great to reuse and dispose of when done properly. This material can be easily reused. Clean the material and recycle when possible.";
            return ecoScoreComment;
        } else if (ecoScoreLabel.contains("VERY HIGH")) {
            ecoScoreComment = "This object contains a material that is ideal for recycling, and reusability. This material can be easily reused. Clean the material and recycle when possible.";
            return ecoScoreComment;
        } else {
            throw new IllegalArgumentException("The ecoScore comment cannot be retrieved!");
        }
    }

    /**
     * Based on the resinID and the Disposal Method, the ecoScore is generated
     * If else, an Illegal Argument Exception is thrown
     * @param resinID
     * @param disposalInput
     * @return
     */
    // This is to check the disposal input and the resinID to determine the ecoScore
    private String outputEcoScore(int resinID, String disposalInput) {
        //ecoScoreLabel.setText("ERROR");

        if (disposalInput.contains("Recycle")) {
            if (resinID == 3 || resinID == 4 || resinID == 6) {
                ecoScoreLabel.setText("LOW");
            } else if (resinID == 2) {
                ecoScoreLabel.setText("MEDIUM");
            } else if (resinID == 5 | resinID == 7) {
                ecoScoreLabel.setText("HIGH");
            } else if (resinID == 1) {
                ecoScoreLabel.setText("VERY HIGH");
            }
            return ecoScoreLabel.getText();
        } else if (disposalInput.contains("Compost") | disposalInput.contains("Garbage")) {

            if (resinID == 1 || resinID == 5 || resinID == 7) {
                ecoScoreLabel.setText("LOW");
            } else if ((resinID >= 2 && resinID <= 4) | resinID == 6) {
                ecoScoreLabel.setText("VERY LOW");
            }
            return ecoScoreLabel.getText();
        } else if (disposalInput.contains("Sorting Facility")) {
            if (resinID == 2) {
                ecoScoreLabel.setText("HIGH");
            } else if (resinID == 1 || resinID >= 3 || resinID <= 7) {
                ecoScoreLabel.setText("MEDIUM");
            }
            return ecoScoreLabel.getText();
        }
        if (disposalInput.contains("Select")) {

            commentLabel.setText("");

            resinSpinner.getValueFactory().setValue(1);

        } else {
            throw new IllegalArgumentException("Error in generating ecoScore");
        }

        return ecoScoreLabel.getText();
    }

    /**
     * This is responsible for generating the proper ValueFactories, setting the values for the ResinID Spinner and
     * the Material ComboBox
     * This is also checking for action changes using Lambdas
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        commentLabel.setDisable(true);
        commentLabel.setWrapText(true);

        SpinnerValueFactory < Integer > valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7);
        resinSpinner.setValueFactory(valueFactory);


        TextField resinEditor = resinSpinner.getEditor();

        resinEditor.textProperty().addListener((observableValue, oldValue, newValue) -> {

        try {
            Integer.parseInt(newValue); //Checks to see if it's an int
            materialLoader(Integer.parseInt(newValue)); // If so, then I can pass this new Value into a function that will change the material
            if (disposalComboBox.getValue() != null) {
                commentGenerator(resinSpinner.getValue(), disposalComboBox.getValue(), materialLabel.getText());

            } else {

            }
        } catch (NumberFormatException e) {
            resinEditor.setText(oldValue);
            infoLabel.setTextFill(RED);
            infoLabel.setText("Only whole numbers are allowed!");
        }

        resinSpinner.setEditable(true);
        });
        disposalComboBox.getItems().addAll("Select", "Recycle", "Compost", "Garbage", "Sorting Facility");
        disposalComboBox.setEditable(true);
        TextField disposalEditor = disposalComboBox.getEditor();

        disposalEditor.textProperty().addListener((observableValue, oldValue, newValue) -> {

        try {
            disposalComboBox.setEditable(true);

            commentGenerator(resinSpinner.getValue(), newValue, materialLabel.getText());


        } catch (IllegalArgumentException e) {
            disposalComboBox.setValue(oldValue);
            infoLabel.setTextFill(RED);
            infoLabel.setText("Only proper disposal methods allowed!");
        }
        disposalComboBox.setEditable(true);


        });


    }
}