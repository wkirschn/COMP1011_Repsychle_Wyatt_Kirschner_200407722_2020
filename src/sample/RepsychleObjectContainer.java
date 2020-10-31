/**
 *    Name:       Wyatt Kirschner
 *    Student ID: 200407722
 *    Date:       10/24/20
 *    Notes:
 *        I have made some modifications so far. Changed how the Regular Expressions work with the various reasons of
 *        data input. I have also started to incorporate how an Add Product scene will limit the scope of entry on
 *        certain items. The EcoScore will be generated from the Resin ID, since this rating does state how easy it is
 *        to recycle or how harmful the product is!
 *
 *        I'm planning on having the input of how the item being disposed of generating a comment based on the user's actions.
 *
 *        The comments section will also be auto generated based the Resin ID selected and the dispsosal method
 *
 *        I would need to next have functions that will set the label values as required.
 *
 *        The purpose of this is to generate the hardcode needed to have these objects being created with success
 *
 *        I will then need to place this into the database and then the charts / table
 *
 *    Image Sources:
 *         Resin ID -  http://www.trashforce.org/content.asp?q_areaprimaryid=3&q_areasecondaryid=44&q_areatertiaryid=0&q_articleid=32
 *        ImageView - https://www.tutorialspoint.com/javafx/javafx_images.htm
 *
 */

package sample;

import java.sql.SQLException;

/**
 *  This is the main class for the RePsychle Application. This will be where I will store my setters, getters,
 *  and other methods I may need.
 *
 */

public class RepsychleObjectContainer {
    /**
     *  id is for the Primary Key
     */

    private int id; // This will be the Primary key
    /**
     *  resinID is for holding the resinID number that is at the bottom of the item
     */
    private int resinIdCode;
    /**
     * Brand Name is to store the Brand Name
     * Object Name is used in conjunction with Product Name
     * Material is for the material type
     * Disposal is the disposal method used
     * ecoDoc is used in conjunction with ecoComment or comment
     * ecoScore is the rating that is given, the higher the better!
     *
     */

    private String brandName, objectName, material, disposal, ecoDoc, ecoScore;

    /**
     * This is a Regular Expression that is used to ensure that nothing malicious is occurs during entry
     */

    private final String nameRegEx = "[A-Za-z\\s]{1,50}"; //Take another look


    /**
     *
     * @param id
     * @param brandName
     * @param objectName
     * @param resinIdCode
     * @param material
     * @param disposal
     * @param ecoDoc
     * @param ecoScore
     *
     * Use this to create the object since the primary key is automatically incremented so no need to pass a predefined Primary Key
     * This is used for when I need to insert the product into the Database
     */

    public RepsychleObjectContainer(int id, String brandName, String objectName, int resinIdCode,  String material, String disposal, String ecoDoc, String ecoScore) {
        setId(id);
        setBrandName(brandName);
        setObjectName(objectName);
        setResinIdCode(resinIdCode);
        setMaterial(material);
        setDisposal(disposal);
        setEcoDoc(ecoDoc);
        setEcoScore(ecoScore);
    }

    /**
     *
     * @param brandName
     * @param objectName
     * @param resinIdCode
     * @param material
     * @param disposal
     * @param ecoDoc
     * @param ecoScore
     *
     * Since I don't need to retrieve the Primary Key,
     * this constructor will retrieve the information from the database when needed and when I need to create objects locally.
     */


