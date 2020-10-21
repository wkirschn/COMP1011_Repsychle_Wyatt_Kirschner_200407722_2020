package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class RepsychleObjectContainer {
    private int id; // This will be the Primary key
    private String brandName, objectName, material, disposal;
    private Double ecoScore;
    //Name: Marker, Plastic, Recycle
        // Later on, I will implement different lists so I can have the material name, ecoscore, disposal method
    //As I am putting these into strings, I can create different Hashlists or arraylists to read out of later
    private final String nameRegEx = "[A-Z][a-zA-Z\\-\\s']";


    public RepsychleObjectContainer(int id, String brandName, String objectName, String material, String disposal, Double ecoScore) {
        setId(id);
        setBrandName(brandName);
        setObjectName(objectName);
        setMaterial(material);
        setDisposal(disposal);
        setEcoScore(ecoScore);



    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id > 0) {            // Okay for now, but there is nothing to stop me from overwriting a previously created ID
            this.id = id;
        }
       else {
           throw new IllegalArgumentException("Please select an ID that is greater than 0");
        }
    }

    public String getBrandName() {
        return brandName;
    }

    /*
        Need to generate an EcoScore Percentage (Not even close to being accurate):
            Size of the Item:
            Recycle Rating:
                PETE    -   Polyethylene Terepthalate   -   1   -   High
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
                        *   Styrene monomer can leach into food and is a possible carinogen
                        *   Disposable plates and cups, meat trays, egg cartons, carry-out containers, asprin bottles, compact disc cases
                        *   Not many programs accept them and many manufactures have switched to PET
                        *   Recycled into Insulation, light switch plates, egg cartons, vents, rulers, foam packaging, carry-out containers

                Other   -   Other   -   7   -   Low
                        *   Polycarbonate has been shown to be a hormone disruptor, PLA (polylactic acid) is made from plants and is carbon neutral, also falls into this category
                        *   Found in three- and five-gallon water bottles, bullet-proof materials, sunglasses, DVDs, iPod and Computer Cases, signs and displays
                            certain food containers, nylon
                        *   Since they are very different, there will be specific protocols on how to recycle them


        Source:     https://www.goodhousekeeping.com/home/g804/recycling-symbols-plastics-460321/

     */


    public void setBrandName(String brandName) {                // If you have a Brand Name, then in the future... have it isolate the
        if(brandName.matches(nameRegEx)) {
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
        if(objectName.matches(nameRegEx)) {
            this.objectName = objectName;
        }
        else {
            throw new IllegalArgumentException("Please enter in a valid object name");
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
        if(material.matches(nameRegEx)) {


            this.material = material;           // Validation for the Regex
        }
    }

    public String getDisposal() {
        return disposal;
    }

    /*
        Once again, if a certain item is selected, then they cannot input
     */

    public void setDisposal(String disposal) {
        this.disposal = disposal;
    }

    public Double getEcoScore() {
        return ecoScore;
    }

    public void setEcoScore(Double ecoScore) {
        this.ecoScore = ecoScore;
    }
}
