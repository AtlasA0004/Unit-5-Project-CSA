import org.code.theater.*;
import org.code.media.*;

public class Books_ModelsScene extends Scene {

  /*
  * Instance Variables 
  * Used to store the images
  */
  private String[][] images = {{"Book1.jpeg", "Book2.jpeg"},
                                 {"Model1.jpeg", "Model2.jpeg"}};
  
  /*
  * Constructor
  */
  public Books_ModelsScene() {

  }

  /**
   * Top-level drawScene method which will draw the whole animation
   * Preconditions: images is defined as a 2D array with atleast 1 row
   * Postconditions: None
   * Draws a subheading and displays the images
   * Applies a filter based on whether it is a book or a model
   */
  public void drawScene() {
    //Play a background sound
    playSound("Books_ModelsSound.wav");

    //Clear and set the text attributes
    clear("gray");   
    setTextColor("white");
    setTextHeight(40);

    //Draw the sub header for the class
    drawText("My Books and Models", 10, 90);

    //Wait and clear the screen
    pause(1);
    clear("gray");

    // For every item in the array images, iterating in row-major order
    for(int row=0; row<images.length; row++){
      for(int col =0; col<images[row].length; col++){

       //Set the current image to a temporary variable
        ImageFilter currentImage = new ImageFilter(images[row][col]);

        //Draw the current image, getting the dimensions and position through calling getDimension().
        drawImage(currentImage, getDimension(row, "x"), getDimension(row, "y"), getDimension(row, "width"));

        //Wait
        pause(1.25);

        //Apply the corresponding filter (through calling the applyFilter method), and redraw the image
        applyFilter(row, currentImage);
        drawImage(currentImage, getDimension(row, "x"), getDimension(row, "y"), getDimension(row, "width"));

       //Wait and clear the screen
        pause(1);
        clear("gray");
      }
    }

    //Set the end screen
    setTextHeight(35);
    drawText("Thank you for Watching!", 10, 90);
    ImageFilter Logo = new ImageFilter("Logo.png");
    drawImage(Logo, 50, 200, 300);
  }

  /*
  * Method to Method to apply a filter to the image based on its loyatly/row number
  * Preconditions: row is a int number that is a valid index for images, and toFilter is a ImageFilter object in images
  * Postconditions: None
  */
  public void applyFilter(int row, ImageFilter toFilter){

    //If the image is in row 0 (it is a book), apply the threshold filter at 140
    if(row == 0){
     toFilter.threshold(140); 
    }

    //Else (it is a model), apply the motionBlur filter for 20 horizontally
    else{
      toFilter.motionBlur(15, "horizontal");
    }
  }

  /*
  * Method to find the dimensions of the images
  * Preconditions: row is a valid index of images, and place is a String of value "x", "y", or "width"
  * Postconditions: An interger is returned to serve as a dimension of an image
  */
  public int getDimension(int row, String place){

    //If the dimension required is an x value
    if(place == "x"){

      //If it is at row 0, return the corresponding x value of 50
      if(row == 0){
        return 50;
      }
      //Else: corresponding x value of 0
      else{
        return 0;
      }
    }

    //If the dimension required is a y value
    if(place == "y"){

      //If it is at row 0, return the corresponding y value of 20
      if(row == 0){
        return 20;
      }

      //Else, return the corresponding y value of 100
      else{
        return 100;
      }
    }

    //If the dimension required is a width value
    if(place == "width"){

      //If it is at row 0, return the corresponding width value of 275
      if(row == 0){
        return 275;
      }

      //Else, return the corresponding width value of the scene width
      else{
        return getWidth();
      }
    }
    //If the method is not called corecly, return 0 to prevent an error.
    return 0;
  }
  
}