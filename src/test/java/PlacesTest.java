import org.junit.*;
import static org.junit.Assert.* ;

public class PlacesTest {

  @Test
  public void places_instantiatesWithPlaces_true() {
    Places myPlaces = new Places("Chennai, India");
    assertEquals("Chennai, India", myPlaces.getLocation());
  }

}
