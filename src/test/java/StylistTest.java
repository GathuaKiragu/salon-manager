import org.junit.*;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  private Stylist myStylist;
  @Before
  public void instanciate(){
  myStylist = new Stylist("Jane Kiragu", "254777598696" , "Morning", "Crothets", "imageurl");
  }

  // Test to check instance of stylist
    @Test
    public void stylist_instantiatesCorrectly_true() {
      assertEquals(true, myStylist instanceof Stylist);
    }
// Test to check if name instantiates correctly
    @Test
      public void getName_stylistInstantiatesWithName() {
        assertEquals("Jane Kiragu", myStylist.getName());
      }
  }
