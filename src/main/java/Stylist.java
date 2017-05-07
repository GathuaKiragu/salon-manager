import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String name;
  private String tel;
  private String availability;
  private String specialty;
  private String image;
  private int id;

  public Stylist(String name, String tel, String availability, String specialty, String image) {
    this.name = name;
    this.tel = tel;
    this.availability = availability;
    this.specialty = specialty;
    this.image = image;
  }

// returns the stylists name
  public String getName() {
    return name;
  }
// returns the stylists telephone number
  public String getTel() {
    return tel;
  }
// return how available the stylist is
  public String getAvailability() {
    return availability;
  }
// returns the stylists specialty
  public String getSpecialty() {
    return specialty;
  }
// returns the stylists image
  public String getImage() {
    return image;
  }
// returns the stylist id
  public int getId() {
    return id;
  }
}
