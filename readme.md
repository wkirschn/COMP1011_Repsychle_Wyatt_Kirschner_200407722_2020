
<!DOCTYPE  html>

  

<html  class="no-js">  <!--<![endif]-->

  

<head>

  

<meta  charset="utf-8">

  

<meta  http-equiv="X-UA-Compatible"  content="IE=edge">

  
  
  

<meta  name="description"  content="">

  

<meta  name="viewport"  content="width=device-width, initial-scale=1">

  

<link  rel="stylesheet"  href="">

  

</head>

  

<body>

  
  
  

<!-- display-4,-->

  

<h1  class="text-center"><img  src="/srcsample/img/RePsychle Logo.png" />

Wyatt Kirschner - COMP1011 - A1 - 200407722</h1>

  

<p>This JavaFX application was developed to take the user's input and insert this into the database. When requested, the application will display what is displayed in the database through a table and pie charts.</p>

  
</div>

  

<div  class="text-left">

  

<h2  class="text-center">Why this concept?</h2>

  

<blockquote>I was taking out the recycling at home, and it started to become more difficult to determine which items could be recycled or not. It started to dawn on me that there is a lot of work to properly recycle an object. If you were to recycle a plastic jar of mayo, it would not be able to be recycled unless the insides are completely clean and the label is taken off.  This application is based off the idea that I had for another project, but I was never able to properly handle what could be handled into the database and to retrieve it. This application is another step in the right direction.
</blockquote>

  

<h2  class="text-center">Our Vision</h2>

  <blockquoute>

  This JavaFX application is based on the input of the user. The ecoScore is generated from the ResinID, and the disposal method. When all the information is included, this is sent as an entry to the database. When the table is not empty, the database table and pie charts are displayed.  

In the future, I want to be able to find a database that would give the proper information and that is reliable in order to generate better calculations.


  

An EcoScore would give you a calculation of how efficient the item is. These include:

  

<ol>

  

<li>Ease of environmental disposal</li>

  

<li>How it's produced</li>

  

<li>If the item is safe to your health</li>

  

</ol>

  

<blockquote>

  

The main objective is to give the user a 'snapshot' of the item's lifecycle. If a consumer was able to make a purchase based on these factors as well as many others... people can make better choices on how to recycle. 

  

</blockquote>

  

<h2> Version 0.9 </h2>

  

<blockquote>

  

This JavaFX application is can perform the following:
<ol>
<li>Check for Validation for Brand Name, Product Name, Resin ID, Material, Disposal Method, Eco Comment, and EcoScore</li>
<li>Check for a proper database connection to insert the record into the table</li>
<li>Transitions to a Table View scene when the database contains records </li>
<li>By selecting a row in the Table View, information about that record is parsed into the Comment TextArea </li>
<li>Transition from Table View to Pie Chart View</li>
<li>Display Pie Charts based on EcoScore count, Resin ID count, and Disposal Method count</li>
<li>The TextArea would display how many items in the database the individual count of EcoScore, Resin ID, and Disposal Method respectively</li>
<li>All scenes contain an exit from the program by ending the scene </li>
</ol>


There will need to be more features added and more tweaks to the functionality!

  

</blockquote>
<h2>Improvements</h2>
  <blockquote>
There are some improvements and changes that I would make to the submission of the project. 
<ol>
<li>Included functionality to add items to the database, even if it was a customly written .CSV to show the potential</li>
<li>Reduce some of the code by using TreeMaps / HashMaps for building out custom maps for the Resin ID / Disposal Method / EcoScore Generation</li>
<li>Support for various situations where the ResinID wasn't between 1 - 7, or there isn't an identifier and the ecoScore could be generated based on the material only</li>
</ol>
</blockquote>
<h2>EcoScore Research </h2>
<blockquote>
 
<h3>PETE    -   Polyethylene Terephthalate   -   Resin ID: 1   </h3>  <ol> <li> Soft drinks, water, ketchup, and beer bottles, mouthwash bottles, peanut butter containers, salad dressing, vegetable oil containers </li><li>Rinse of any foods or contents, place caps into the trash! Rest is good for blue bin!</li> <li>   So not the best! </li><li>   If it does pass a purity check, then it's turned into polar Polar fleece, fiber, tote bags, furniture, carpet, paneling, straps, bottles and food containers (as long as the plastic being recycled meets purity standards and doesn't have hazardous contaminants) </li> </ol>  
  
