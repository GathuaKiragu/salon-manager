import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  private Client myClient;
  @Before
  public void instanciate(){
  myClient = new Client("Vanessa Musera", "254777885596" , "Morning", "Crothets", 1);
  }

  // Test to check instance of client
    @Test
    public void client_instantiatesCorrectly_true() {
      assertEquals(true, myClient instanceof Client);
    }
}
