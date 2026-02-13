import org.code.theater.*;
import org.code.media.*;

public class ImageFilter extends ImagePlus{
  public ImageFilter(String filename){
    super(filename);
  }

/*
* Filter the image to appear darker and more grim
* Preconditions: None
* Postconditions: None
*/
  public void makeDark(){

    //Define an array to store the pixels
    Pixel[][] pixels = getPixelsFromImage();

    //For every pixel
    for(int row =0; row< pixels.length; row++){
      for(int col=0; col< pixels[row].length; col++){

        //Set the current pixel and its values
        Pixel currentPixel = pixels[row][col];
        int Red = currentPixel.getRed();
        int Green = currentPixel.getGreen();
        int Blue = currentPixel.getBlue();

        //Apply the filter to the RGB values
        Red *= 0.6;
        Green *= 0.4;
        Blue *= 0.5;
        

        //Set the pixel its new values
        currentPixel.setRed(Red);
        currentPixel.setGreen(Green);
        currentPixel.setBlue(Blue);
        
      }
    }
  }

  
/*
* Filter the image to reverse the color values
* Preconditions: None
* Postconditions: None
*/
  public void makeNegative() {

    //Define an array to store the pixels
    Pixel[][] pixels = getPixelsFromImage();

    //For every pixel
    for(int row =0; row< pixels.length; row++){
      for(int col=0; col< pixels[row].length; col++){

        //Set the current pixel and its values
        Pixel currentPixel = pixels[row][col];
        int Red = currentPixel.getRed();
        int Green = currentPixel.getGreen();
        int Blue = currentPixel.getBlue();

        //Inverse the RGB values
        Red = 255 - Red;
        Green = 255 - Green;
        Blue = 255 - Blue;
        
        //Set the pixel to its new values
        currentPixel.setRed(Red);
        currentPixel.setGreen(Green);
        currentPixel.setBlue(Blue);
        
      }
    }
  }

  /*
  * Filter the image to shift all of the colors by an amount
  * Preconditions: value is a non-negative number less than 255
  * Postconditions: None
  */
  public void colorShift(int value) {

    //Define an array to store the pixels
    Pixel[][] pixels = getPixelsFromImage();

    //For every pixel
    for(int row=0; row<pixels.length; row++){
      for(int col=0; col<pixels[row].length; col++){

        //Set the current pixel
        Pixel currentPixel = pixels[row][col];

        //Create variables to store the shifted RGB values
        int Red = currentPixel.getRed() + value;
        int Green = currentPixel.getGreen() + value;
        int Blue = currentPixel.getBlue() + value;

        //If the shifted number exceeds the possible value, then set it to 255.
        if(Red > 255){
          Red = 255;
        }
        if(Green > 255){
          Green = 255;
        }
        if(Blue > 255){
          Blue = 255;
        }

        //Set the pixels RGB to its new values
        currentPixel.setRed(Red);
        currentPixel.setGreen(Green);
        currentPixel.setBlue(Blue);
      }
    }
  }

