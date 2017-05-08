import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();



  private Client myClient;
  @Before
  public void instanciate(){
  myClient = new Client("Vanessa Musera", "254777885596" , "Morning", "pictureurl", "Crothets", 1);
  }

  // Test to check instance of client
  @Ignore
    @Test
    public void client_instantiatesCorrectly_true() {
      assertEquals(true, myClient instanceof Client);
    }

    // Test to check if client instanciates correctly with client name
    @Ignore
      @Test

      public void ClientInstantiatesCorrectlywithName_true() {
        assertEquals("Vanessa Musera", myClient.getName());
      }
      // Test to check if client instanciates correctly with client phone number
      @Ignore
      @Test

      public void ClientInstantiatesCorrectlywithPhone_true() {
        assertEquals("254777885596", myClient.getPhone());
      }
      // Test to check if client instanciates correctly with availability
@Ignore
      @Test

      public void ClientInstantiatesCorrectlywithAbout_true() {
        assertEquals("Morning", myClient.getAvailability());
      }

    // Test to check if client instanciates correctly with image
      @Test
@Ignore
      public void ClientInstantiatesCorrectlywithPicture_true() {
        assertEquals("pictureurl", myClient.getImage());
      }
    // Test to check if client instanciates correctly with stylistId
@Ignore
      @Test
      public void ClientInstantiatesCorrectlywithStyListId_true() {
        assertEquals(1, myClient.getStyListId());
      }
@Ignore
      @Test
        public void save_returnsTrueIfClientsAretheSame() {
          myClient.save();
          assertTrue(Client.all().get(0).equals(myClient));
        }
@Ignore
      @Test
      public void all_returnsAllInstancesOfClient_true() {
        Client firstClient = new Client("Vanessa Musera", "gggkk" , "Morning", "pictureurl", "Crothets", 1);
        firstClient.save();
        Client secondClient = new Client("Clarice Walcott", "kgkkg" , "Noon", "pictureurl1", "BlowDry", 2);
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
      }
@Ignore
      // Test to check if all instances are cleared correctly
      @Test
      public void clear_emptiesAllClientsFromArrayList_0() {
        assertEquals(Client.all().size(), 0);
      }
@Ignore
      // Test to check if clients instantiates correctly with ID
      @Test
      public void getId_clientInstantiateWithAnID_1() {
        myClient.save();
        assertTrue(myClient.getId() > 0);
      }
@Ignore
      @Test
      public void find_returnsClientWithSameId_secondclient() {
        Client firstClient = new Client("Vanessa Musera", "gggkk" , "Morning", "pictureurl", "Crothets", 1);
        firstClient.save();
        Client secondClient = new Client("Clarice Walcott", "kgkkg" , "Noon", "pictureurl1", "BlowDry", 2);
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
      }
@Ignore
      @Test
      public void equals_returnsTrueIfClientDetailsAretheSame() {
        Client firstClient = new Client("Vanessa Musera", "gggkk" , "Morning", "pictureurl", "Crothets", 1);
        Client secondClient = new Client("Vanessa Musera", "gggkk" , "Morning", "pictureurl", "Crothets", 1);
        assertTrue(firstClient.equals(secondClient));
      }
@Ignore
      @Test
      public void save_assignsIdToObject() {
        Client firstClient = new Client("Vanessa Musera", "gggkk" , "Morning", "pictureurl", "Crothets", 1);
        myClient.save();
        Client savedClient = Client.all().get(0);
        assertEquals(myClient.getId(), savedClient.getId());
      }

      // @Test
      // public void save_savesStyListIdIntoDB_true() {
        // Stylist myStylist = new Stylist("Janet KoiKoi", "0711234567", "Evening", "Braids");
        // myStylist.save();
      //   Client firstClient = new Client("Vanessa Musera", "gggkk" , "Morning", "pictureurl", "Crothets", 1);
      //   myClient.save();
      //   Client savedClient = Client.find(myClient.getId());
      //   assertEquals(savedClient.getStyListId(), myStylist.getId());
      // }
@Ignore
      @Test
      public void update_updatesClientInformation_true() {
        myClient.save();
        myClient.update("Janet Kiragu", "077777777" , "Morning", "pictureurl3", "Wigs", 3);
        assertEquals("Janet Kiragu", Client.find(myClient.getId()).getName());
      }
@Ignore
      @Test
        public void delete_deletesClient_true() {
        myClient.save();
        int myClientId = myClient.getId();
        myClient.delete();
        assertEquals(null, Client.find(myClientId));
      }
}
