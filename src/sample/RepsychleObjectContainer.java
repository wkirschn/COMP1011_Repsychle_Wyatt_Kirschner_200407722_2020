/**
 *    Name:       Wyatt Kirschner
 *    Student ID: 200407722
 *    Date:       11/01/20
 *    Notes:    All notes will be placed in a README.md
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

    private final String nameRegEx = "[A-Za-z\\s]{1,50}";


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

    public RepsychleObjectContainer(int id, String brandName, String objectName, int resinIdCode, String material, String disposal, String ecoDoc, String ecoScore) {
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


    public RepsychleObjectContainer(String brandName, String objectName, int resinIdCode, String material, String disposal, String ecoDoc, String ecoScore) {

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
        } catch (SQLException e) {
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

    /**
     Validation for the same name or if it matches a Regex is needed
     If the name is the same, then new name
     Logically, two markers are not identical
     A specific BIC pen would be different from a knock-off pen

     Throws an Illegal Argument if not valid

     */

    public void setObjectName(String objectName) {
        if (objectName.matches(nameRegEx)) {

            this.objectName = objectName;
        } else {
            throw new IllegalArgumentException("Please enter in a valid object name!");
        }
    }

    /**
     *  Retrieves the Material
     * @return
     */

    public String getMaterial() {
        return material;
    }

    /**
     * Set's the material from the label, or an Illegal Argument Exception is thrown
     * @return
     */


    public void setMaterial(String material) {
        if (material != null) {
            this.material = material;
        } else {
            throw new IllegalArgumentException("Please ensure a valid material is loaded into the Label");
        }
    }

    /**
     * Returns the Disposal Method
     * @return
     */

    public String getDisposal() {
        return disposal;
    }

    /*
        Once again, if a certain item is selected, then they cannot input
     */

    /**
     * I could have created an ArrayList of the Combobox or grabbed it directly, this would require me to create a constructor
     * I've decided to use .equalsIgnoreCase instead
     *
     * If it's not a valid disposal method, an Illegal Argument Exception is thrown
     * @param disposal
     */

    public void setDisposal(String disposal) {
        if (disposal.equalsIgnoreCase("Recycle") || disposal.equalsIgnoreCase("Garbage") || disposal.equalsIgnoreCase("Compost") || disposal.equalsIgnoreCase("Sorting Facility")) {
            this.disposal = disposal;
        } else {
            throw new IllegalArgumentException("Please enter a disposal method that is either: Recycle, Garbage, Compost, or Sorting Facility!");
        }
    }

    /**
     * Retrieves the ecoScore
     * @return
     */

    public String getEcoScore() {
        return ecoScore;
    }

    /**
     * Checks the String for a pre-determined ecoScore Rating, if else, throws Illegal Argument Exception
     * @param ecoScore
     */

    public void setEcoScore(String ecoScore) {
        if (ecoScore.equalsIgnoreCase("VERY LOW") || ecoScore.equalsIgnoreCase("LOW") || ecoScore.equalsIgnoreCase("MEDIUM") || ecoScore.equalsIgnoreCase("HIGH") || ecoScore.equalsIgnoreCase("VERY HIGH")) {
            this.ecoScore = ecoScore;
        } else {
            throw new IllegalArgumentException("Please ensure a valid EcoScore rating is produced!");
        }
    }

    /**
     * Retrieves the Eco Comment
     * @return
     */

    public String getEcoDoc() {
        return ecoDoc;
    }

    /**
     * Set's the Eco Comment, it will throw an Illegal Argument Exception if it's not valid
     * @param ecoDoc
     */

    public void setEcoDoc(String ecoDoc) {
        if (ecoDoc != null) {
            this.ecoDoc = ecoDoc;
        } else {
            throw new IllegalArgumentException("Please enter a valid comment regarding the product!");
        }
    }

    /**
     * This will return a basic string that contains all the information about the item.
     * @return
     */

    public String toString() {
        return String.format("Brand Name: %s, Product Name: %s, Resin ID: %d, Material: %s, Disposal Method: %s, Comment: %s, EcoScore: %s",
                brandName, objectName, resinIdCode, material, disposal, ecoDoc, ecoScore);

    }

    // I could have made these inside of the viewChartController, but I find it much more neat inside the RepsychleObjectContainer

    /**
     * This will be for the including the comment for the table that will output the information in a proper format
     * @param brandNameTable
     * @param productNameTable
     * @param resinIdTable
     * @param materialTable
     * @param disposalTable
     * @param ecoCommentTable
     * @param ecoScoreTable
     * @return
     */

    public String toCommentString(String brandNameTable, String productNameTable, int resinIdTable, String materialTable, String disposalTable, String ecoCommentTable, String ecoScoreTable) {
        return String.format("Brand Name: %s \n\nProduct Name: %s\n\nResin ID: %d\n\nMaterial: %s\n\nDisposal Method: %s\n\nComment: %s\n\nEcoScore: %s",
                brandNameTable, productNameTable, resinIdTable, materialTable, disposalTable, ecoCommentTable, ecoScoreTable);
    }

    /**
     * This will output the comment the count for how many products of each ecoScore that is in the database
     * @param veryLow
     * @param low
     * @param medium
     * @param high
     * @param veryHigh
     * @return
     */

    public String toEcoScorePie(int veryLow, int low, int medium, int high, int veryHigh) {
        return String.format("Eco Score:\n\nVery Low: %d\n\nLow: %d\n\nMedium: %d\n\nHigh: %d\n\nVery High: %d", veryLow, low, medium, high, veryHigh);
    }

    /**
     * This will output the comment the count for how many products of each resinID that is in the database
     * @param resinOne
     * @param resinTwo
     * @param resinThree
     * @param resinFour
     * @param resinFive
     * @param resinSix
     * @param resinSeven
     * @return
     */

    public String toResinIdPie(int resinOne, int resinTwo, int resinThree, int resinFour, int resinFive, int resinSix, int resinSeven) {
        return String.format("Resin ID:\n\nOne: %d\n\nTwo: %d\n\nThree: %d\n\nFour: %d\n\nFive:%d\n\nSix: %d\n\nSeven: %d", resinOne, resinTwo, resinThree, resinFour, resinFive, resinSix, resinSeven);
    }

    /**
     * This will output the comment the count for how many products of each Disposal Method that is in the database
     * @param reycleCounter
     * @param compostCounter
     * @param garbageCounter
     * @param sortingCounter
     * @return
     */

    public String toDisposalPie(int reycleCounter, int compostCounter, int garbageCounter, int sortingCounter) {
        return String.format("Disposal Methods:\n\nRecycle: %d\n\nCompost: %d\n\nGarbage: %d\n\nSorting Facility: %d", reycleCounter, compostCounter, garbageCounter, sortingCounter);
    }








}