  /*
  * Filter the image to apply a sepia look
  * Preconditions: None
  * Postconditions: None
  */
  public void applySepia() {

    //Define an array to store the pixels
    Pixel[][] pixels = getPixelsFromImage();

    //For every pixel
    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length; col++) {

        //Set the current pixels and its values
        Pixel currentPixel = pixels[row][col];
        int iRed = currentPixel.getRed();
        int iGreen = currentPixel.getGreen();
        int iBlue = currentPixel.getBlue();

        //Apply the filter to each color
        int Red = (int)((0.393 * iRed) + (0.769 * iGreen) + (0.189 * iBlue));
        int Green = (int)((0.349 * iRed) + (0.686 * iGreen) + (0.168 * iBlue));
        int Blue = (int)((0.272 * iRed) + (0.534 * iGreen) + (0.131 * iBlue));

        //If the generated value exceeds the RGB limit either positevly or negitavely, set the value to the closest bound
        if(Red > 255){
          Red = 255;
        }
        if(Red< 0){
          Red = 0;
        }
        if(Green > 255){
          Green = 255;
        }
        if(Green< 0){
          Green = 0;
        }
        if(Blue > 255){
          Blue = 255;
        }
        if(Blue< 0){
          Blue = 0;
        }

        //Set the pixel to its new values
        currentPixel.setRed(Red);
        currentPixel.setGreen(Green);
        currentPixel.setBlue(Blue);
      }
    }    
  }

  /*
  * Filter the image to add a motion blur effect
  * Preconditions: length is a value greater than 0 and less than the image lenght, direction is a String of "horizontal", "vertical", or "diagonal"
  * Postconditions: None
  */
  public void motionBlur(int length, String direction) {
    
    //get the pixels in the image
    Pixel[][] pixels = getPixelsFromImage();

    //Iterate through the pixels
    for(int row=0; row<pixels.length; row++){
      for(int col=0; col<pixels[row].length; col++){

        //Initalize colors to zero
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;

        //Initialize position to our current pixel
        int x = col;
        int y = row;

        //Count number of pixels iterated for motion blur
        int distance = 0;
        
        //For the length of the blur but not going beyond the image
        for(int count=0; count<length && x<pixels[row].length && y < pixels.length; count++){

          //Add one to the distance
          distance++;

          //Get the pixels and accumulate its RGB values
          Pixel current = pixels[y][x];
          totalRed += current.getRed();
          totalGreen += current.getGreen();
          totalBlue += current.getBlue();

          //Move based on the direction
          if(direction == "horizontal"){
            x += 1;
          }
          if(direction == "vertical"){
            y += 1;
          }
          if(direction == "diagonal"){
            x += 1;
            y += 1;
          }
        }

        //Find the mean
        totalRed /= distance;
        totalGreen /= distance;
        totalBlue /= distance;

        //Set the new color value
        pixels[row][col].setRed(totalRed);
        pixels[row][col].setGreen(totalGreen);
        pixels[row][col].setBlue(totalBlue);
        
      }
    }    
  }

  /*
  * Filter to turn the image into only black and white pixels based on the parameter
  * Preconditions: value is a int number in the interval (0, 255)
  * Postconditions: None
  */
  public void threshold(int value) {

    //Define an array to store the pixels
    Pixel[][] pixels = getPixelsFromImage();

    //For every pixel
    for(int row=0; row<pixels.length; row++){
      for(int col=0; col<pixels[row].length; col++){

        //Find the average of the RGB color values
        int average = (pixels[row][col].getRed() + pixels[row][col].getGreen() + pixels[row][col].getBlue()) /3;
        
        //If the average is less than the value, set the pixel to black
        if(average < value){
          pixels[row][col].setRed(0);
          pixels[row][col].setGreen(0);
          pixels[row][col].setBlue(0);
        }

        //Else, set the pixel to white
        else{
          pixels[row][col].setRed(255);
          pixels[row][col].setGreen(255);
          pixels[row][col].setBlue(255);
        }
      }
    }
  }

  /*
  * Filter to turn the pixels to a pure RGB value based on its average
  * Preconditions: None
  * Postconditions: None
  */
  public void colorize() {

    //Define an array to store the pixels
    Pixel[][] pixels = getPixelsFromImage();

    //For every pixel
    for(int row=0; row<pixels.length; row++){
      for(int col=0; col<pixels[row].length; col++){

        //Find the average of the RGB values
        int average = (pixels[row][col].getRed() + pixels[row][col].getGreen() + pixels[row][col].getBlue()) /3;
       
        //If the average is less than 85, turn the pixel red
        if(average < 85){
          pixels[row][col].setRed(255);
          pixels[row][col].setGreen(0);
          pixels[row][col].setBlue(0);
        }

        //Else if the average is less than 170, turn the pixel green
        else if(average< 170){
          pixels[row][col].setRed(0);
          pixels[row][col].setGreen(255);
          pixels[row][col].setBlue(0);
        }

        //Else turn the pixel blue
        else{
          pixels[row][col].setRed(0);
          pixels[row][col].setGreen(0);
          pixels[row][col].setBlue(255);
        }
      }
    } 
  }
}