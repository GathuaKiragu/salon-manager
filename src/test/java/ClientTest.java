import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  private Client myClient;
  @Before
  public void instanciate(){
  myClient = new Client("Vanessa Musera", "254777885596" , "Morning", "pictureurl", "Crothets", 1);
  }

  // Test to check instance of client
    @Test
    public void client_instantiatesCorrectly_true() {
      assertEquals(true, myClient instanceof Client);
    }

    // Test to check if client instanciates correctly with client name
      @Test

      public void ClientInstantiatesCorrectlywithName_true() {
        assertEquals("Vanessa Musera", myClient.getName());
      }
      // Test to check if client instanciates correctly with client phone number
      @Test

      public void ClientInstantiatesCorrectlywithPhone_true() {
        assertEquals("254777885596", myClient.getPhone());
      }
      // Test to check if client instanciates correctly with availability

      @Test

      public void ClientInstantiatesCorrectlywithAbout_true() {
        assertEquals("Morning", myClient.getAvailability());
      }

    // Test to check if client instanciates correctly with StylistId
      @Test

      public void ClientInstantiatesCorrectlywithPicture_true() {
        assertEquals("pictureurl", myClient.getImage());
      }

      @Test
      public void ClientInstantiatesCorrectlywithStyListId_true() {
        assertEquals(1, myClient.getStyListId());
      }

}
