import org.code.theater.*;
import org.code.media.*;

public class FactionsScene extends Scene {

  /*
  * Instance Variables
  * Used to store the images and the second part of the captions
  */
  private String[][] images = {{"Imperium1.jpeg", "Imperium2.jpeg", "Imperium3.jpeg", "Imperium4.jpeg"},
                               {"Chaos1.jpeg", "Chaos2.jpeg", "Chaos3.jpeg", "Chaos4.jpeg"},
                               {"Alien1.jpeg", "Alien2.jpeg", "Alien3.jpeg", "Alien4.jpeg"}};
  private String[][] captions = {{"Space Marine", "Tech Preists", "Imperial Guard", "Sisters of Battle"},
                                 {"Thousand Sons", "World Eaters", "Emperors Children", "Black Legion"},
                                 {"Aeldari", "Tyranids", "Orks", "Tau"}};
  
  /*
  * Constructor
  */
  public FactionsScene() {
    
  }

  /**
   * Top-level drawScene method which will draw the whole animation
   * Preconditions: images and caption are defined as 2D arrays with atleast one row, and all the Strings in images match to a downloaded asset
   * Postconditions: None
   * Displays the home screen, then iterates through the different factions and thier loyaties
   * Applies a filter to each image corresponding to thier faction
   */
  public void drawScene() {
    //Play a background sound
    playSound("FactionSound1.wav");

    //Clear the screen
    clear("gray");

    //Set the text attributes
    setTextColor("white");
    setTextHeight(50);
    
    //Draw the home screen image & title
    ImageFilter Logo = new ImageFilter("Logo.png");
    drawImage(Logo, 50, 200, 300);    
    drawText("Unit 5 Project", 60, 100);
    setTextHeight(25);
    drawText("By Atlas A", 60, 150);    

    //Wait and clear the screen
    pause(1.5); 
    clear("gray");

    //Set the sub header for the class
    setTextHeight(50);
    drawText("Factions", 100, 90);

    //Wait, clear the screen, and reset the text size
    pause(1);
    clear("gray");
    setTextHeight(25);

    // For every item in the array images, iterating in row-major order
    for(int row = 0; row<images.length; row++){
     for(int col = 0; col<images[row].length; col++){

       //When the first sound clip runs out, play the second one
       if(row == 1 && col == 1){
        playSound("FactionSound2.wav"); 
       }

       //Set the current image to a temporary variable
       ImageFilter currentImage = new ImageFilter(images[row][col]);

       //Draw the image and the corresponding caption (through calling the getFaction method)
       drawImage(currentImage, 0, 100, getWidth());
       drawText(getFaction(row, col), 90, 75);

       //Wait
       pause(1.25);

       //Apply the corresponding filter (through calling the applyFilter method), and redraw the image
       applyFilter(row, currentImage);
       drawImage(currentImage, 0, 100, getWidth());

       //Wait and clear the screen
       pause(1);
       clear("gray");
       
     } 
    }
  }

  /*
  * Method to apply a filter to the image based on its loyatly/row number
  * Preconditions: rowLoyatly is a int number in [0, 2] that matches the row number of the toFilter image, toFilter is a defined ImageFilter in the 2D array images
  * Postconditions: None
  */
  public void applyFilter(int rowLoyatly, ImageFilter toFilter){

    //If the image is in row 0 (its loyatly is Imperium), apply the makeDark() filter
    if(rowLoyatly == 0){
      toFilter.makeDark();
    }

    //If the image is in row 1 (its loyatly is Chaos), apply the makeNegative() filter
    if(rowLoyatly == 1){
      toFilter.makeNegative();
    }

    //If the image is in row 2 (itsloyatly is Alien), apply the applySepia() filter
    if(rowLoyatly == 2){
      toFilter.applySepia();
    }
  }

  /*
  * Method to decide the caption for each image
  * Preconditions: row and col are defined as int and are valid idexes in the 2D array images
  * Postconditions: returns a String containing the loyatly and the faction of the image in images at images[row][col]
  */
  public String getFaction(int row, int col){
    //Define a value to be returned
    String result = "";

    //If the image is in row 0 (its loyatly is Imperium), add "Imperium: " to the caption
    if(row == 0){
      result += "Imperium: ";
    }

    //If the image is in row 1 (its loyatly is Chaos), add "Chaos: " to the caption
    if(row == 1){
      result += "Chaos: ";
    }

    //If the image is in row 2 (its loyatly is Alien, add "Alien: " to the caption
    if(row == 2){
      result += "Aliens: ";
    }

    //Add the name of the faction to the result
    result += captions[row][col];

    //Return the generated caption
    return result;
  }
  
}