import org.code.theater.*;
import org.code.media.*;

public class TheaterRunner {
  public static void main(String[] args) {


    // Instantiate Scene subclass objects
    FactionsScene faction = new FactionsScene();
    Books_ModelsScene books_models = new Books_ModelsScene();
    
    // Call drawScene methods in each subclass
    faction.drawScene();
    books_models.drawScene();
    
    // Play scenes in Theater, in order of arguments
    Theater.playScenes(faction, books_models);
    
  }
}