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
  //  Test to check if find method can find record with id
     @Test
     public void find_returnsStylistWithSameId_secondStylist() {
       Stylist firstStylist = new Stylist("Jane Kiragu", "25477745496" , "Morning", "Crothets", "imageurl");
       firstStylist.save();
       Stylist secondStylist = new Stylist("Jane Kiragu", "25477745496" , "Morning", "Crothets", "imageurl");
       secondStylist.save();
       assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
     }
     @Test
     public void equals_returnsTrueIfNamesAretheSame() {
       Stylist firstStylist = new Stylist("Fanulla Kiragu", "254724500045" , "Morning", "Wigs", "imageurl");
       Stylist secondStylist = new Stylist("Fanulla Kiragu","254724500045" , "Morning", "Wigs", "imageurl");
       assertTrue(firstStylist.equals(secondStylist));
     }
     // Test to check if stylist information can be saved correctly into the database
     @Test
     public void save_savesIntoDatabase_true() {
       Stylist myStylist = new Stylist("Fanulla Kiragu", "254724500045" , "Morning", "Wigs", "imageurl");
       myStylist.save();
       assertTrue(Stylist.all().get(0).equals(myStylist));
     }
     // Test to check if saved Object is assigned an id
     @Test
     public void save_assignsIdToObject() {
       Stylist myStylist = new Stylist("Fanulla Kiragu", "254724500045" , "Morning", "Wigs", "imageurl");
       myStylist.save();
       Stylist savedStylist = Stylist.all().get(0);
       assertEquals(myStylist.getId(), savedStylist.getId());
     }
     // Test to check if client information can be retrieved successfully from the database
     @Test
     public void getClients_retrievesAllStylistsFromDatabase_stylistsList() {
       Stylist myStylist = new Stylist("Jane Kiragu", "254777598696" , "Morning", "Crothets", "imageurl");
       myStylist.save();
       Client firstClient = new Client("Vanessa Musera", "gggkk" , "Morning", "pictureurl", "Crothets", myStylist.getId());
       firstClient.save();
       Client secondClient = new Client("Vanessa Musera", "gggkk" , "Morning", "pictureurl", "Crothets", myStylist.getId());
       secondClient.save();
       Client[] clients = new Client[] { firstClient, secondClient };
       assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
     }
     // Test to check if information from the database can be updated successfully
     @Test
     public void update_updatesStylistInformation_true() {
       myStylist.save();
       myStylist.update("Janet Kiragu", "077777777" , "Morning", "wigs", "imageurl");
       assertEquals("Janet Kiragu", Stylist.find(myStylist.getId()).getName());
     }
     // Test to check if stylist records can be deleted from the dabase
     @Test
       public void delete_deletesStylist_true() {
       myStylist.save();
       int myStylistId = myStylist.getId();
       myStylist.delete();
       assertEquals(null, Stylist.find(myStylistId));
     }
 }