    public RepsychleObjectContainer(String brandName, String objectName, int resinIdCode,  String material, String disposal, String ecoDoc, String ecoScore) {

        setBrandName(brandName);
        setObjectName(objectName);
        setResinIdCode(resinIdCode);
        setMaterial(material);
        setDisposal(disposal);
        setEcoDoc(ecoDoc);
        setEcoScore(ecoScore);

        /**
         * When required, I can update the database when needed and insert the primary key as well
         */

        try {
            int id = DBUtility.insertNewProduct(this);
            setId(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is an empty constructor that I can create when I want to access methods outside of this class
     */


    public RepsychleObjectContainer() {

    }

    /**
     * This will return the Primary Key as needed
     */

    public int getId() {
        return id;
    }

    /**
     * This method will check to see if the Primary Key is greater than 0 to ensure that it's technically able to insert
     * itself into the objects table
     * If not, an IllegalArgumentException is thrown
     * @param id
     */

    private void setId(int id) {
        if (id > 0) { // Okay for now, but there is nothing to stop me from overwriting a previously created ID
            this.id = id;
        } else {
            throw new IllegalArgumentException("Please select an ID that is greater than 0!");
        }
    }

    /**
     * This will retrieve the resinID
     */

    public int getResinIdCode() {
        return resinIdCode;
    }

    /**
     * By ensuring that the resinID is within the range of this program, I can have better control of what can be inputted
     * Another iteration of this program would involve reading from a CSV and matching it as so to see if one would exist
     * An IllegalArgumentException is thrown if not within the ranges set
     * @param resinIdCode
     */

    public void setResinIdCode(int resinIdCode) {
        if (resinIdCode >= 0 && resinIdCode <= 7) {
            this.resinIdCode = resinIdCode;
        } else {
            throw new IllegalArgumentException("Please enter a valid Resin ID Code from 0 to 7!");
        }
    }

    /**
     *  Retrieves the Brand Name
     */


    public String getBrandName() {
        return brandName;
    }

    /**
     * Checks to see if the Brand Name will match the name Regular Expression to prevent SQL injection
     * @param brandName
     */


    public void setBrandName(String brandName) { // If you have a Brand Name, then in the future... have it isolate the
        if (brandName.matches(nameRegEx)) {
            this.brandName = brandName;
        }
        this.brandName = brandName;
    }

    /**
     *
     * @return
     */

    public String getObjectName() {
        return objectName;
    }

    /*
        Validation for the same name or if it matches a Regex is needed
        If the name is the same, then new name
        Logically, two markers are not identical
        A specific BIC pen would be different from a knock-off pen

     */

    public void setObjectName(String objectName) {
        if (objectName.matches(nameRegEx)) {

            this.objectName = objectName;
        } else {
            throw new IllegalArgumentException("Please enter in a valid object name!");
        }
    }

    public String getMaterial() {
        return material;
    }

    /*
        Another challenge is to have the person select the material that is at the packaging and then the options occur
        I think I will have the input page as a portal for the admin to plug in certain values and then the user can see the specific windows
        Not sure how to do login validation
        I would need to create an arraylist or hash based on the symbol code and then it can fill it in?
     */


    public void setMaterial(String material) {
        if (material != null) {
            this.material = material;
        }
    }

    public String getDisposal() {
        return disposal;
    }

    /*
        Once again, if a certain item is selected, then they cannot input
     */

    public void setDisposal(String disposal) {
        if (disposal.equalsIgnoreCase("Recycle") || disposal.equalsIgnoreCase("Garbage") || disposal.equalsIgnoreCase("Compost") || disposal.equalsIgnoreCase("Sorting Facility")) {
            this.disposal = disposal;
        } else {
            throw new IllegalArgumentException("Please enter a disposal method that is either: Recycle, Garbage, Compost, or Sorting Facility!");
        }
    }

    public String getEcoScore() {
        return ecoScore;
    }

    public void setEcoScore(String ecoScore) {
        if (ecoScore != null) {
            this.ecoScore = ecoScore;
        } else {
            throw new IllegalArgumentException("Please ensure a valid EcoScore rating is produced!");
        }
    }

    public String getEcoDoc() {
        return ecoDoc;
    }

    public void setEcoDoc(String ecoDoc) {
        if (ecoDoc != null) {
            this.ecoDoc = ecoDoc;
        } else {
            throw new IllegalArgumentException("Please enter a valid comment regarding the product!");
        }
    }

    public String toString() {
        return String.format("Brand Name: %s, Product Name: %s, Resin ID: %d, Material: %s, Disposal Method: %s, Comment: %s, EcoScore: %s",
              brandName, objectName, resinIdCode, material, disposal, ecoDoc, ecoScore);

    }

    // I could have made these inside of the viewChartController, but I find it much more neat inside the RepsychleObjectContainer


    public String toCommentString(String brandNameTable, String productNameTable, int resinIdTable, String materialTable, String disposalTable, String ecoCommentTable, String ecoScoreTable) {
        return String.format("Brand Name: %s \n\nProduct Name: %s\n\nResin ID: %d\n\nMaterial: %s\n\nDisposal Method: %s\n\nComment: %s\n\nEcoScore: %s",
                brandNameTable, productNameTable, resinIdTable, materialTable, disposalTable, ecoCommentTable, ecoScoreTable);
    }

    public String toEcoScorePie(int veryLow, int low, int medium, int high, int veryHigh) {
        return String.format("Eco Score:\n\nVery Low: %d\n\nLow: %d\n\nMedium: %d\n\nHigh: %d\n\nVery High:%d", veryLow, low, medium, high, veryHigh );
    }

    public String toResinIdPie(int resinOne, int resinTwo, int resinThree, int resinFour, int resinFive, int resinSix, int resinSeven) {
        return String.format("Resin ID:\n\nOne: %d\n\nTwo: %d\n\nThree: %d\n\nFour: %d\n\nFive:%d\n\nSix: %d\n\nSeven: %d", resinOne, resinTwo, resinThree, resinFour, resinFive, resinSix, resinSeven);
    }

    public String toDisposalPie(int reycleCounter, int compostCounter, int garbageCounter, int sortingCounter) {
        return String.format("Disposal Methods:\n\nRecycle: %d\n\nCompost: %d\n\nGarbage: %d\n\nSorting Facility: %d", reycleCounter, compostCounter, garbageCounter, sortingCounter);
    }








}