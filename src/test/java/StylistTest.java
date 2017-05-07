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
// Test to check if all instances of stylist instantiates correctly
   @Test
   public void all_returnsAllInstancesOfStylist_true() {
     Stylist firstStylist = new Stylist("Jane Kiragu", "25477745496" , "Morning", "Crothets", "imageurl");
     firstStylist.save();
     Stylist secondStylist = new Stylist("Mercy Mukami", "25474598696" , "Noon", "Wigs", "imageurl");
     secondStylist.save();
     assertEquals(true, Stylist.all().get(0).equals(firstStylist));
     assertEquals(true, Stylist.all().get(1).equals(secondStylist));
   }
//  Test to check if stylists instanciates correctly with id
    @Test
    public void getId_stylistsInstantiateWithAnId_1() {
      myStylist.save();
      assertTrue(myStylist.getId() > 0);
    }
 }
