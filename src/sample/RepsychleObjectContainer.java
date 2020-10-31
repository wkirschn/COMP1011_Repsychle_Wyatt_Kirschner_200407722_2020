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

    Image Sources:
        Resin ID -  http://www.trashforce.org/content.asp?q_areaprimaryid=3&q_areasecondaryid=44&q_areatertiaryid=0&q_articleid=32
        ImageView - https://www.tutorialspoint.com/javafx/javafx_images.htm

 */


package sample;

import java.sql.SQLException;

public class RepsychleObjectContainer {
    private int id; // This will be the Primary key
    private int resinIdCode;
    private String brandName, objectName, material, disposal, ecoDoc, ecoScore;
    //Name: Marker, Plastic, Recycle
    // Later on, I will implement different lists so I can have the material name, EcoScore, disposal method
    //As I am putting these into strings, I can create different Hashlists or arraylists to read out of later

    private final String nameRegEx = "[A-Za-z\\s]{1,50}"; //Take another look





    // Use this to create the object since the primary key is automatically incremented so no need to pass a predefined Primary Key

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

    public RepsychleObjectContainer(String brandName, String objectName, int resinIdCode,  String material, String disposal, String ecoDoc, String ecoScore) {

        setBrandName(brandName);
        setObjectName(objectName);
        setResinIdCode(resinIdCode);
        setMaterial(material);
        setDisposal(disposal);
        setEcoDoc(ecoDoc);
        setEcoScore(ecoScore);

        try {
            int id = DBUtility.insertNewProduct(this);
            setId(id);
            System.out.println("INSERTED");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RepsychleObjectContainer(String ecoScore) {
        setEcoScore(ecoScore);
    }
    public RepsychleObjectContainer() {
        // Used to access methods when needed
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        if (id > 0) { // Okay for now, but there is nothing to stop me from overwriting a previously created ID
            this.id = id;
        } else {
            throw new IllegalArgumentException("Please select an ID that is greater than 0!");
        }
    }

    public int getResinIdCode() {
        return resinIdCode;
    }

    public void setResinIdCode(int resinIdCode) {
        if (resinIdCode >= 0 && resinIdCode <= 7) {
            this.resinIdCode = resinIdCode;
        } else {
            throw new IllegalArgumentException("Please enter a valid Resin ID Code from 0 to 7!");
        }
    }

    public String getBrandName() {
        return brandName;
    }

    /*
        Need to generate an EcoScore Percentage (Not even close to being accurate):
            Size of the Item:
            Recycle Rating:
                PETE    -   Polyethylene Terephthalate   -   1   -   High
                        *   Soft drinks, water, ketchup, and beer bottles, mouthwash bottles, peanut butter containers,
                            salad dressing, vegetable oil containers
                        *   Rinse of any foods or contents, place caps into the trash! Rest is good for blue bin!
                        *   So not the best
                        *   If it does pass a purity check, then it's turned into polar Polar fleece, fiber, tote bags,
                        furniture, carpet, paneling, straps, bottles and food containers
                        (as long as the plastic being recycled meets purity standards and doesn't have hazardous contaminants)


                HDPE    -   High-density polyethylene   -   2   -   High
                        *   Milk jugs; juice bottles; bleach, detergent, and other household cleaner bottles; shampoo bottles;
                        some trash and shopping bags; motor oil bottles; butter and yogurt tubs; cereal box liners
                        *   Little leaching of chemicals
                        *   Rinse of any foods or contents, the most ideal bottle!
                        *   Laundry detergent bottles, oil bottles, pens, recycling containers,
                        floor tile, drainage pipe, lumber, benches, doghouses, picnic tables, fencing, shampoo bottles


                PVC     -   Polyvinyl Chloride          -   3   -   Low
                        *   Shampoo and cooking oil bottles, blister packaging, wire jacketing, siding, windows, piping
                        *   Bad for the environment, since chlorine is part of PVC and can release dioxins during
                            manufacturing
                        *   Bad for burning
                        *   Rarely can be recycled, unless taken to some plastic lumber makers
                        *   Decks, paneling, mud-flaps, roadway gutters, flooring, cables, speed bumps, mats


                LDPE    -   Low-Density Polyethylene    -   4   -   Medium
                        *   Historically not accepted through American recycling programs
                        *   Found in squeezable bottles; bread, frozen food, dry cleaning, and shopping bags; tote bags; furniture
                        *   Recycled into trash can liners and cans, compost bins, shipping envelopes, paneling, lumber,
                        landscaping ties, floor tile


                PP      -   Polypropylene   -   5   -  Medium
                        *   Good for hot liquids, starting to become more accepted
                        *   Some yogurt containers, syrup and medicine bottles, caps, straws
                        *   Can be cleaned and placed in the recycling
                        *   Caps are still sent to the garbage
                        *   Signal lights, battery cables, brooms, brushes, auto battery cases, ice scrapers, landscape borders,
                            bicycle racks, rakes, bins, pallets, trays

                PS      -   Polystyrene     -   6   -   Low
                        *   Rigid or foam products (Styrofoam)
                        *   Styrene monomer can leach into food and is a possible carcinogen
                        *   Disposable plates and cups, meat trays, egg cartons, carry-out containers, Asprin bottles, compact disc cases
                        *   Not many programs accept them and many manufactures have switched to PET
                        *   Recycled into Insulation, light switch plates, egg cartons, vents, rulers, foam packaging, carry-out containers

               Other    -   Other   -   7   -   Low
                        *   Polycarbonate has been shown to be a hormone disruptor, PLA (polylactic acid) is made from plants and is carbon neutral, also falls into this category
                        *   Found in three- and five-gallon water bottles, bullet-proof materials, sunglasses, DVDs, iPod and Computer Cases, signs and displays
                            certain food containers, nylon
                        *   Since they are very different, there will be specific protocols on how to recycle them


        Source:     https://www.goodhousekeeping.com/home/g804/recycling-symbols-plastics-460321/

     */


    public void setBrandName(String brandName) { // If you have a Brand Name, then in the future... have it isolate the
        if (brandName.matches(nameRegEx)) {
            // Specific CSV or list it can pull from, this is just simple concept
            this.brandName = brandName;
        }
        this.brandName = brandName;
    }

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