<h3> HDPE    -   High-density polyethylene   - Resin ID: 2 </h3><ol> <li> Milk jugs; juice bottles; bleach, detergent, and other household cleaner bottles; shampoo bottles; some trash and shopping bags; motor oil bottles; butter and yogurt tubs; cereal box liners </li><li>  Little leaching of chemicals </li><li>   Rinse of any foods or contents, the most ideal bottle! </li><li>  Laundry detergent bottles, oil bottles, pens, recycling containers, floor tile, drainage pipe, lumber, benches, doghouses, picnic tables, fencing, shampoo bottles  </li></ol>  
  
<h3> PVC     -   Polyvinyl Chloride          -  Resin ID: 3 </h3> <ol><li> Shampoo and cooking oil bottles, blister packaging, wire jacketing, siding, windows, piping </li><li>   Bad for the environment, since chlorine is part of PVC and can release dioxins during manufacturing </li><li>   Bad for burning </li><li>   Rarely can be recycled, unless taken to some plastic lumber makers</li> <li>   Decks, paneling, mud-flaps, roadway gutters, flooring, cables, speed bumps, mats  </li></ol>  
  
<h3> LDPE    -   Low-Density Polyethylene    -  Resin ID: 4</h3> <ol><li>  Historically not accepted through American recycling programs</li> <li>   Found in squeezable bottles; bread, frozen food, dry cleaning, and shopping bags; tote bags; furniture </li><li>   Recycled into trash can liners and cans, compost bins, shipping envelopes, paneling, lumber, landscaping ties, floor tile </li> </ol>  
  
<h3> PP      -   Polypropylene   -  Resin ID: 5 </h3> <ol> <li>  Good for hot liquids, starting to become more accepted </li><li>   Some yogurt containers, syrup and medicine bottles, caps, straws </li><li>   Can be cleaned and placed in the recycling </li><li>   Caps are still sent to the garbage</li> <li>   Signal lights, battery cables, brooms, brushes, auto battery cases, ice scrapers, landscape borders, bicycle racks, rakes, bins, pallets, trays  </ol>  

<h3> PS      -   Polystyrene     -  Resin ID: 6 </h3> <ol> <li> Rigid or foam products (Styrofoam)</li> <li> Styrene monomer can leach into food and is a possible carcinogen</li> <li>   Disposable plates and cups, meat trays, egg cartons, carry-out containers, Asprin bottles, compact disc cases </li><li>   Not many programs accept them and many manufactures have switched to PET </li><li>   Recycled into Insulation, light switch plates, egg cartons, vents, rulers, foam packaging, carry-out containers </li> </ol>  

<h3> Other    -   Other   -  Resin ID: 7 </h3> <ol> <li>  Polycarbonate has been shown to be a hormone disruptor, PLA (polylactic acid) is made from plants and is carbon neutral, also falls into this category</li> <li>  Found in three- and five-gallon water bottles, bullet-proof materials, sunglasses, DVDs, iPod and Computer Cases, signs and displays certain food containers, nylon </li> <li>   Since they are very different, there will be specific protocols on how to recycle them  
</li></ol>  
 Source:     https://www.goodhousekeeping.com/home/g804/recycling-symbols-plastics-460321/  


</blockquote>
<h2> References Used </h2>
<ol>
<li>Resin ID -  http://www.trashforce.org/content.asp?q_areaprimaryid=3&q_areasecondaryid=44&q_areatertiaryid=0&q_articleid=32</li>
<li>ImageView - https://www.tutorialspoint.com/javafx/javafx_images.htm</li>
<li>https://docs.oracle.com/javafx/2/charts/pie-chart.htm</li>
<li>https://www.tutorialspoint.com/javafx/pie_chart.htm</li>
</ol>

</div>

  

<div  class="text-center">

  

<img  src="src/sample/img/RePsychle Favicon Icon.png" />

  

</div>

  
  
  

</body>

  

</html>