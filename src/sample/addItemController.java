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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
import static javafx.scene.paint.Color.RED;

public class addItemController implements Initializable {



    @FXML
    private TextField brandNameTextField;

    @FXML
    private TextField productNameTextField;

    @FXML
    private Spinner < Integer > resinSpinner;


    @FXML
    private ComboBox < String > disposalComboBox;

    @FXML
    private Label materialLabel;

    @FXML
    private TextArea commentLabel;

    @FXML
    private Label ecoScoreLabel;

    @FXML
    private Label infoLabel;

    @FXML
    private Label objectLabel;

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
    void statisticsButton(ActionEvent event) throws SQLException, IOException {
        // There will be a check where it will determine if there is anything in the database table. If not, it will be asked to add an item first to the database
        infoLabel.setText("");
        if(DBUtility.getAllProducts().isEmpty()) {
            infoLabel.setText("Please add an entry before viewing the statistics!");

        }
        else {
            infoLabel.setText("Switching to Statistics...");
            Parent root = FXMLLoader.load(getClass().getResource("databaseTable.fxml"));
            Stage primaryStage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();

            primaryStage.setScene(new Scene(root, 800, 400));
            primaryStage.setTitle("RePsychle - Statistics");
            primaryStage.show();
        }
    }

    @FXML
    void submitButton(ActionEvent event) {
        System.out.println("Send!");
        // This is to add the item to the database
        if (inputValidation()) {
            System.out.println("VALIDATION PASSES");
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

                System.out.println("This works!");
                System.out.println(newObject.toString());
            }   catch (IllegalArgumentException e) {
                e.printStackTrace();
                infoLabel.setText(e.getMessage());
            }




        } else {
            throw new IllegalArgumentException("Make sure that the fields are properly filled out!");
        }




    }

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


    public void materialLoader(int resinID)  { // When it comes to creating an object, then I will collect the information to parse into the database
        if (resinID >= 1 || resinID <= 7) {
            if (resinID == 1) {
                materialLabel.setText("PETE: Polyethylene Terephthalate");


                //resinImage.setImage(one);

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



    public void commentGenerator(int resinId, String disposalMethod, String material) {
        commentLabel.setText("");
        infoLabel.setText("");
        System.out.println(disposalMethod + "WOAH");
        String comment = ("You have selected a product rated with a resin ID of: " + resinId + " and you are attempting to ");


        if (disposalMethod.contains("Recycle")) {
            comment += "recycle an object that contains " + material + ". The ecoScore of the object is " + outputEcoScore(resinId, disposalMethod) + ". " + ecoScoreComment(outputEcoScore(resinId, disposalMethod));
            //comment += "recycle an object that contains " + material + ". The ecoScore of the object is ";
            //comment += outputEcoScore(resinId, disposalMethod) + ". "; //+ ecoScoreComment(outputEcoScore(resinId, disposalMethod));


            System.out.println(outputEcoScore(resinId, "Recycle"));

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

            if(resinSpinner.getValue() != 1) {
                infoLabel.setText("Please select a disposal method!");
                commentLabel.setText(null);

            }
            else {
                infoLabel.setText("Please select a disposal method!");
                commentLabel.setText(null);
                SpinnerValueFactory < Integer > valueFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7);
                resinSpinner.setValueFactory(valueFactory);
            }


        }

        System.out.println(comment);






    }
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

            SpinnerValueFactory < Integer > valueFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7);
            resinSpinner.setValueFactory(valueFactory);

        } else {
            throw new IllegalArgumentException("Error in generating ecoScore");
        }
        System.out.println("ZZZ" + ecoScoreLabel.getText());
        return ecoScoreLabel.getText();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        commentLabel.setDisable(true);
        commentLabel.setWrapText(true);

        SpinnerValueFactory < Integer > valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 7);
        resinSpinner.setValueFactory(valueFactory);
        /*

        disposalComboBox.getItems().addAll("Recycle", "Garbage", "Local Disposal Facility");*/
        // Validation for Resin Spinner

        TextField resinEditor = resinSpinner.getEditor();

        resinEditor.textProperty().addListener((observableValue, oldValue, newValue) -> {
                System.out.printf("Old Resin ID: %s New Resin ID: %s%n", oldValue, newValue);
        try {
            Integer.parseInt(newValue); //Checks to see if it's an int
            materialLoader(Integer.parseInt(newValue)); // If so, then I can pass this new Value into a function that will change the material
            if(disposalComboBox.getValue() != null) {
                commentGenerator(resinSpinner.getValue(), disposalComboBox.getValue(), materialLabel.getText());

            }
            else  {
                System.out.println("S");
            }
        } catch (NumberFormatException e) {
            resinEditor.setText(oldValue);
            objectLabel.setTextFill(RED);
            objectLabel.setText("Only whole numbers are allowed!");
        }

        resinSpinner.setEditable(true);
        });
        disposalComboBox.getItems().addAll("Select", "Recycle", "Compost", "Garbage", "Sorting Facility");
        disposalComboBox.setEditable(true);
        TextField disposalEditor = disposalComboBox.getEditor();

        disposalEditor.textProperty().addListener((observableValue, oldValue, newValue) -> {
                System.out.printf("Old Disposal Method: %s, New Disposal Method: %s%n", oldValue, newValue);

        String tossValue = newValue;
        try {
            disposalComboBox.setEditable(true);
            System.out.println(resinSpinner.getValue() + newValue + materialLabel.getText()); // Need to check to see when Resin and Disposal are Blank
            commentGenerator(resinSpinner.getValue(), newValue, materialLabel.getText());
            //ecoScoreGeneration(resinSpinner.getValue(), newValue);

        } catch (IllegalArgumentException e) {
            disposalComboBox.setValue(oldValue);
            objectLabel.setTextFill(RED);
            objectLabel.setText("Only proper disposal methods allowed!");
        }
        disposalComboBox.setEditable(true);


        });


    }
}